package com.definancy.sdk.auth;

public class Authentication {
    private final Authorization authorization;
    private final DPoP dpop;

    public Authentication(Authorization authorization, DPoP dpop) throws Exception {
        this.authorization = authorization;
        this.dpop = dpop;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public DPoP getDPoP() {
        return dpop;
    }
}
