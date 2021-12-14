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

import static spring.turbo.util.StringPool.EMPTY;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class StringUtils {

    private StringUtils() {
        super();
    }

    public static String repeat(@Nullable String string, int n) {
        if (string == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        while (n-- != 0) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static boolean isNull(@Nullable String string) {
        return string == null;
    }

    public static boolean isNotNull(@Nullable String string) {
        return string != null;
    }

    public static boolean isEmpty(@Nullable String string) {
        return isNull(string) || EMPTY.equals(string);
    }

    public static boolean isNotEmpty(@Nullable String string) {
        return !isEmpty(string);
    }

    public static boolean isBlank(@Nullable String string) {
        if (isNull(string) || EMPTY.equals(string)) {
            return true;
        }

        int strLen = string.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(@Nullable String string) {
        return !isBlank(string);
    }

    public static boolean containsWhitespace(@Nullable String string) {
        if (string == null) {
            return false;
        }

        int strLen = string.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
