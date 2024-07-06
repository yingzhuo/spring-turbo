package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.util.Arrays;

import static spring.turbo.util.CharPool.*;

/**
 * {@link String} 匹配器
 *
 * @author 应卓
 * @see StringTokenizer
 * @since 2.0.2
 */
public interface StringMatcher {

    /**
     * 连接多个 {@link StringMatcher}
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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 返回匹配逗号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher commaMatcher() {
        return new Char(COMMA);
    }

    /**
     * 返回匹配单引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher singleQuoteMatcher() {
        return new Char(SINGLE_QUOTE);
    }

    /**
     * 返回匹配双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher doubleQuoteMatcher() {
        return new Char(DOUBLE_QUOTE);
    }

    /**
     * 返回匹配单双引号的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher quoteMatcher() {
        return new CharSet(new char[]{'\'', '"'});
    }

    /**
     * 返回匹配空格匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher spaceMatcher() {
        return new Char(SPACE);
    }

    /**
     * 返回匹配制表符的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher tabMatcher() {
        return new Char(TAB);
    }

    /**
     * 返回匹配 {@code -} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher hyphenMatcher() {
        return new Char(HYPHEN);
    }

    /**
     * 返回匹配 {@code :} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher colonMatcher() {
        return new Char(COLON);
    }

    /**
     * 返回匹配 {@code ::} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher doubleColonMatcher() {
        return new CharArray("::".toCharArray());
    }

    /**
     * 返回匹配 {@code _} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher underscoreMatcher() {
        return new Char(UNDERSCORE);
    }

    /**
     * 返回匹配 {@code @} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher atMarkerMatcher() {
        return new Char(AT_SIGN);
    }

    /**
     * 返回匹配 {@code .} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher dotMatcher() {
        return new Char(DOT);
    }

    /**
     * 返回匹配 {@code ;} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher semicolonMatcher() {
        return new Char(SEMICOLON);
    }

    /**
     * 返回匹配 {@code /} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher slashMatcher() {
        return new Char(SLASH);
    }

    /**
     * 返回匹配 {@code \} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher backslashMatcher() {
        return new Char(BACKSLASH);
    }

    /**
     * 返回匹配 {@code /} 和 {@code \} 的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher slashAndBackslashMatcher() {
        return new CharSet(new char[]{SLASH, BACKSLASH});
    }

    /**
     * 返回匹配白字符的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher whitespaceMatcher() {
        return new Whitespace();
    }

    /**
     * 返回匹配数字的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher numericMatcher() {
        return new CharSet(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    }

    /**
     * 返回匹配小写字母的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher lowerMatcher() {
        return new CharSet(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'});
    }

    /**
     * 返回匹配大写字母的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher upperMatcher() {
        return new CharSet(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'});
    }

    /**
     * 返回匹配英文字母的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher alphaMatcher() {
        return new CharSet(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                // ------
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z'});
    }

    /**
     * 返回匹配英文字母和数字的匹配器
     *
     * @return {@link StringMatcher} 实例
     */
    public static StringMatcher alphanumericMatcher() {
        return new CharSet(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                // ------
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z',
                // ------
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
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
    public static StringMatcher stringMatcher(@Nullable String string) {
        if (string == null) {
            return new None();
        } else {
            return string.isEmpty() ? new None() : stringMatcher(string.toCharArray());
        }
    }

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

    public static final class And implements StringMatcher {

        private final StringMatcher[] matchers;

        public And(StringMatcher... matchers) {
            Asserts.notNull(matchers);
            this.matchers = matchers.clone();
        }

        @Override
        public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd) {
            int total = 0;
            int curStart = start;
            for (StringMatcher stringMatcher : matchers) {
                if (stringMatcher != null) {
                    int len = stringMatcher.isMatch(buffer, curStart, bufferStart, bufferEnd);
                    if (len == 0) {
                        return 0;
                    }
                    total += len;
                    curStart += len;
                }
            }
            return total;
        }

        @Override
        public int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
            int total = 0;
            int curStart = start;
            for (StringMatcher stringMatcher : matchers) {
                if (stringMatcher != null) {
                    int len = stringMatcher.isMatch(buffer, curStart, bufferStart, bufferEnd);
                    if (len == 0) {
                        return 0;
                    }
                    total += len;
                    curStart += len;
                }
            }
            return total;
        }

        @Override
        public int size() {
            int total = 0;
            for (StringMatcher stringMatcher : matchers) {
                if (stringMatcher != null) {
                    total += stringMatcher.size();
                }
            }
            return total;
        }
    }

    public static final class CharArray implements StringMatcher {

        private final char[] chars;
        private final String string;

        public CharArray(char... chars) {
            Asserts.notNull(chars);
            this.chars = chars.clone();
            this.string = String.valueOf(chars);
        }

        @Override
        public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd) {
            int len = size();
            if (start + len > bufferEnd) {
                return 0;
            }
            int j = start;
            for (int i = 0; i < len; i++, j++) {
                if (chars[i] != buffer[j]) {
                    return 0;
                }
            }
            return len;
        }

        @Override
        public int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
            int len = size();
            if (start + len > bufferEnd) {
                return 0;
            }
            int j = start;
            for (int i = 0; i < len; i++, j++) {
                if (chars[i] != buffer.charAt(j)) {
                    return 0;
                }
            }
            return len;
        }

        @Override
        public int size() {
            return chars.length;
        }
    }

    public static final class Char implements StringMatcher {

        private final char ch;

        public Char(char ch) {
            this.ch = ch;
        }

        @Override
        public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd) {
            return ch == buffer[start] ? 1 : 0;
        }

        @Override
        public int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
            return ch == buffer.charAt(start) ? 1 : 0;
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static class CharSet implements StringMatcher {

        private final char[] chars;

        public CharSet(char[] chars) {
            Asserts.notNull(chars);
            this.chars = chars.clone();
            Arrays.sort(this.chars);
        }

        @Override
        public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd) {
            return Arrays.binarySearch(chars, buffer[start]) >= 0 ? 1 : 0;
        }

        @Override
        public int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
            return Arrays.binarySearch(chars, buffer.charAt(start)) >= 0 ? 1 : 0;
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static final class None implements StringMatcher {

        public None() {
        }

        @Override
        public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd) {
            return 0;
        }

        @Override
        public int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
            return 0;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    public static final class Whitespace implements StringMatcher {

        private static final int SPACE_INT = (int) CharPool.SPACE; // 32

        public Whitespace() {
        }

        @Override
        public int isMatch(char[] buffer, int start, int bufferStart, int bufferEnd) {
            return buffer[start] <= SPACE_INT ? 1 : 0;
        }

        @Override
        public int isMatch(CharSequence buffer, int start, int bufferStart, int bufferEnd) {
            return buffer.charAt(start) <= SPACE_INT ? 1 : 0;
        }

        @Override
        public int size() {
            return 1;
        }
    }

}
