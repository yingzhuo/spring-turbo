/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * @author 应卓
 * @see #create()
 * @see #fromString(String, String)
 * @since 3.1.1
 */
public final class RSATextKeyPair extends TextKeyPair {

    public static final String ALGORITHM = "RSA";
    public static final int KEY_SIZE_1024 = 1024;
    public static final int KEY_SIZE_2048 = 2048;

    private final String publicKeyBase64;
    private final String privateKeyBase64;
    private final byte[] publicKeyBytes;
    private final byte[] privateKeyBytes;

    private RSATextKeyPair(String publicKey, String privateKey, byte[] publicKeyBytes, byte[] privateKeyBytes) {
        this.publicKeyBase64 = publicKey;
        this.privateKeyBase64 = privateKey;
        this.publicKeyBytes = publicKeyBytes;
        this.privateKeyBytes = privateKeyBytes;
    }

    public static RSATextKeyPair create() {
        return create(KEY_SIZE_1024);
    }

    public static RSATextKeyPair create(int keySize) {
        try {
            var generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(keySize);

            var keyPair = generator.generateKeyPair();
            var publicKey = keyPair.getPublic();
            var privateKey = keyPair.getPrivate();

            return new RSATextKeyPair(
                    BASE64_ENCODER.encodeToString(publicKey.getEncoded()),
                    BASE64_ENCODER.encodeToString(privateKey.getEncoded()),
                    publicKey.getEncoded(),
                    privateKey.getEncoded()
            );
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public static RSATextKeyPair fromString(String publicKey, String privateKey) {
        return new RSATextKeyPair(publicKey, privateKey, BASE64_DECODER.decode(publicKey), BASE64_DECODER.decode(privateKey));
    }

    @Override
    public String getPublicKeyAsBase64EncodedString() {
        return this.publicKeyBase64;
    }

    @Override
    public String getPrivateKeyAsBase64EncodedString() {
        return this.privateKeyBase64;
    }

    @Override
    public byte[] getPublicKeyAsBytes() {
        return this.publicKeyBytes;
    }

    @Override
    public byte[] getPrivateKeyAsBytes() {
        return this.privateKeyBytes;
    }

    @Override
    public String getAlgorithm() {
        return ALGORITHM;
    }

}
