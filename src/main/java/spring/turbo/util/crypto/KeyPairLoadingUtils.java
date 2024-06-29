/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.io.IOUtils;
import spring.turbo.util.Asserts;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static spring.turbo.io.CloseUtils.closeQuietly;
import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * 秘钥加载工具
 *
 * @author 应卓
 * @since 3.3.1
 * @deprecated 使用 {@link DSAKeyPair} 等代替
 */
@Deprecated(forRemoval = true, since = "3.3.2")
public final class KeyPairLoadingUtils {

    /**
     * 私有构造方法
     */
    private KeyPairLoadingUtils() {
        super();
    }

    public static KeyPair load(String algorithm, String publicKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return load(
                algorithm,
                new ByteArrayInputStream(publicKey.getBytes(UTF_8)),
                new ByteArrayInputStream(privateKey.getBytes(UTF_8))
        );
    }

    public static KeyPair loadQuietly(String algorithm, String publicKey, String privateKey) {
        try {
            return load(algorithm, publicKey, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static KeyPair load(String algorithm, InputStream publicKey, InputStream privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Asserts.hasText(algorithm, "algorithm is null or blank");
        Asserts.notNull(publicKey, "publicKey is null");
        Asserts.notNull(privateKey, "privateKey is null");

        // read public key
        byte[] encodedPublicKey = IOUtils.copyToByteArray(publicKey);
        closeQuietly(publicKey);

        // read private key
        byte[] encodedPrivateKey = IOUtils.copyToByteArray(privateKey);
        closeQuietly(privateKey);

        // generate KeyPair
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        var publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        var pub = keyFactory.generatePublic(publicKeySpec);

        var privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        var pri = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(pub, pri);
    }

    public static KeyPair loadQuietly(String algorithm, InputStream publicKey, InputStream privateKey) {
        try {
            return load(algorithm, publicKey, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
