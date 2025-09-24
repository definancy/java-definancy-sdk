package com.definancy.sdk.auth;

import java.io.IOException;

public interface Attestor {
    public String Attest(String thumbprint) throws IOException;
}
