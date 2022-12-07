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
import spring.turbo.util.PredefinedStringMatchers.*;

import static spring.turbo.util.CharPool.*;

/**
 * @author 应卓
 * @since 2.0.2
 */
public final class StringMatcherFactories {

    /**
     * 私有构造方法
     */
    private StringMatcherFactories() {
        super();
    }

    /**
     * 链接多个 {@link StringMatcher}
     *
     * @param matchers 要连接的多个实例
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher andMatcher(@Nullable StringMatcher... matchers) {
        if (matchers == null || matchers.length == 0) {
            return new None();
        }
        if (matchers.length == 1) {
            return matchers[0];
        } else {
            return new And(matchers);
        }
    }

    /**
     * 返回任何字符都不匹配的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher noneMatcher() {
        return new None();
    }

    /**
     * 返回指定单个字符的匹配器
     *
     * @param ch 指定的字符
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher charMatcher(char ch) {
        return new Char(ch);
    }

    /**
     * 返回指定多个字符的匹配器
     *
     * @param chars 指定的多个字符
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher charSetMatcher(@Nullable char... chars) {
        final int len = chars != null ? chars.length : 0;
        if (len == 0) {
            return new None();
        }
        if (len == 1) {
            return new Char(chars[0]);
        }
        return new CharSet(chars);
    }

    /**
     * 返回指定多个字符的匹配器
     *
     * @param chars 指定的多个字符
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher charSetMatcher(@Nullable String chars) {
        if (chars == null) {
            return new None();
        }
        final int len = chars.length();
        if (len == 0) {
            return new None();
        }
        if (len == 1) {
            return new Char(chars.charAt(0));
        }
        return new CharSet(chars.toCharArray());
    }

    /**
     * 匹配逗号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher commaMatcher() {
        return new Char(COMMA);
    }

    /**
     * 匹配单引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher singleQuoteMatcher() {
        return new Char(SINGLE_QUOTE);
    }

    /**
     * 匹配双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher doubleQuoteMatcher() {
        return new Char(DOUBLE_QUOTE);
    }

    /**
     * 匹配单双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher quoteMatcher() {
        return new CharSet("'\"".toCharArray());
    }

    /**
     * 匹配单双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher spaceMatcher() {
        return new Char(SPACE);
    }

    /**
     * 匹配制表符的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher tabMatcher() {
        return new Char(TAB);
    }

    /**
     * 匹配HYPHEN的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher HyphenMatcher() {
        return new Char(HYPHEN);
    }

    /**
     * 匹配白字符的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher whitespaceMatcher() {
        return new Whitespace();
    }

    /**
     * 返回 {@link StringTokenizer} 默认风格的匹配器
     *
     * @return {@link StringMatcher} 实例
     * @see StringTokenizer
     */
    public static StringMatcher splitMatcher() {
        return new CharSet(" \t\n\r\f".toCharArray());
    }

    /**
     * 返回指定字符串的匹配器
     *
     * @param string 字符串
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher stringMatcher(@Nullable char... string) {
        final int length = string != null ? string.length : 0;
        return length == 0 ? new None() : length == 1 ? new Char(string[0]) : new CharArray(string);
    }

    /**
     * 返回指定字符串的匹配器
     *
     * @param string 字符串
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher stringMatcher(final String string) {
        return StringUtils.isEmpty(string) ? new None() : stringMatcher(string.toCharArray());
    }

}
