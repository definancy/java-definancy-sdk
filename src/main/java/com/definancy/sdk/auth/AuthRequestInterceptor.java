package com.definancy.sdk.auth;

import java.io.IOException;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;

public class AuthRequestInterceptor implements HttpRequestInterceptor {
    private final AuthProvider provider;

    public AuthRequestInterceptor(AuthProvider provider) throws Exception {
        this.provider = provider;
    }

    @Override
    public void process(
            HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext)
            throws HttpException, IOException {
        if (this.provider == null) {
            return;
        }

        String authorizationToken;
        String dpopToken;
        try {
            Authentication authentication = this.provider.authenticate(httpRequest, entityDetails, httpContext);
            authorizationToken = authentication.getAuthorization().encodeB64();
            dpopToken = authentication.getDPoP().encodeB64();
        } catch (Exception e) {
            throw new IOException(e);
        }

        // Build new request with both headers
        httpRequest.addHeader("Authorization", "DPoP " + authorizationToken);
        httpRequest.addHeader("DPoP", dpopToken);
    }
}
