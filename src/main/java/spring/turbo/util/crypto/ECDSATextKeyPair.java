/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

import static spring.turbo.util.Base64Utils.decode;
import static spring.turbo.util.Base64Utils.encodeWithoutPadding;

/**
 * @author 应卓
 * @see #create()
 * @see #fromString(String, String)
 * @since 3.1.1
 */
public final class ECDSATextKeyPair extends TextKeyPair {

    public static final String ALGORITHM = "EC";

    private final String publicKeyBase64;
    private final String privateKeyBase64;
    private final byte[] publicKeyBytes;
    private final byte[] privateKeyBytes;

    private ECDSATextKeyPair(String publicKeyBase64, String privateKeyBase64, byte[] publicKeyBytes, byte[] privateKeyBytes) {
        this.publicKeyBase64 = publicKeyBase64;
        this.privateKeyBase64 = privateKeyBase64;
        this.publicKeyBytes = publicKeyBytes;
        this.privateKeyBytes = privateKeyBytes;
    }

    public static ECDSATextKeyPair create() {
        try {
            var generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
            var keyPair = generator.generateKeyPair();
            var publicKey = keyPair.getPublic();
            var privateKey = keyPair.getPrivate();

            return new ECDSATextKeyPair(
                    encodeWithoutPadding(publicKey.getEncoded()),
                    encodeWithoutPadding(privateKey.getEncoded()),
                    publicKey.getEncoded(),
                    privateKey.getEncoded()
            );
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new AssertionError();
        }
    }

    public static ECDSATextKeyPair fromString(String publicKey, String privateKey) {
        return new ECDSATextKeyPair(publicKey, privateKey, decode(publicKey), decode(privateKey));
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
