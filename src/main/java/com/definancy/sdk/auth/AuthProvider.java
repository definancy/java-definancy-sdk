package com.definancy.sdk.auth;

import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;

public interface AuthProvider {
    Authentication authenticate(
            HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext)
            throws Exception;
}
