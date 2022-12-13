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

import static spring.turbo.util.CharPool.*;

/**
 * {@link String} 匹配器
 *
 * @author 应卓
 * @since 2.0.2
 */
public interface StringMatcher {

    public default int isMatch(final char[] buffer, final int pos) {
        return isMatch(buffer, pos, 0, buffer.length);
    }

    public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd);

    public default int isMatch(final CharSequence buffer, final int pos) {
        return isMatch(buffer, pos, 0, buffer.length());
    }

    public default int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
        return isMatch(CharSequenceUtils.toCharArray(buffer), start, bufferEnd, bufferEnd);
    }

    public default int size() {
        return 0;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 连接多个 {@link StringMatcher}
     *
     * @param matchers 要连接的多个实例
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher andMatcher(@Nullable StringMatcher... matchers) {
        if (matchers == null || matchers.length == 0) {
            return new PredefinedStringMatchers.None();
        }
        if (matchers.length == 1) {
            return matchers[0];
        } else {
            return new PredefinedStringMatchers.And(matchers);
        }
    }

    /**
     * 返回任何字符都不匹配的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher noneMatcher() {
        return new PredefinedStringMatchers.None();
    }

    /**
     * 返回指定单个字符的匹配器
     *
     * @param ch 指定的字符
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher charMatcher(char ch) {
        return new PredefinedStringMatchers.Char(ch);
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
            return new PredefinedStringMatchers.None();
        }
        if (len == 1) {
            return new PredefinedStringMatchers.Char(chars[0]);
        }
        return new PredefinedStringMatchers.CharSet(chars);
    }

    /**
     * 返回指定多个字符的匹配器
     *
     * @param chars 指定的多个字符
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher charSetMatcher(@Nullable String chars) {
        if (chars == null) {
            return new PredefinedStringMatchers.None();
        }
        final int len = chars.length();
        if (len == 0) {
            return new PredefinedStringMatchers.None();
        }
        if (len == 1) {
            return new PredefinedStringMatchers.Char(chars.charAt(0));
        }
        return new PredefinedStringMatchers.CharSet(chars.toCharArray());
    }

    /**
     * 返回匹配逗号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher commaMatcher() {
        return new PredefinedStringMatchers.Char(COMMA);
    }

    /**
     * 返回匹配单引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher singleQuoteMatcher() {
        return new PredefinedStringMatchers.Char(SINGLE_QUOTE);
    }

    /**
     * 返回匹配双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher doubleQuoteMatcher() {
        return new PredefinedStringMatchers.Char(DOUBLE_QUOTE);
    }

    /**
     * 返回匹配单双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher quoteMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{'\'', '"'});
    }

    /**
     * 匹配单双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher spaceMatcher() {
        return new PredefinedStringMatchers.Char(SPACE);
    }

    /**
     * 返回匹配制表符的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher tabMatcher() {
        return new PredefinedStringMatchers.Char(TAB);
    }

    /**
     * 返回匹配 {@code -} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher hyphenMatcher() {
        return new PredefinedStringMatchers.Char(HYPHEN);
    }

    /**
     * 返回匹配 {@code :} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher colonMatcher() {
        return new PredefinedStringMatchers.Char(COLON);
    }

    /**
     * 返回匹配 {@code ::} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher doubleColonMatcher() {
        return new PredefinedStringMatchers.CharArray("::".toCharArray());
    }

    /**
     * 返回匹配 {@code _} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher underscoreMatcher() {
        return new PredefinedStringMatchers.Char(UNDERSCORE);
    }

    /**
     * 返回匹配 {@code @} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher atSignMatcher() {
        return new PredefinedStringMatchers.Char(AT_SIGN);
    }

    /**
     * 返回匹配 {@code .} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher dotMatcher() {
        return new PredefinedStringMatchers.Char(DOT);
    }

    /**
     * 返回匹配 {@code ;} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher semicolonMatcher() {
        return new PredefinedStringMatchers.Char(SEMICOLON);
    }

    /**
     * 返回匹配 {@code /} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher slashMatcher() {
        return new PredefinedStringMatchers.Char(SLASH);
    }

    /**
     * 返回匹配 {@code \} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher backslashMatcher() {
        return new PredefinedStringMatchers.Char(BACKSLASH);
    }

    /**
     * 返回匹配 {@code /} 和 {@code \} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher slashAndBackslashMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{SLASH, BACKSLASH});
    }

    /**
     * 返回匹配白字符的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher whitespaceMatcher() {
        return new PredefinedStringMatchers.Whitespace();
    }

    /**
     * 返回匹配数字的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher numericMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        });
    }

    /**
     * 返回匹配小写字母的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher lowerMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{
                'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p',
                'q', 'r', 's', 't',
                'u', 'v', 'w', 'x',
                'y', 'z'
        });
    }

    /**
     * 返回匹配大写字母的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher upperMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{
                'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X',
                'Y', 'Z'
        });
    }

    /**
     * 返回匹配英文字母的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher alphaMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{
                'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X',
                'Y', 'Z',
                // ------
                'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p',
                'q', 'r', 's', 't',
                'u', 'v', 'w', 'x',
                'y', 'z'
        });
    }

    /**
     * 返回匹配英文字母和数字的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher alphanumericMatcher() {
        return new PredefinedStringMatchers.CharSet(new char[]{
                'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X',
                'Y', 'Z',
                // ------
                'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p',
                'q', 'r', 's', 't',
                'u', 'v', 'w', 'x',
                'y', 'z',
                // ------
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        });
    }

    /**
     * 返回 {@link StringTokenizer} 默认风格的匹配器
     *
     * @return {@link StringMatcher} 实例
     * @see StringTokenizer
     */
    public static StringMatcher splitMatcher() {
        return new PredefinedStringMatchers.CharSet(" \t\n\r\f".toCharArray());
    }

    /**
     * 返回指定字符串的匹配器
     *
     * @param string 字符串
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher stringMatcher(@Nullable char... string) {
        final int length = string != null ? string.length : 0;
        return length == 0 ? new PredefinedStringMatchers.None() : length == 1 ? new PredefinedStringMatchers.Char(string[0]) : new PredefinedStringMatchers.CharArray(string);
    }

    /**
     * 返回指定字符串的匹配器
     *
     * @param string 字符串
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher stringMatcher(@Nullable String string) {
        if (string == null) {
            return new PredefinedStringMatchers.None();
        } else {
            return string.isEmpty() ? new PredefinedStringMatchers.None() : stringMatcher(string.toCharArray());
        }
    }

}
