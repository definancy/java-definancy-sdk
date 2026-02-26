package com.definancy.sdk.demo;

import com.definancy.sdk.auth.Authorization;
import com.definancy.sdk.auth.DPoP;
import com.definancy.sdk.auth.Jwt;
import com.definancy.sdk.crypto.KeyPair;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class GenerateAuth {
    public static void main(String[] args) throws Exception {
        KeyPair master = KeyPair.generateKeyPair();
        KeyPair slave = KeyPair.generateKeyPair();

        String network = Config.network;
        String audience = Config.audience;

        Jwt authorization =
                new Authorization(
                        master.publicKey().computeDID(network),
                        audience,
                        slave.publicKey().jwk().thumbprint());
        String signature = master.sign(authorization.encodeB64());
        authorization.setSignature(signature);
        String authorizationString =
                String.format("Authorization: DPoP %s", authorization.encodeB64());

        String id = UUID.randomUUID().toString();
        Jwt dpop = new DPoP(id, "GET", audience + "/v1/contract", null, slave.publicKey().jwk());
        signature = slave.sign(dpop.encodeB64());
        dpop.setSignature(signature);
        System.out.println("=== Request 1 ===");
        System.out.println(authorizationString);
        System.out.printf("DPoP: %s\n", dpop.encodeB64());

        System.out.println();

        byte[] body =
                ("{\n"
                                + "  \"contract-ids\": [\n"
                                + "    {\n"
                                + "      \"asset-unit\": \"BTC\",\n"
                                + "      \"network-id\": \"bitcoin\"\n"
                                + "    },\n"
                                + "    {\n"
                                + "      \"asset-unit\": \"EUR\",\n"
                                + "      \"network-id\": \"target\"\n"
                                + "    }\n"
                                + "  ],\n"
                                + "  \"enabled\": true\n"
                                + "}\n")
                        .getBytes(StandardCharsets.UTF_8);

        dpop = new DPoP(id, "PUT", audience + "/v1/vault/myVault", body, slave.publicKey().jwk());
        signature = slave.sign(dpop.encodeB64());
        dpop.setSignature(signature);
        System.out.println("=== Request 2 ===");
        System.out.println(authorizationString);
        System.out.printf("DPoP: %s\n", dpop.encodeB64());
    }
}
