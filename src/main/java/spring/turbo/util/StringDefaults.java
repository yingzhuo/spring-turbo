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
 * {@link String} 默认值相关工具
 *
 * @author 应卓
 *
 * @see StringUtils
 *
 * @since 2.1.0
 */
public final class StringDefaults {

    /**
     * 私有构造方法
     */
    private StringDefaults() {
        super();
    }

    public static String nullToDefault(@Nullable String string, String defaultString) {
        return string == null ? defaultString : string;
    }

    public static String emptyToDefault(@Nullable String string, String defaultString) {
        return string == null || string.isEmpty() ? defaultString : string;
    }

    public static String blankToDefault(@Nullable String string, String defaultString) {
        return string == null || string.isBlank() ? defaultString : string;
    }

    public static String nullToEmpty(@Nullable String string) {
        return nullToDefault(string, EMPTY);
    }

    public static String blankToEmpty(@Nullable String string) {
        return blankToDefault(string, EMPTY);
    }

}
