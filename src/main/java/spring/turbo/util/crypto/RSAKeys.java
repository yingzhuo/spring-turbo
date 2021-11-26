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
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static spring.turbo.util.crypto.Base64.encode;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class RSAKeys implements KeyPair {

    public static final int KEY_SIZE_1024 = 1024;
    public static final int KEY_SIZE_2048 = 2048;

    private final String base64PublicKey;
    private final String base64PrivateKey;

    private RSAKeys(String publicKey, String privateKey) {
        this.base64PublicKey = publicKey;
        this.base64PrivateKey = privateKey;
    }

    public static RSAKeys create() {
        return create(KEY_SIZE_2048);
    }

    public static RSAKeys create(int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(keySize);
            java.security.KeyPair keyPair = generator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            return new RSAKeys(
                    encode(publicKey.getEncoded()),
                    encode(privateKey.getEncoded())
            );
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public static RSAKeys fromString(String publicKey, String privateKey) {
        return new RSAKeys(publicKey, privateKey);
    }

    public String getBase64PublicKey() {
        return this.base64PublicKey;
    }

    public String getBase64PrivateKey() {
        return this.base64PrivateKey;
    }


}
