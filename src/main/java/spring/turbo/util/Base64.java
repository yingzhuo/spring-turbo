/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

/**
 * @author 应卓
 *
 * @since 1.0.0
 */
public final class Base64 {

    private static final byte PAD_DEFAULT = '='; // Allow static access to default

    private static final byte[] DECODE_TABLE = {
            // 0 1 2 3 4 5 6 7 8 9 A B C D E F
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00-0f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10-1f
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, // 20-2f + - /
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, // 30-3f 0-9
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // 40-4f A-O
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, // 50-5f P-Z _
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, // 60-6f a-o
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 // 70-7a p-z
    };

    /**
     * 私有构造方法
     */
    private Base64() {
        super();
    }

    public static byte[] decode(String key) {
        return java.util.Base64.getUrlDecoder().decode(key);
    }

    public static String encode(byte[] key) {
        return new String(java.util.Base64.getUrlEncoder().encode(key));
    }

    public static byte[] toBytes(String key) {
        return decode(key);
    }

    public static String toString(byte[] bytes) {
        return encode(bytes);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static boolean isBase64(byte octet) {
        return octet == PAD_DEFAULT || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1);
    }

    public static boolean isBase64(@Nullable byte[] bytes) {
        if (bytes == null) {
            return false;
        }

        for (byte b : bytes) {
            if (!isBase64(b) && !isWhiteSpace(b)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBase64(@Nullable String string) {
        if (string == null) {
            return false;
        }
        return isBase64(string.getBytes(CharsetPool.UTF_8));
    }

    private static boolean isWhiteSpace(final byte byteToCheck) {
        return switch (byteToCheck) {
        case ' ', '\n', '\r', '\t' -> true;
        default -> false;
        };
    }

}
