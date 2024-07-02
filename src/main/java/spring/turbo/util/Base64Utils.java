/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.Base64;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * {@link Base64} 相关工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class Base64Utils {

    /**
     * 私有构造方法
     */
    private Base64Utils() {
        super();
    }

    public static byte[] encode(byte[] data) {
        return encode(data, true, true);
    }

    public static byte[] encode(byte[] data, boolean withoutPadding) {
        return encode(data, withoutPadding, true);
    }

    public static byte[] encode(byte[] data, boolean withoutPadding, boolean urlSafe) {
        Base64.Encoder encoder = urlSafe ? Base64.getUrlEncoder() : Base64.getEncoder();
        if (withoutPadding) {
            encoder = encoder.withoutPadding();
        }
        return encoder.encode(data);
    }

    public static String encodeToString(byte[] data) {
        return encodeToString(data, true, true);
    }

    public static String encodeToString(byte[] data, boolean withoutPadding) {
        return encodeToString(data, withoutPadding, true);
    }

    public static String encodeToString(byte[] data, boolean withoutPadding, boolean urlSafe) {
        Base64.Encoder encoder = urlSafe ? Base64.getUrlEncoder() : Base64.getEncoder();
        if (withoutPadding) {
            encoder = encoder.withoutPadding();
        }
        return encoder.encodeToString(data);
    }

    public static byte[] decode(byte[] data) {
        return decode(data, true);
    }

    public static byte[] decode(byte[] data, boolean urlSafe) {
        var decoder = urlSafe ? Base64.getUrlDecoder() : Base64.getDecoder();
        return decoder.decode(data);
    }

    public static byte[] decode(String src) {
        return decode(src, true);
    }

    public static byte[] decode(String src, boolean urlSafe) {
        return decode(src.getBytes(UTF_8), urlSafe);
    }

}
