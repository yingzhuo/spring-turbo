/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.stringtokenizer;

import java.util.Arrays;

/**
 * @author 应卓
 * @since 2.0.2
 */
public abstract class AbstractStringMatcher implements StringMatcher {

    static final class AndStringMatcher extends AbstractStringMatcher {

        private final StringMatcher[] stringMatchers;

        AndStringMatcher(final StringMatcher... stringMatchers) {
            this.stringMatchers = stringMatchers.clone();
        }

        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            int total = 0;
            int curStart = start;
            for (final StringMatcher stringMatcher : stringMatchers) {
                if (stringMatcher != null) {
                    final int len = stringMatcher.isMatch(buffer, curStart, bufferStart, bufferEnd);
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
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            int total = 0;
            int curStart = start;
            for (final StringMatcher stringMatcher : stringMatchers) {
                if (stringMatcher != null) {
                    final int len = stringMatcher.isMatch(buffer, curStart, bufferStart, bufferEnd);
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
            for (final StringMatcher stringMatcher : stringMatchers) {
                if (stringMatcher != null) {
                    total += stringMatcher.size();
                }
            }
            return total;
        }
    }

    static final class CharArrayMatcher extends AbstractStringMatcher {

        private final char[] chars;
        private final String string;

        CharArrayMatcher(final char... chars) {
            this.string = String.valueOf(chars);
            this.chars = chars.clone();
        }

        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            final int len = size();
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
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            final int len = size();
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

        @Override
        public String toString() {
            return super.toString() + "[\"" + string + "\"]";
        }

    }

    static final class CharMatcher extends AbstractStringMatcher {

        private final char ch;

        CharMatcher(final char ch) {
            this.ch = ch;
        }

        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            return ch == buffer[start] ? 1 : 0;
        }

        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            return ch == buffer.charAt(start) ? 1 : 0;
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public String toString() {
            return super.toString() + "['" + ch + "']";
        }
    }

    static final class CharSetMatcher extends AbstractStringMatcher {

        private final char[] chars;

        CharSetMatcher(final char[] chars) {
            this.chars = chars.clone();
            Arrays.sort(this.chars);
        }

        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            return Arrays.binarySearch(chars, buffer[start]) >= 0 ? 1 : 0;
        }

        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            return Arrays.binarySearch(chars, buffer.charAt(start)) >= 0 ? 1 : 0;
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public String toString() {
            return super.toString() + Arrays.toString(chars);
        }

    }

    static final class NoneMatcher extends AbstractStringMatcher {

        NoneMatcher() {
        }

        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            return 0;
        }

        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            return 0;
        }

        @Override
        public int size() {
            return 0;
        }

    }

    static final class TrimMatcher extends AbstractStringMatcher {
        private static final int SPACE_INT = 32;

        TrimMatcher() {
        }

        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            return buffer[start] <= SPACE_INT ? 1 : 0;
        }

        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            return buffer.charAt(start) <= SPACE_INT ? 1 : 0;
        }

        @Override
        public int size() {
            return 1;
        }
    }

}
