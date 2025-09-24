package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.api.AssetApi;
import com.definancy.model.Asset;
import com.definancy.sdk.auth.AuthInterceptor;
import com.definancy.sdk.auth.impl.LocalAttestor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class APIGetAssets {
    public static void main(String[] args) throws Exception {
        String network = Config.network;
        String audience = Config.audience;

        LocalAttestor localAttestor = new LocalAttestor(network, audience);
        AuthInterceptor authInterceptor = new AuthInterceptor(localAttestor);

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
        apiClient.setBasePath("https://definancy.xboshy.io");

        AssetApi api = new AssetApi(apiClient);

        List<Asset> assets = api.getAssets();

        if (assets.isEmpty()) {
            System.out.println("No assets found");
            return;
        }

        System.out.printf("Found %d assets:", assets.size());
        for (Asset asset : assets) {
           System.out.println(asset);
        }
    }
}
