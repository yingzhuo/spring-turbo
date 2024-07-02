/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.Asserts;
import spring.turbo.util.HexUtils;
import spring.turbo.util.StringUtils;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * {@link RSAKeyPair} 创建工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class RSAKeyPairFactories {

    public static final String ALG_RSA = "RSA";

    /**
     * 私有构造方法
     */
    private RSAKeyPairFactories() {
        super();
    }

    /**
     * 创建实例
     *
     * @return 密钥对
     */
    public static RSAKeyPair create() {
        return create(2048);
    }

    /**
     * 创建实例
     *
     * @param keySize keySize
     * @return 密钥对
     */
    public static RSAKeyPair create(int keySize) {
        try {
            var generator = KeyPairGenerator.getInstance(ALG_RSA);
            generator.initialize(keySize);
            var keyPair = generator.generateKeyPair();
            return new Pair((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 从文本中读取密钥对
     *
     * @param publicKeyString  公钥。Base64HexEncoded
     * @param privateKeyString 私钥。Base64HexEncoded
     * @return 密钥对
     */
    public static RSAKeyPair createFromBased64EncodedString(String publicKeyString, String privateKeyString) {
        var decoder = Base64.getDecoder();
        publicKeyString = StringUtils.removeAllWhitespaces(publicKeyString);
        privateKeyString = StringUtils.removeAllWhitespaces(privateKeyString);
        var publicKeyBytes = decoder.decode(publicKeyString);
        var privateKeyBytes = decoder.decode(privateKeyString);
        return createFromBytes(publicKeyBytes, privateKeyBytes);
    }

    /**
     * 从文本中读取密钥对
     *
     * @param publicKeyString  公钥。HexEncoded
     * @param privateKeyString 私钥。HexEncoded
     * @return 密钥对
     */
    public static RSAKeyPair createFromHexEncodedString(String publicKeyString, String privateKeyString) {
        publicKeyString = StringUtils.removeAllWhitespaces(publicKeyString);
        privateKeyString = StringUtils.removeAllWhitespaces(privateKeyString);
        var publicKeyBytes = HexUtils.decode(publicKeyString);
        var privateKeyBytes = HexUtils.decode(privateKeyString);
        return createFromBytes(publicKeyBytes, privateKeyBytes);
    }

    /**
     * 从字节数组中读取密钥对
     *
     * @param publicKeyBytes  公钥。HexEncoded
     * @param privateKeyBytes 私钥。HexEncoded
     * @return 密钥对
     */
    public static RSAKeyPair createFromBytes(byte[] publicKeyBytes, byte[] privateKeyBytes) {
        try {
            var keyFactory = KeyFactory.getInstance(ALG_RSA);
            var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            var pub = keyFactory.generatePublic(publicKeySpec);

            var privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            var pri = keyFactory.generatePrivate(privateKeySpec);

            return new Pair((RSAPublicKey) pub, (RSAPrivateKey) pri);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 从JDK密钥对生成
     *
     * @param keyPair JDK密钥对
     * @return 密钥对
     */
    public static RSAKeyPair createFromJdkKeyPair(KeyPair keyPair) {
        return new Pair(
                (RSAPublicKey) keyPair.getPublic(),
                (RSAPrivateKey) keyPair.getPrivate()
        );
    }

    /**
     * 从JDK密钥中生成
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 密钥对
     */
    public static RSAKeyPair createFromJdkKeys(PublicKey publicKey, PrivateKey privateKey) {
        return new Pair(
                (RSAPublicKey) publicKey,
                (RSAPrivateKey) privateKey
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    private record Pair(RSAPublicKey publicKey, RSAPrivateKey privateKey) implements RSAKeyPair {

        Pair {
            Asserts.notNull(publicKey, "publicKey is required");
            Asserts.notNull(privateKey, "privateKey is required");
        }

        @Override
        public RSAPublicKey getJdkPublicKey() {
            return this.publicKey;
        }

        @Override
        public RSAPrivateKey getJdkPrivateKey() {
            return this.privateKey;
        }
    }

}
