/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.nio.charset.StandardCharsets;

/**
 * @author 应卓
 * @since 1.0.0
 */
public interface RSACryptor {

    public static RSACryptorBuilder builder() {
        return new RSACryptorBuilder();
    }

    public byte[] encryptByPublicKey(byte[] data);

    public default String encryptByPublicKey(String data) {
        byte[] bytes = encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8));
        return Base64.encode(bytes);
    }

    public byte[] decryptByPrivateKey(byte[] encryptedData);

    public default String decryptByPrivateKey(String encryptedData) {
        byte[] bytes = decryptByPrivateKey(Base64.decode(encryptedData));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public byte[] encryptByPrivateKey(byte[] data);

    public default String encryptByPrivateKey(String data) {
        byte[] bytes = encryptByPrivateKey(data.getBytes(StandardCharsets.UTF_8));
        return Base64.encode(bytes);
    }

    public byte[] decryptByPublicKey(byte[] encryptedData);

    public default String decryptByPublicKey(String encryptedData) {
        byte[] bytes = decryptByPublicKey(Base64.decode(encryptedData));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public byte[] sign(byte[] data);

    public default String sign(String data) {
        return Base64.encode(sign(data.getBytes(StandardCharsets.UTF_8)));
    }

    public boolean verify(byte[] data, byte[] sign);

    public default boolean verify(String data, String sign) {
        return verify(
                data.getBytes(StandardCharsets.UTF_8),
                Base64.decode(sign)
        );
    }

}
