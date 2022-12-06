/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.Arrays;

/**
 * 预定义的 {@link StringMatcher} 实现
 *
 * @author 应卓
 * @see StringMatcher
 * @see StringMatcherFactories
 * @since 2.0.2
 */
public final class PredefinedStringMatchers {

    /**
     * 私有构造方法
     */
    private PredefinedStringMatchers() {
        super();
    }

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
            super();
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

    public static final class Trim implements StringMatcher {

        private static final int SPACE_INT = (int) CharPool.SPACE; // 32

        public Trim() {
            super();
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
