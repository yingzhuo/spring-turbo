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
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

import static spring.turbo.util.Base64Utils.decode;
import static spring.turbo.util.Base64Utils.encode;
import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * DSA签名工具
 *
 * @author 应卓
 * @see #of(KeyPair)
 * @see #of(DSAKeyPair)
 * @see #of(PublicKey, PrivateKey)
 * @since 3.2.2
 */
public interface DSA {

    /**
     * 创建工具实例
     *
     * @param keyPair 密钥对
     * @return 工具实例
     */
    public static DSA of(KeyPair keyPair) {
        return new DSAImpl((DSAPublicKey) keyPair.getPublic(), (DSAPrivateKey) keyPair.getPrivate());
    }

    /**
     * 创建工具实例
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 工具实例
     */
    public static DSA of(PublicKey publicKey, PrivateKey privateKey) {
        return new DSAImpl((DSAPublicKey) publicKey, (DSAPrivateKey) privateKey);
    }

    /**
     * 创建工具实例
     *
     * @param keyPair 密钥对
     * @return 工具实例
     */
    public static DSA of(DSAKeyPair keyPair) {
        return new DSAImpl(keyPair.getJdkPublicKey(), keyPair.getJdkPrivateKey());
    }

    public byte[] sign(byte[] data);

    public default String sign(String data) {
        return encode(sign(data.getBytes(UTF_8)));
    }

    public boolean verify(byte[] data, byte[] sign);

    public default boolean verify(String data, String sign) {
        return verify(data.getBytes(UTF_8), decode(sign));
    }

}
