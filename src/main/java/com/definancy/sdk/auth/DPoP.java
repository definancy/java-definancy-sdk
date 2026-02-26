package com.definancy.sdk.auth;

import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;
import java.time.Instant;

public class DPoP extends Jwt {
    public DPoP(String id, String method, String uri, byte[] body, Jwk jwk) throws Exception {
        super();
        String bodyHash = null;
        if (body != null) {
            byte[] bodyDigest = Digester.digest(body);
            bodyHash = Encoder.encodeToBase64(bodyDigest);
        }

        DPoPHeader header = new DPoPHeader();
        header.jwk = jwk;

        DPoPClaims claims = new DPoPClaims();
        claims.id = id;
        claims.method = method;
        claims.uri = uri;
        claims.issuedAt = Instant.now().getEpochSecond();
        claims.expiresAt = claims.issuedAt + 60;
        claims.bodyHash = bodyHash;

        this.setHeader(header);
        this.setClaims(claims);
    }
}
