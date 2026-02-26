package com.definancy.sdk.auth.impl;

import com.definancy.sdk.DID;
import com.definancy.sdk.auth.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.io.entity.BasicHttpEntity;
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.net.URIBuilder;

public class LocalAuthProvider implements AuthProvider {
    private final DID did;
    private final Signer signer;

    public LocalAuthProvider(DID did, Signer signer) throws Exception {
        this.did = did;
        this.signer = signer;
    }

    public Authentication authenticate(
            HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext)
            throws Exception {
        URI uri = httpRequest.getUri();
        URI audience =
                new URIBuilder()
                        .setScheme(uri.getScheme())
                        .setHost(uri.getHost())
                        .setPort(uri.getPort())
                        .build();

        Jwk jwk = signer.jwk();

        Authorization authorization = new Authorization(did, audience.toString(), jwk.thumbprint());

        authorization.setSignature(signer.sign(authorization.encodeB64()));

        // Generate DPoP token for this specific request
        String id = UUID.randomUUID().toString();
        byte[] body = getBody(httpRequest);
        if (body != null && body.length == 0) {
            body = null;
        }

        DPoP dpop = new DPoP(id, httpRequest.getMethod(), audience.toString(), body, jwk);
        dpop.setSignature(signer.sign(dpop.encodeB64()));

        return new Authentication(authorization, dpop);
    }

    private static byte[] getBody(HttpRequest request) throws IOException {
        if (request instanceof ClassicHttpRequest) {
            ClassicHttpRequest classicHttpRequest = (ClassicHttpRequest) request;
            if (classicHttpRequest.getEntity() == null) {
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            classicHttpRequest.getEntity().writeTo(outputStream);
            if (!classicHttpRequest.getEntity().isRepeatable()) {
                // copy back the entity, so it can be read again
                BasicHttpEntity entity =
                        new BasicHttpEntity(
                                new ByteArrayInputStream(outputStream.toByteArray()),
                                ContentType.parse(classicHttpRequest.getEntity().getContentType()));
                // wrap into repeatable entity to support retries
                classicHttpRequest.setEntity(new BufferedHttpEntity(entity));
            }
            return outputStream.toByteArray();
        } else if (request instanceof SimpleHttpRequest) {
            SimpleHttpRequest simpleHttpRequest = (SimpleHttpRequest) request;
            if (simpleHttpRequest.getBody() == null) {
                return null;
            }
            return simpleHttpRequest.getBodyBytes();
        }

        throw new IllegalArgumentException("Unsupported request type: " + request.getClass());
    }
}
