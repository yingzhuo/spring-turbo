/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import static spring.turbo.util.CharsetPool.UTF_8;
import static spring.turbo.util.crypto.Base64.decode;
import static spring.turbo.util.crypto.Base64.encode;

/**
 * @author 应卓
 *
 * @see #builder()
 * @see DSAKeys
 *
 * @since 1.0.0
 */
public interface DSA extends Crypto {

    public static DSABuilder builder() {
        return new DSABuilder();
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
