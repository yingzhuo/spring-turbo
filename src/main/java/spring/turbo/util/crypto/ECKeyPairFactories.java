/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.Base64Utils;
import spring.turbo.util.HexUtils;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
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
        return create(ECGenParameterSpec.prime256v1);
    }

    /**
     * 创建实例
     *
     * @param ecGenParameterSpec EC生成参数
     * @return 密钥对
     */
    public static ECKeyPair create(ECGenParameterSpec ecGenParameterSpec) {
        try {
            var generator = KeyPairGenerator.getInstance("EC");
            generator.initialize(new java.security.spec.ECGenParameterSpec(ecGenParameterSpec.getStdName()), new SecureRandom());
            var keyPair = generator.generateKeyPair();
            return new SimpleECKeyPair((ECPublicKey) keyPair.getPublic(), (ECPrivateKey) keyPair.getPrivate());
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * 从文本中读取密钥对
     *
     * @param publicKeyString  公钥。可以是 HexEncoded 或 Base64HexEncoded
     * @param privateKeyString 私钥。可以是 HexEncoded 或 Base64HexEncoded
     * @return 密钥对
     */
    public static ECKeyPair createFromString(String publicKeyString, String privateKeyString) {
        if (Base64Utils.isBase64(publicKeyString) && Base64Utils.isBase64(privateKeyString)) {
            return createFromBased64EncodedString(publicKeyString, privateKeyString);
        } else {
            return createFromHexEncodedString(publicKeyString, privateKeyString);
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
            var keyFactory = KeyFactory.getInstance("EC");
            var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            var pub = keyFactory.generatePublic(publicKeySpec);

            var privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            var pri = keyFactory.generatePrivate(privateKeySpec);

            return new SimpleECKeyPair((ECPublicKey) pub, (ECPrivateKey) pri);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
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
        return new SimpleECKeyPair(
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
        return new SimpleECKeyPair(
                (ECPublicKey) publicKey,
                (ECPrivateKey) privateKey
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    private record SimpleECKeyPair(ECPublicKey publicKey, ECPrivateKey privateKey) implements ECKeyPair {
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
