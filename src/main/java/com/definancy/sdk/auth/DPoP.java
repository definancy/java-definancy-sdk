package com.definancy.sdk.auth;

import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

public class DPoP extends Jwt {
    public DPoP(String method, String uri, String body, Jwk jwk) {
        super();
        String bodyHash = null;
        if (body != null) {
            byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
            byte[] bodyDigest = Digester.digest(bodyBytes);
            bodyHash = Encoder.encodeToBase64(bodyDigest);
        }

        DPoPHeader header = new DPoPHeader();
        header.jwk = jwk;

        DPoPClaims claims = new DPoPClaims();
        claims.id = UUID.randomUUID().toString();
        claims.method = method;
        claims.uri = uri;
        claims.issuedAt = Instant.now().getEpochSecond();
        claims.expiresAt = claims.issuedAt + 60;
        claims.bodyHash = bodyHash;

        this.setHeader(header);
        this.setClaims(claims);
    }
}
