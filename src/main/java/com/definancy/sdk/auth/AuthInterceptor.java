package com.definancy.sdk.auth;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    private final AuthProvider provider;

    public AuthInterceptor(AuthProvider provider) throws Exception {
        this.provider = provider;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (this.provider == null) {
            return chain.proceed(originalRequest);
        }

        String authorizationToken;
        String dpopToken;
        try {
            Authentication authentication = this.provider.authenticate(originalRequest);
            authorizationToken = authentication.getAuthorization().encodeB64();
            dpopToken = authentication.getDPoP().encodeB64();
        } catch (Exception e) {
            throw new IOException(e);
        }

        // Build new request with both headers
        Request newRequest = originalRequest.newBuilder()
                .header("Authorization", "DPoP " + authorizationToken)
                .header("DPoP", dpopToken)
                .build();

        return chain.proceed(newRequest);
    }
}
