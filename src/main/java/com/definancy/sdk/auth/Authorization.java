package com.definancy.sdk.auth;

import com.definancy.sdk.DID;

import java.time.Instant;

public class Authorization extends Jwt {
    public Authorization(DID did, String audience, String thumbprint) {
        super();

        AuthorizationHeader header = new AuthorizationHeader();

        AuthorizationClaims claims = new AuthorizationClaims();
        claims.issuer = did.toString();
        claims.subject = did.toString();
        claims.audience = audience;
        claims.issuedAt = Instant.now().getEpochSecond();
        claims.expiresAt = claims.issuedAt + 60;
        claims.confirmation = new AuthorizationClaims.ConfirmationClaims();
        claims.confirmation.thumbprint = thumbprint;

        this.setHeader(header);
        this.setClaims(claims);
    }
}
