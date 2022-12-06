/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import spring.turbo.lang.Singleton;

/**
 * @author 应卓
 * @since 2.0.2
 */
@Singleton
public final class StringMatcherFactories {

    public static StringMatcherFactories getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * Matches the comma character.
     */
    private static final PredefinedStringMatchers.Char COMMA_MATCHER = new PredefinedStringMatchers.Char(',');

    /**
     * Matches the double quote character.
     */
    private static final PredefinedStringMatchers.Char DOUBLE_QUOTE_MATCHER = new PredefinedStringMatchers.Char(
            '"');
    /**
     * Matches no characters.
     */
    private static final PredefinedStringMatchers.None NONE_MATCHER = new PredefinedStringMatchers.None();

    /**
     * Matches the single or double quote character.
     */
    private static final PredefinedStringMatchers.CharSet QUOTE_MATCHER = new PredefinedStringMatchers.CharSet(
            "'\"".toCharArray());
    /**
     * Matches the double quote character.
     */
    private static final PredefinedStringMatchers.Char SINGLE_QUOTE_MATCHER = new PredefinedStringMatchers.Char(
            '\'');
    /**
     * Matches the space character.
     */
    private static final PredefinedStringMatchers.Char SPACE_MATCHER = new PredefinedStringMatchers.Char(' ');

    /**
     * Matches the same characters as StringTokenizer, namely space, tab, newline, form feed.
     */
    private static final PredefinedStringMatchers.CharSet SPLIT_MATCHER = new PredefinedStringMatchers.CharSet(
            " \t\n\r\f".toCharArray());
    /**
     * Matches the tab character.
     */
    private static final PredefinedStringMatchers.Char TAB_MATCHER = new PredefinedStringMatchers.Char('\t');

    /**
     * Matches the String trim() whitespace characters.
     */
    private static final PredefinedStringMatchers.Trim TRIM_MATCHER = new PredefinedStringMatchers.Trim();

    /**
     * 私有构造方法
     */
    private StringMatcherFactories() {
        super();
    }

    /**
     * Creates a matcher that matches all of the given matchers in order.
     *
     * @param stringMatchers the matcher
     * @return a matcher that matches all of the given matchers in order.
     * @since 1.9
     */
    public StringMatcher andMatcher(final StringMatcher... stringMatchers) {
        final int len = ArrayUtils.size(stringMatchers);
        if (len == 0) {
            return NONE_MATCHER;
        }
        if (len == 1) {
            return stringMatchers[0];
        }
        return new PredefinedStringMatchers.And(stringMatchers);
    }

    /**
     * Constructor that creates a matcher from a character.
     *
     * @param ch the character to match, must not be null
     * @return a new Matcher for the given char
     */
    public StringMatcher charMatcher(final char ch) {
        return new PredefinedStringMatchers.Char(ch);
    }

    /**
     * Constructor that creates a matcher from a set of characters.
     *
     * @param chars the characters to match, null or empty matches nothing
     * @return a new matcher for the given char[]
     */
    public StringMatcher charSetMatcher(final char... chars) {
        final int len = chars != null ? chars.length : 0;
        if (len == 0) {
            return NONE_MATCHER;
        }
        if (len == 1) {
            return new PredefinedStringMatchers.Char(chars[0]);
        }
        return new PredefinedStringMatchers.CharSet(chars);
    }

    /**
     * Creates a matcher from a string representing a set of characters.
     *
     * @param chars the characters to match, null or empty matches nothing
     * @return a new Matcher for the given characters
     */
    public StringMatcher charSetMatcher(final String chars) {
        final int len = StringUtils.length(chars);
        if (len == 0) {
            return NONE_MATCHER;
        }
        if (len == 1) {
            return new PredefinedStringMatchers.Char(chars.charAt(0));
        }
        return new PredefinedStringMatchers.CharSet(chars.toCharArray());
    }

    /**
     * Returns a matcher which matches the comma character.
     *
     * @return a matcher for a comma
     */
    public StringMatcher commaMatcher() {
        return COMMA_MATCHER;
    }

    /**
     * Returns a matcher which matches the double quote character.
     *
     * @return a matcher for a double quote
     */
    public StringMatcher doubleQuoteMatcher() {
        return DOUBLE_QUOTE_MATCHER;
    }

    /**
     * Matches no characters.
     *
     * @return a matcher that matches nothing
     */
    public StringMatcher noneMatcher() {
        return NONE_MATCHER;
    }

    /**
     * Returns a matcher which matches the single or double quote character.
     *
     * @return a matcher for a single or double quote
     */
    public StringMatcher quoteMatcher() {
        return QUOTE_MATCHER;
    }

    /**
     * Returns a matcher which matches the single quote character.
     *
     * @return a matcher for a single quote
     */
    public StringMatcher singleQuoteMatcher() {
        return SINGLE_QUOTE_MATCHER;
    }

    /**
     * Returns a matcher which matches the space character.
     *
     * @return a matcher for a space
     */
    public StringMatcher spaceMatcher() {
        return SPACE_MATCHER;
    }

    /**
     * Matches the same characters as StringTokenizer, namely space, tab, newline and form feed.
     *
     * @return The split matcher
     */
    public StringMatcher splitMatcher() {
        return SPLIT_MATCHER;
    }

    /**
     * Creates a matcher from a string.
     *
     * @param chars the string to match, null or empty matches nothing
     * @return a new Matcher for the given String
     * @since 1.9
     */
    public StringMatcher stringMatcher(final char... chars) {
        final int length = chars != null ? chars.length : 0;
        return length == 0 ? NONE_MATCHER
                : length == 1 ? new PredefinedStringMatchers.Char(chars[0])
                : new PredefinedStringMatchers.CharArray(chars);
    }

    /**
     * Creates a matcher from a string.
     *
     * @param str the string to match, null or empty matches nothing
     * @return a new Matcher for the given String
     */
    public StringMatcher stringMatcher(final String str) {
        return StringUtils.isEmpty(str) ? NONE_MATCHER : stringMatcher(str.toCharArray());
    }

    /**
     * Returns a matcher which matches the tab character.
     *
     * @return a matcher for a tab
     */
    public StringMatcher tabMatcher() {
        return TAB_MATCHER;
    }

    /**
     * Matches the String trim() whitespace characters.
     *
     * @return The trim matcher
     */
    public StringMatcher trimMatcher() {
        return TRIM_MATCHER;
    }


    // 延迟加载
    private static final class SyncAvoid {
        private static final StringMatcherFactories INSTANCE = new StringMatcherFactories();
    }

}
