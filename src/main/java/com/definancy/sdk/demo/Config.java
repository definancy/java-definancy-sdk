package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.model.ContractId;
import com.definancy.sdk.DID;
import com.definancy.sdk.auth.AuthRequestInterceptor;
import com.definancy.sdk.auth.impl.LocalAuthProvider;
import com.definancy.sdk.crypto.KeyPair;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;

public class Config {
    public static String network = "dev";
    public static String audience = "https://" + network + ".definancy.com";
    public static String secret = "qHWHe6jLnx7gD-CZSe3X2UwgC-ISFOVy4rfFWxxJXX0";
    public static String vaultId = "sdkDemoVault";
    public static List<ContractId> priceContracts = Arrays.asList(
            Utils.createContractId("target", "EUR")
    );
    public static List<ContractId> payContracts = Arrays.asList(
        Utils.createContractId("bitcoin-testnet4", "BTC"),
        Utils.createContractId("ethereum-11155111", "ETH"),
        Utils.createContractId("ethereum-11155111", "USDC"),
        Utils.createContractId("litecoin-testnet4", "LTC"),
        Utils.createContractId("algorand-testnet", "ALGO")
    );
    public static List<ContractId> contracts = Stream.concat(
        priceContracts.stream(),
        payContracts.stream()
    ).collect(Collectors.toList());

    public static KeyPair getKeyPair() throws Exception {
        return KeyPair.generateKeyPairFromSecret(secret);
    }

    public static DID getDID() throws Exception {
        KeyPair keyPair = getKeyPair();
        return keyPair.publicKey().computeDID(Config.network);
    }

    public static ApiClient GetApiClient() throws Exception {
        String audience = Config.audience;
        int timeout = 5000;

        ConnectionConfig connConfig =
                ConnectionConfig.custom()
                        .setConnectTimeout(timeout, TimeUnit.MILLISECONDS)
                        .setSocketTimeout(timeout, TimeUnit.MILLISECONDS)
                        .build();

        RequestConfig requestConfig =
                RequestConfig.custom()
                        .setConnectionRequestTimeout(Timeout.ofMilliseconds(2000L))
                        .build();

        BasicHttpClientConnectionManager cm = new BasicHttpClientConnectionManager();
        cm.setConnectionConfig(connConfig);

        LogRequestInterceptor logRequestInterceptor = new LogRequestInterceptor();
        LogResponseInterceptor logResponseInterceptor = new LogResponseInterceptor();

        KeyPair keyPair = getKeyPair();
        DID did = getDID();

        LocalAuthProvider localAuthProvider = new LocalAuthProvider(did, keyPair);
        AuthRequestInterceptor authRequestInterceptor =
                new AuthRequestInterceptor(localAuthProvider);

        ApiClient apiClient = new ApiClient();
        CloseableHttpClient httpClient =
                HttpClientBuilder.create()
                        .setDefaultRequestConfig(requestConfig)
                        .setConnectionManager(cm)
                        .addRequestInterceptorFirst(logRequestInterceptor)
                        .addRequestInterceptorLast(authRequestInterceptor)
                        .addResponseInterceptorLast(logResponseInterceptor)
                        .build();
        apiClient.setHttpClient(httpClient);
        apiClient.setBasePath(audience);

        return apiClient;
    }
}
