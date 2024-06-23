/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import static spring.turbo.util.Base64Utils.decode;
import static spring.turbo.util.Base64Utils.encode;
import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * ECDSA加密和签名工具
 *
 * @author 应卓
 * @see #builder()
 * @see ECDSATextKeyPair
 * @since 3.1.1
 */
public interface ECDSA {

    public static ECDSABuilder builder() {
        return new ECDSABuilder();
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
