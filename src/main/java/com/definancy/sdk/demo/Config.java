package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.sdk.DID;
import com.definancy.sdk.auth.AuthInterceptor;
import com.definancy.sdk.auth.impl.LocalAuthProvider;
import com.definancy.sdk.crypto.KeyPair;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

public class Config {
	public static String network = "dev";
	public static String audience = "https://dev.definancy.com";
    public static String secret = "qHWHe6jLnx7gD-CZSe3X2UwgC-ISFOVy4rfFWxxJXX0";
    public static String vaultId = "myVault";

    public static KeyPair getKeyPair() throws Exception {
        return KeyPair.generateKeyPairFromSecret(secret);
    }

    public static DID getDID() throws Exception {
        KeyPair keyPair = getKeyPair();
        return keyPair.publicKey().computeDID(Config.network);
    }

    public static ApiClient GetApiClient() throws Exception {
        String network = Config.network;
        String audience = Config.audience;
        String secret = Config.secret;

        KeyPair keyPair = getKeyPair();
        DID did = getDID();

        LocalAuthProvider signer = new LocalAuthProvider(did, keyPair);
        AuthInterceptor authInterceptor = new AuthInterceptor(signer);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor) // Optional: for debugging
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        ApiClient apiClient = new ApiClient();
        apiClient.setHttpClient(httpClient);
        apiClient.setBasePath(audience);

        return apiClient;
    }
}
