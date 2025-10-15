package com.definancy.sdk.auth;

import okhttp3.Request;

public interface AuthProvider {
    Authentication authenticate(Request request) throws Exception;
}
