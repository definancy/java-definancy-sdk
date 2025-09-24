package com.definancy.sdk.auth;

import com.definancy.sdk.crypto.KeyPair;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    private final String accessToken;
    private final KeyPair keyPair;

    public AuthInterceptor(Attestor attestor) throws Exception {
        this.keyPair = KeyPair.generateKeyPair();
        String thumbprint = this.keyPair.publicKey().jwk().thumbprint();
        this.accessToken = attestor.Attest(thumbprint);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (accessToken == null) {
            return chain.proceed(originalRequest);
        }

        // Generate DPoP token for this specific request
        Jwt dpop = Jwt.createDPoP(
            this.keyPair.publicKey(),
            originalRequest.method(),
            originalRequest.url().toString(),
            null
        );

        String dpopToken;
        try {
            dpopToken = dpop.encodeB64();
        } catch (Exception e) {
            throw new IOException(e);
        }

        // Build new request with both headers
        Request newRequest = originalRequest.newBuilder()
                .header("Authorization", "DPoP " + accessToken)
                .header("DPoP", dpopToken)
                .build();

        return chain.proceed(newRequest);
    }
}
