/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

import static spring.turbo.util.Base64Utils.decode;
import static spring.turbo.util.Base64Utils.encode;
import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * ECDSA签名工具
 *
 * @author 应卓
 * @see #of(KeyPair)
 * @see #of(ECKeyPair)
 * @see #of(PublicKey, PrivateKey)
 * @since 3.2.2
 */
public interface ECDSA {

    /**
     * 创建工具实例
     *
     * @param keyPair 密钥对
     * @return 工具实例
     */
    public static ECDSA of(KeyPair keyPair) {
        return new ECDSAImpl((ECPublicKey) keyPair.getPublic(), (ECPrivateKey) keyPair.getPrivate());
    }

    /**
     * 创建工具实例
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 工具实例
     */
    public static ECDSA of(PublicKey publicKey, PrivateKey privateKey) {
        return new ECDSAImpl((ECPublicKey) publicKey, (ECPrivateKey) privateKey);
    }

    /**
     * 创建工具实例
     *
     * @param keyPair 密钥对
     * @return 工具实例
     */
    public static ECDSA of(ECKeyPair keyPair) {
        return new ECDSAImpl(keyPair.getJdkPublicKey(), keyPair.getJdkPrivateKey());
    }

    /**
     * 签名
     *
     * @param data 要签名的原始数据
     * @return 签名
     */
    public byte[] sign(byte[] data);

    /**
     * 签名
     *
     * @param data 要签名的原始数据
     * @return 签名
     */
    public default String sign(String data) {
        return encode(sign(data.getBytes(UTF_8)));
    }

    /**
     * 校检签名
     *
     * @param data 原始数据
     * @param sign 签名
     * @return 校检结果
     */
    public boolean verify(byte[] data, byte[] sign);

    /**
     * 校检签名
     *
     * @param data 原始数据
     * @param sign 签名
     * @return 校检结果
     */
    public default boolean verify(String data, String sign) {
        return verify(data.getBytes(UTF_8), decode(sign));
    }

}
