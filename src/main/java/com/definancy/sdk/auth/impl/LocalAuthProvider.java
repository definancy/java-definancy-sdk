package com.definancy.sdk.auth.impl;

import com.definancy.sdk.DID;
import com.definancy.sdk.auth.*;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.ext.Provider;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.UUID;

@Provider
public class LocalAuthProvider implements AuthProvider {
        private final DID did;
        private final Signer signer;

        public LocalAuthProvider(DID did, Signer signer) throws Exception {
                this.did = did;
                this.signer = signer;
        }

        public Authentication authenticate(ClientRequestContext requestContext) throws Exception {
                URI uri = requestContext.getUri();
                URI audience = new URIBuilder()
                                .setScheme(uri.getScheme())
                                .setHost(uri.getHost())
                                .setPort(uri.getPort())
                                .build();

                Jwk jwk = signer.jwk();

                Authorization authorization = new Authorization(
                                did,
                                audience.toString(),
                                jwk.thumbprint());

                authorization.setSignature(
                                signer.sign(authorization.encodeB64()));

                // Generate DPoP token for this specific request
                Object body = requestContext.getEntity();
                String id = UUID.randomUUID().toString();

                DPoP dpop = new DPoP(
                                id,
                                requestContext.getMethod(),
                                audience.toString(),
                                body == null ? null : body.toString(),
                                jwk);
                dpop.setSignature(
                                signer.sign(dpop.encodeB64()));

                return new Authentication(authorization, dpop);
        }
}
