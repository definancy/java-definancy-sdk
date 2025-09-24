package com.definancy.sdk.crypto;

import com.definancy.sdk.DID;
import com.definancy.sdk.ID;
import com.definancy.sdk.util.CryptoProvider;
import com.definancy.sdk.util.Encoder;

import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;

public class KeyPair {
    private final java.security.KeyPair privateKeyPair;

    private static final String KEY_ALGO = "Ed25519";
    private static final String SIGN_ALGO = "EdDSA";
    private static final int SK_SIZE = 32;
    private static final int SK_SIZE_BITS = SK_SIZE * 8;

    /**
     * KeyPair creates a new, random account.
     */
    private KeyPair() throws Exception {
        this((SecureRandom)null);
    }

    public static KeyPair generateKeyPair() throws Exception {
        return new KeyPair();
    }

    // randomSrc can be null, in which case system default is used
    public KeyPair(SecureRandom randomSrc) throws Exception {
        CryptoProvider.setupIfNeeded();
        KeyPairGenerator gen = KeyPairGenerator.getInstance(KEY_ALGO);
        if (randomSrc != null) {
            gen.initialize(SK_SIZE_BITS, randomSrc);
        }
        this.privateKeyPair = gen.generateKeyPair();
    }

    public Ed25519PublicKey publicKey() {
        return new Ed25519PublicKey(this.privateKeyPair.getPublic());
    }

    public DID computeDID(String network) throws Exception {
        ID id = new ID(this.publicKey());
        return new DID(network, id);
    }

    public String sign(String message) throws Exception {
        CryptoProvider.setupIfNeeded();
        byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);
        Signature signer = Signature.getInstance(SIGN_ALGO);
        signer.initSign(this.privateKeyPair.getPrivate());
        signer.update(msgBytes);
        byte[] signature = signer.sign();

        return Encoder.encodeToBase64(signature);
    }

    public Boolean verify(String message, String signature) throws Exception {
        CryptoProvider.setupIfNeeded();

        Signature verifier = Signature.getInstance(SIGN_ALGO);
        verifier.initVerify(this.privateKeyPair.getPublic());
        byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);
        verifier.update(msgBytes);
        byte[] sigBytes = Encoder.decodeFromBase64(signature);

        return verifier.verify(sigBytes);
    }
}
