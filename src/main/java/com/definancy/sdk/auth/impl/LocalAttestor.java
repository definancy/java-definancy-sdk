package com.definancy.sdk.auth.impl;

import com.definancy.sdk.auth.Attestor;
import com.definancy.sdk.auth.Jwt;
import com.definancy.sdk.crypto.KeyPair;

import java.io.IOException;

public class LocalAttestor implements Attestor {
    private final KeyPair keyPair;
    private final String network;
    private final String audience;

    public LocalAttestor(String network, String audience) throws Exception {
        this.keyPair = KeyPair.generateKeyPair();
        this.network = network;
        this.audience = audience;
    }

    @Override
    public String Attest(String thumbprint) throws IOException {
        try {
            Jwt authorization = Jwt.createAuthorization(
                    this.keyPair.computeDID(this.network),
                    this.audience,
                    thumbprint
            );

            String signature = keyPair.sign(authorization.encodeB64());
            authorization.setSignature(signature);
            return authorization.encodeB64();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
