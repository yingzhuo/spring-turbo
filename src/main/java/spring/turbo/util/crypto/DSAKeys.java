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
import java.security.PrivateKey;
import java.security.PublicKey;

import static spring.turbo.util.crypto.Base64.encode;

/**
 * @author 应卓
 * @since 1.0.0
 */
public class DSAKeys implements KeyPair {

    public static final int KEY_SIZE_512 = 512;

    private final String base64PublicKey;
    private final String base64PrivateKey;

    private DSAKeys(String base64PublicKey, String base64PrivateKey) {
        this.base64PublicKey = base64PublicKey;
        this.base64PrivateKey = base64PrivateKey;
    }

    public static DSAKeys create() {
        return create(KEY_SIZE_512);
    }

    public static DSAKeys create(int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
            generator.initialize(keySize);
            java.security.KeyPair keyPair = generator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            return new DSAKeys(
                    encode(publicKey.getEncoded()),
                    encode(privateKey.getEncoded())
            );

        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getBase64PublicKey() {
        return this.base64PublicKey;
    }

    @Override
    public String getBase64PrivateKey() {
        return this.base64PrivateKey;
    }

}
