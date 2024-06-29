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
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static java.nio.charset.StandardCharsets.UTF_8;
import static spring.turbo.util.Base64Utils.decode;
import static spring.turbo.util.Base64Utils.encode;

/**
 * RSA加密与签名工具
 *
 * @author 应卓
 * @see #of(KeyPair)
 * @see #of(RSAKeyPair)
 * @see #of(PublicKey, PrivateKey)
 * @since 3.2.2
 */
public interface RSA {

    /**
     * 创建工具实例
     *
     * @param keyPair 密钥对
     * @return 工具实例
     */
    public static RSA of(KeyPair keyPair) {
        return new RSAImpl((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
    }

    /**
     * 创建工具实例
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 工具实例
     */
    public static RSA of(PublicKey publicKey, PrivateKey privateKey) {
        return new RSAImpl((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
    }

    /**
     * 创建工具实例
     *
     * @param keyPair 密钥对
     * @return 工具实例
     */
    public static RSA of(RSAKeyPair keyPair) {
        return of(keyPair.getJdkKeyPair());
    }

    public byte[] encryptByPublicKey(byte[] data);

    public default String encryptByPublicKey(String data) {
        byte[] bytes = encryptByPublicKey(data.getBytes(UTF_8));
        return encode(bytes);
    }

    public byte[] decryptByPrivateKey(byte[] encryptedData);

    public default String decryptByPrivateKey(String encryptedData) {
        byte[] bytes = decryptByPrivateKey(decode(encryptedData));
        return new String(bytes, UTF_8);
    }

    public byte[] encryptByPrivateKey(byte[] data);

    public default String encryptByPrivateKey(String data) {
        byte[] bytes = encryptByPrivateKey(data.getBytes(UTF_8));
        return encode(bytes);
    }

    public byte[] decryptByPublicKey(byte[] encryptedData);

    public default String decryptByPublicKey(String encryptedData) {
        byte[] bytes = decryptByPublicKey(decode(encryptedData));
        return new String(bytes, UTF_8);
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
