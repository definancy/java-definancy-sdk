package com.definancy.sdk.auth;

import com.definancy.sdk.DID;
import com.definancy.sdk.crypto.Ed25519PublicKey;
import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Jwt {
	@JsonProperty("header")
	private final Header header;
	@JsonProperty("claims")
	private final Claims claims;
	@JsonProperty("signature")
	private String signature;

	public Jwt(Header header, Claims claims) {
		this.header = header != null ? header : new Header();
		this.claims = claims != null ? claims : new Claims();
	}

	@JsonIgnore
	public String json() throws Exception {
		return Encoder.encodeToJson(this);
	}

	@JsonIgnore
	public byte[] encode() throws Exception {
		return this.encodeB64().getBytes(StandardCharsets.UTF_8);
	}

	@JsonIgnore
	public String encodeB64() throws Exception {
		String h = this.header.encodeB64();
		String c = this.claims.encodeB64();

		if (this.signature == null) {
			return h + "." + c;
		}

		return h + "." + c + "." + this.signature;
	}

	@JsonIgnore
	public void setSignature(String signature) throws Exception {
		this.signature = signature;
	}

	@Override
	public String toString() {
		String result = "<invalid>";

		try {
			result = this.encodeB64();
		} catch (Exception e) {
			/* do nothing */;
		}

		return result;
	}

	public static Jwt createAuthorization(DID did, String audience, String thumbprint) {
		AuthorizationHeader header = new AuthorizationHeader();

		AuthorizationClaims claims = new AuthorizationClaims();
		claims.issuer = did.toString();
		claims.subject = did.toString();
		claims.audience = audience;
		claims.issuedAt = Instant.now().getEpochSecond();
		claims.expiresAt = claims.issuedAt + 60;
		claims.confirmation = new AuthorizationClaims.ConfirmationClaims();
		claims.confirmation.thumbprint = thumbprint;

		return new Jwt(header, claims);
	}

	public static Jwt createDPoP(Ed25519PublicKey publicKey, String method, String uri, String body) {
		String bodyHash = null;
		if (body != null) {
			byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
			byte[] bodyDigest = Digester.digest(bodyBytes);
			bodyHash = Encoder.encodeToBase64(bodyDigest);
		}

		DPoPHeader header = new DPoPHeader();
		header.jwk = publicKey.jwk();

		DPoPClaims claims = new DPoPClaims();
		claims.id = UUID.randomUUID().toString();
		claims.method = method;
		claims.uri = uri;
		claims.issuedAt = Instant.now().getEpochSecond();
		claims.expiresAt = claims.issuedAt + 60;
		claims.bodyHash = bodyHash;

		return new Jwt(header, claims);
	}
}
