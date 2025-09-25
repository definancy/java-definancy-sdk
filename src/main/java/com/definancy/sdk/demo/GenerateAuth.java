package com.definancy.sdk.demo;

import com.definancy.sdk.auth.Jwt;
import com.definancy.sdk.crypto.KeyPair;

public class GenerateAuth {
	public static void main(String[] args) throws Exception {
		KeyPair master = KeyPair.generateKeyPair();
		KeyPair slave =  KeyPair.generateKeyPair();

		String network = Config.network;
		String audience = Config.audience;

		Jwt authorization  = Jwt.createAuthorization(
			master.publicKey().computeDID(network),
			audience,
			slave.publicKey().jwk().thumbprint()
		);
		String signature = master.sign(authorization.encodeB64());
		authorization.setSignature(signature);
		String authorizationString = String.format("Authorization: DPoP %s", authorization.encodeB64());

		Jwt dpop = Jwt.createDPoP(
			slave.publicKey(),
			"GET",
			audience + "/v1/contract",
			null
		);
		signature = slave.sign(dpop.encodeB64());
		dpop.setSignature(signature);
		System.out.println("=== Request 1 ===");
		System.out.println(authorizationString);
		System.out.printf("DPoP: %s\n", dpop.encodeB64());

		System.out.println();

		dpop = Jwt.createDPoP(
			slave.publicKey(),
			"PUT",
			audience + "/v1/vault/myVault",
            "{\n" +
            "  \"contract-ids\": [\n" +
            "    {\n" +
            "      \"asset-unit\": \"BTC\",\n" +
            "      \"network-id\": \"bitcoin\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"asset-unit\": \"EUR\",\n" +
            "      \"network-id\": \"target\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"enabled\": true\n" +
            "}\n"
		);
		signature = slave.sign(dpop.encodeB64());
		dpop.setSignature(signature);
		System.out.println("=== Request 2 ===");
		System.out.println(authorizationString);
		System.out.printf("DPoP: %s\n", dpop.encodeB64());
	}
}
