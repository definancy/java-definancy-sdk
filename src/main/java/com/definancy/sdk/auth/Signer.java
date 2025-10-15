package com.definancy.sdk.auth;

public interface Signer {
    public String sign(String message) throws Exception;

    public Jwk jwk() throws Exception;
}
