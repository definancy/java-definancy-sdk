package com.definancy.sdk.auth.impl;

import com.definancy.sdk.auth.Attestor;
import com.definancy.sdk.auth.Jwt;
import com.definancy.sdk.crypto.KeyPair;

import java.io.IOException;

public class LocalAttestor implements Attestor {
    private final KeyPair keyPair;
    private final String network;
    private final String audience;

    public LocalAttestor(KeyPair keyPair, String network, String audience) throws Exception {
        this.keyPair = keyPair;
        this.network = network;
        this.audience = audience;
    }

    public LocalAttestor(String network, String audience) throws Exception {
        this(KeyPair.generateKeyPair(), network, audience);
    }

    @Override
    public String Attest(String thumbprint) throws IOException {
        try {
            Jwt authorization = Jwt.createAuthorization(
                    this.keyPair.publicKey().computeDID(this.network),
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
