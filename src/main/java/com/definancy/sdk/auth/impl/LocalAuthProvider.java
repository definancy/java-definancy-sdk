package com.definancy.sdk.auth.impl;

import com.definancy.sdk.DID;
import com.definancy.sdk.auth.*;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LocalAuthProvider implements AuthProvider {
    private final DID did;
    private final Signer signer;

    public LocalAuthProvider(DID did, Signer signer) throws Exception {
        this.did = did;
        this.signer = signer;
    }

    public Authentication authenticate(Request request) throws Exception {
        HttpUrl url = request.url();
        HttpUrl audience = new HttpUrl.Builder()
                .scheme(url.scheme())
                .host(url.host())
                .build();

        Jwk jwk = signer.jwk();

        Authorization authorization = new Authorization(
                did,
                audience.toString(),
                jwk.thumbprint()
        );

        authorization.setSignature(
                signer.sign(authorization.encodeB64())
        );

        // Generate DPoP token for this specific request
        RequestBody body = request.body();
        DPoP dpop = new DPoP(
                request.method(),
                request.url().toString(),
                body == null? null : body.toString(),
                jwk
        );
        dpop.setSignature(
                signer.sign(dpop.encodeB64())
        );

        return new Authentication(authorization, dpop);
    }
}
