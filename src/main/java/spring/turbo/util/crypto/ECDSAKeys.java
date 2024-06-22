/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

import static spring.turbo.util.Base64Utils.encode;

/**
 * @author 应卓
 * @since 3.2.6
 */
public final class ECDSAKeys implements Keys {

    private final String base64PublicKey;
    private final String base64PrivateKey;

    public ECDSAKeys(String base64PublicKey, String base64PrivateKey) {
        this.base64PublicKey = base64PublicKey;
        this.base64PrivateKey = base64PrivateKey;
    }

    public static ECDSAKeys create() {
        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
            generator.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
            KeyPair keyPair = generator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            return new ECDSAKeys(encode(publicKey.getEncoded()), encode(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new AssertionError();
        }
    }

    public static ECDSAKeys fromString(String publicKey, String privateKey) {
        return new ECDSAKeys(publicKey, privateKey);
    }

    @Override
    public String getBase64PublicKey() {
        return base64PublicKey;
    }

    @Override
    public String getBase64PrivateKey() {
        return base64PrivateKey;
    }

}
