/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

/**
 * {@link String} 匹配器
 *
 * @author 应卓
 * @see StringMatcherFactories
 * @since 2.0.2
 */
public interface StringMatcher {

    public default StringMatcher andThen(StringMatcher stringMatcher) {
        Asserts.notNull(stringMatcher);
        return StringMatcherFactories.INSTANCE.andMatcher(this, stringMatcher);
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

}
