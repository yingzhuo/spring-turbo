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
 * @since 1.0.0
 */
public final class CharUtils {

    private CharUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static boolean isNull(@Nullable Character ch) {
        return ch == null;
    }

    public static boolean isNotNull(@Nullable Character ch) {
        return ch != null;
    }

    public static boolean isWhitespace(@Nullable Character ch) {
        return ch != null && Character.isWhitespace(ch);
    }

    public static boolean isNotWhitespace(@Nullable Character ch) {
        return !isWhitespace(ch);
    }

    public static boolean isAscii(@Nullable Character ch) {
        if (ch == null) return false;
        return ch < 128;
    }

    public static boolean isAsciiPrintable(@Nullable Character ch) {
        if (ch == null) return false;
        return ch >= 32 && ch < 127;
    }

    public static boolean isAsciiControl(@Nullable Character ch) {
        if (ch == null) return false;
        return ch < 32 || ch == 127;
    }

    public static boolean isAsciiAlpha(@Nullable Character ch) {
        if (ch == null) return false;
        return isAsciiAlphaUpper(ch) || isAsciiAlphaLower(ch);
    }

    public static boolean isAsciiAlphaUpper(@Nullable Character ch) {
        if (ch == null) return false;
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isAsciiAlphaLower(@Nullable Character ch) {
        if (ch == null) return false;
        return ch >= 'a' && ch <= 'z';
    }

    public static boolean isAsciiNumeric(@Nullable Character ch) {
        if (ch == null) return false;
        return ch >= '0' && ch <= '9';
    }

    public static boolean isAsciiAlphanumeric(@Nullable Character ch) {
        if (ch == null) return false;
        return isAsciiAlpha(ch) || isAsciiNumeric(ch);
    }

}
