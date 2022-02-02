/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import static java.nio.charset.StandardCharsets.UTF_8;
import static spring.turbo.util.crypto.Base64.decode;
import static spring.turbo.util.crypto.Base64.encode;

/**
 * @author 应卓
 * @see #builder()
 * @since 1.0.0
 */
public interface RSA extends Crypto {

    public static RSABuilder builder() {
        return new RSABuilder();
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
        return verify(
                data.getBytes(UTF_8),
                decode(sign)
        );
    }

}
