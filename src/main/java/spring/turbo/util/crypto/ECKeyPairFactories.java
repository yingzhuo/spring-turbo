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
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * {@link ECKeyPair} 创建工具
 *
 * @author 应卓
 * @since 3.2.2
 */
public final class ECKeyPairFactories {

    public static final String ALG_EC = "EC";

    /**
     * 私有构造方法
     */
    private ECKeyPairFactories() {
        super();
    }

    /**
     * 创建实例
     *
     * @return 密钥对
     */
    public static ECKeyPair create() {
        return create(ECGenParameterSpec.secp256r1);
    }

    /**
     * 创建实例
     *
     * @param ecGenParameterSpec EC生成参数
     * @return 密钥对
     */
    public static ECKeyPair create(ECGenParameterSpec ecGenParameterSpec) {
        try {
            var generator = KeyPairGenerator.getInstance(ALG_EC);
            generator.initialize(new java.security.spec.ECGenParameterSpec(ecGenParameterSpec.getStdName()), new SecureRandom());
            var keyPair = generator.generateKeyPair();
            return new Pair((ECPublicKey) keyPair.getPublic(), (ECPrivateKey) keyPair.getPrivate());
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * 从文本中读取密钥对
     *
     * @param publicKeyString  公钥。Base64HexEncoded
     * @param privateKeyString 私钥。Base64HexEncoded
     * @return 密钥对
     */
    public static ECKeyPair createFromBased64EncodedString(String publicKeyString, String privateKeyString) {
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
    public static ECKeyPair createFromHexEncodedString(String publicKeyString, String privateKeyString) {
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
    public static ECKeyPair createFromBytes(byte[] publicKeyBytes, byte[] privateKeyBytes) {
        try {
            var keyFactory = KeyFactory.getInstance(ALG_EC);
            var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            var pub = keyFactory.generatePublic(publicKeySpec);

            var privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            var pri = keyFactory.generatePrivate(privateKeySpec);

            return new Pair((ECPublicKey) pub, (ECPrivateKey) pri);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * 从JDK密钥对生成
     *
     * @param keyPair JDK密钥对
     * @return 密钥对
     */
    public static ECKeyPair createFromJdkKeyPair(KeyPair keyPair) {
        return new Pair(
                (ECPublicKey) keyPair.getPublic(),
                (ECPrivateKey) keyPair.getPrivate()
        );
    }

    /**
     * 从JDK密钥中生成
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 密钥对
     */
    public static ECKeyPair createFromJdkKeys(PublicKey publicKey, PrivateKey privateKey) {
        return new Pair(
                (ECPublicKey) publicKey,
                (ECPrivateKey) privateKey
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    private record Pair(ECPublicKey publicKey, ECPrivateKey privateKey) implements ECKeyPair {

        Pair {
            Asserts.notNull(publicKey, "publicKey is required");
            Asserts.notNull(privateKey, "privateKey is required");
        }

        @Override
        public ECPublicKey getJdkPublicKey() {
            return this.publicKey;
        }

        @Override
        public ECPrivateKey getJdkPrivateKey() {
            return this.privateKey;
        }
    }

}
