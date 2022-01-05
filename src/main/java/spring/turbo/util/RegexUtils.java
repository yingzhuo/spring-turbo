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

import java.util.regex.Pattern;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 正则表达式相关工具
 *
 * @author 应卓
 * @see java.util.regex.Pattern
 * @since 1.0.6
 */
public final class RegexUtils {

    /**
     * 私有构造方法
     */
    private RegexUtils() {
        super();
    }

    /**
     * <p>
     * 移除所有的匹配的子字符串
     * </p>
     *
     * <pre>
     * RegexUtils.removeAll(null, *)      = null
     * RegexUtils.removeAll("any", (Pattern) null)  = "any"
     * RegexUtils.removeAll("any", Pattern.compile(""))    = "any"
     * RegexUtils.removeAll("any", Pattern.compile(".*"))  = ""
     * RegexUtils.removeAll("any", Pattern.compile(".+"))  = ""
     * RegexUtils.removeAll("abc", Pattern.compile(".?"))  = ""
     * RegexUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("&lt;.*&gt;"))      = "A\nB"
     * RegexUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("(?s)&lt;.*&gt;"))  = "AB"
     * RegexUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("&lt;.*&gt;", Pattern.DOTALL))  = "AB"
     * RegexUtils.removeAll("ABCabc123abc", Pattern.compile("[a-z]"))     = "ABC123"
     * </pre>
     *
     * @param text  原始字符串
     * @param regex 正则表达式
     * @return 结果
     * @see #replaceAll(String, Pattern, String)
     * @see java.util.regex.Matcher#replaceAll(String)
     * @see java.util.regex.Pattern
     */
    public static String removeAll(@Nullable final String text, @Nullable final Pattern regex) {
        return replaceAll(text, regex, EMPTY);
    }

    /**
     * <p>
     * 移除所有的匹配的子字符串，这个方法默认不使用 {@link Pattern#DOTALL} 模式
     * </p>
     *
     * <pre>
     * RegexUtils.removeAll(null, *)      = null
     * RegexUtils.removeAll("any", (String) null)  = "any"
     * RegexUtils.removeAll("any", "")    = "any"
     * RegexUtils.removeAll("any", ".*")  = ""
     * RegexUtils.removeAll("any", ".+")  = ""
     * RegexUtils.removeAll("abc", ".?")  = ""
     * RegexUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\nB"
     * RegexUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
     * RegexUtils.removeAll("ABCabc123abc", "[a-z]")     = "ABC123"
     * </pre>
     *
     * @param text  原始字符串
     * @param regex 正则表达式
     * @return 结果
     * @throws java.util.regex.PatternSyntaxException 正则表达式非法
     * @see #replaceAll(String, String, String)
     * @see #removePattern(String, String)
     * @see String#replaceAll(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     */
    public static String removeAll(@Nullable final String text, @Nullable final String regex) {
        return replaceAll(text, regex, EMPTY);
    }

    /**
     * <p>
     * 移除第一个匹配的子字符串
     * </p>
     *
     * <pre>
     * RegexUtils.removeFirst(null, *)      = null
     * RegexUtils.removeFirst("any", (Pattern) null)  = "any"
     * RegexUtils.removeFirst("any", Pattern.compile(""))    = "any"
     * RegexUtils.removeFirst("any", Pattern.compile(".*"))  = ""
     * RegexUtils.removeFirst("any", Pattern.compile(".+"))  = ""
     * RegexUtils.removeFirst("abc", Pattern.compile(".?"))  = "bc"
     * RegexUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("&lt;.*&gt;"))      = "A\n&lt;__&gt;B"
     * RegexUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", Pattern.compile("(?s)&lt;.*&gt;"))  = "AB"
     * RegexUtils.removeFirst("ABCabc123", Pattern.compile("[a-z]"))          = "ABCbc123"
     * RegexUtils.removeFirst("ABCabc123abc", Pattern.compile("[a-z]+"))      = "ABC123abc"
     * </pre>
     *
     * @param text  原始字符串
     * @param regex 正则表达式
     * @return 结果
     * @see #replaceFirst(String, Pattern, String)
     * @see java.util.regex.Matcher#replaceFirst(String)
     * @see java.util.regex.Pattern
     */
    public static String removeFirst(@Nullable final String text, @Nullable final Pattern regex) {
        return replaceFirst(text, regex, EMPTY);
    }

    /**
     * <p>
     * 移除第一个匹配的子字符串，这个方法默认不使用 {@link Pattern#DOTALL} 模式
     * </p>
     *
     * <pre>
     * RegexUtils.removeFirst(null, *)      = null
     * RegexUtils.removeFirst("any", (String) null)  = "any"
     * RegexUtils.removeFirst("any", "")    = "any"
     * RegexUtils.removeFirst("any", ".*")  = ""
     * RegexUtils.removeFirst("any", ".+")  = ""
     * RegexUtils.removeFirst("abc", ".?")  = "bc"
     * RegexUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\n&lt;__&gt;B"
     * RegexUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
     * RegexUtils.removeFirst("ABCabc123", "[a-z]")          = "ABCbc123"
     * RegexUtils.removeFirst("ABCabc123abc", "[a-z]+")      = "ABC123abc"
     * </pre>
     *
     * @param text  原始字符串
     * @param regex 正则表达式
     * @return 结果
     * @throws java.util.regex.PatternSyntaxException 正则表达式非法
     * @see #replaceFirst(String, String, String)
     * @see String#replaceFirst(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     */
    public static String removeFirst(@Nullable final String text, @Nullable final String regex) {
        return replaceFirst(text, regex, EMPTY);
    }

    /**
     * <p>
     * 移除所有匹配的子字符串，这个方法默认使用 {@link Pattern#DOTALL} 模式
     * </p>
     *
     * <pre>
     * RegexUtils.removePattern(null, *)       = null
     * RegexUtils.removePattern("any", (String) null)   = "any"
     * RegexUtils.removePattern("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")  = "AB"
     * RegexUtils.removePattern("ABCabc123", "[a-z]")    = "ABC123"
     * </pre>
     *
     * @param text  原始字符串
     * @param regex 正则表达式
     * @return 结果
     * @throws java.util.regex.PatternSyntaxException 正则表达式非法
     * @see #replacePattern(String, String, String)
     * @see String#replaceAll(String, String)
     * @see Pattern#DOTALL
     */
    public static String removePattern(@Nullable final String text, @Nullable final String regex) {
        return replacePattern(text, regex, EMPTY);
    }

    /**
     * <p>
     * 替换所有的匹配的子字符串
     * </p>
     *
     * <pre>
     * RegexUtils.replaceAll(null, *, *)       = null
     * RegexUtils.replaceAll("any", (Pattern) null, *)   = "any"
     * RegexUtils.replaceAll("any", *, null)   = "any"
     * RegexUtils.replaceAll("", Pattern.compile(""), "zzz")    = "zzz"
     * RegexUtils.replaceAll("", Pattern.compile(".*"), "zzz")  = "zzz"
     * RegexUtils.replaceAll("", Pattern.compile(".+"), "zzz")  = ""
     * RegexUtils.replaceAll("abc", Pattern.compile(""), "ZZ")  = "ZZaZZbZZcZZ"
     * RegexUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("&lt;.*&gt;"), "z")                 = "z\nz"
     * RegexUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("&lt;.*&gt;", Pattern.DOTALL), "z") = "z"
     * RegexUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("(?s)&lt;.*&gt;"), "z")             = "z"
     * RegexUtils.replaceAll("ABCabc123", Pattern.compile("[a-z]"), "_")       = "ABC___123"
     * RegexUtils.replaceAll("ABCabc123", Pattern.compile("[^A-Z0-9]+"), "_")  = "ABC_123"
     * RegexUtils.replaceAll("ABCabc123", Pattern.compile("[^A-Z0-9]+"), "")   = "ABC123"
     * RegexUtils.replaceAll("Lorem ipsum  dolor   sit", Pattern.compile("( +)([a-z]+)"), "_$2")  = "Lorem_ipsum_dolor_sit"
     * </pre>
     *
     * @param text        原始字符串
     * @param regex       正则表达式
     * @param replacement 替换字符串
     * @return 结果
     * @see java.util.regex.Matcher#replaceAll(String)
     * @see java.util.regex.Pattern
     */
    public static String replaceAll(@Nullable final String text, @Nullable final Pattern regex, @Nullable final String replacement) {
        if (ObjectUtils.anyNull(text, regex, replacement)) {
            return text;
        }
        return regex.matcher(text).replaceAll(replacement);
    }

    /**
     * <p>
     * 替换所有的匹配的子字符串，这个方法默认不使用 {@link Pattern#DOTALL} 模式
     * </p>
     *
     * <pre>
     * RegexUtils.replaceAll(null, *, *)       = null
     * RegexUtils.replaceAll("any", (String) null, *)   = "any"
     * RegexUtils.replaceAll("any", *, null)   = "any"
     * RegexUtils.replaceAll("", "", "zzz")    = "zzz"
     * RegexUtils.replaceAll("", ".*", "zzz")  = "zzz"
     * RegexUtils.replaceAll("", ".+", "zzz")  = ""
     * RegexUtils.replaceAll("abc", "", "ZZ")  = "ZZaZZbZZcZZ"
     * RegexUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\nz"
     * RegexUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
     * RegexUtils.replaceAll("ABCabc123", "[a-z]", "_")       = "ABC___123"
     * RegexUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
     * RegexUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
     * RegexUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
     * </pre>
     *
     * @param text        原始字符串
     * @param regex       正则表达式
     * @param replacement 替换字符串
     * @return 结果
     * @throws java.util.regex.PatternSyntaxException 正则表达式非法
     * @see #replacePattern(String, String, String)
     * @see String#replaceAll(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     */
    public static String replaceAll(@Nullable final String text, @Nullable final String regex, @Nullable final String replacement) {
        if (ObjectUtils.anyNull(text, regex, replacement)) {
            return text;
        }
        return text.replaceAll(regex, replacement);
    }

    /**
     * <p>
     * 替换第一个匹配的子字符串
     * </p>
     *
     * <pre>
     * RegexUtils.replaceFirst(null, *, *)       = null
     * RegexUtils.replaceFirst("any", (Pattern) null, *)   = "any"
     * RegexUtils.replaceFirst("any", *, null)   = "any"
     * RegexUtils.replaceFirst("", Pattern.compile(""), "zzz")    = "zzz"
     * RegexUtils.replaceFirst("", Pattern.compile(".*"), "zzz")  = "zzz"
     * RegexUtils.replaceFirst("", Pattern.compile(".+"), "zzz")  = ""
     * RegexUtils.replaceFirst("abc", Pattern.compile(""), "ZZ")  = "ZZabc"
     * RegexUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("&lt;.*&gt;"), "z")      = "z\n&lt;__&gt;"
     * RegexUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", Pattern.compile("(?s)&lt;.*&gt;"), "z")  = "z"
     * RegexUtils.replaceFirst("ABCabc123", Pattern.compile("[a-z]"), "_")          = "ABC_bc123"
     * RegexUtils.replaceFirst("ABCabc123abc", Pattern.compile("[^A-Z0-9]+"), "_")  = "ABC_123abc"
     * RegexUtils.replaceFirst("ABCabc123abc", Pattern.compile("[^A-Z0-9]+"), "")   = "ABC123abc"
     * RegexUtils.replaceFirst("Lorem ipsum  dolor   sit", Pattern.compile("( +)([a-z]+)"), "_$2")  = "Lorem_ipsum  dolor   sit"
     * </pre>
     *
     * @param text        原始字符串
     * @param regex       正则表达式
     * @param replacement 替换字符串
     * @return 结果
     * @see java.util.regex.Matcher#replaceFirst(String)
     * @see java.util.regex.Pattern
     */
    public static String replaceFirst(@Nullable final String text, @Nullable final Pattern regex, @Nullable final String replacement) {
        if (ObjectUtils.anyNull(text, regex, replacement)) {
            return text;
        }
        return regex.matcher(text).replaceFirst(replacement);
    }

    /**
     * <p>
     * 替换第一个匹配的字串，这个方法不使用 {@link Pattern#DOTALL} 模式
     * </p>
     *
     * <pre>
     * RegexUtils.replaceFirst(null, *, *)       = null
     * RegexUtils.replaceFirst("any", (String) null, *)   = "any"
     * RegexUtils.replaceFirst("any", *, null)   = "any"
     * RegexUtils.replaceFirst("", "", "zzz")    = "zzz"
     * RegexUtils.replaceFirst("", ".*", "zzz")  = "zzz"
     * RegexUtils.replaceFirst("", ".+", "zzz")  = ""
     * RegexUtils.replaceFirst("abc", "", "ZZ")  = "ZZabc"
     * RegexUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\n&lt;__&gt;"
     * RegexUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
     * RegexUtils.replaceFirst("ABCabc123", "[a-z]", "_")          = "ABC_bc123"
     * RegexUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_")  = "ABC_123abc"
     * RegexUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "")   = "ABC123abc"
     * RegexUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum  dolor   sit"
     * </pre>
     *
     * @param text        原始字符串
     * @param regex       正则表达式
     * @param replacement 替换字符串
     * @return 结果
     * @throws java.util.regex.PatternSyntaxException 正则表达式非法
     * @see String#replaceFirst(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     */
    public static String replaceFirst(@Nullable final String text, @Nullable final String regex, @Nullable final String replacement) {
        if (ObjectUtils.anyNull(text, regex, replacement)) {
            return text;
        }
        return text.replaceFirst(regex, replacement);
    }

    /**
     * <p>
     * 替换所有的匹配的字串，这个方法使用 {@link Pattern#DOTALL} 模式
     * </p>
     *
     * <pre>
     * RegexUtils.replacePattern(null, *, *)       = null
     * RegexUtils.replacePattern("any", (String) null, *)   = "any"
     * RegexUtils.replacePattern("any", *, null)   = "any"
     * RegexUtils.replacePattern("", "", "zzz")    = "zzz"
     * RegexUtils.replacePattern("", ".*", "zzz")  = "zzz"
     * RegexUtils.replacePattern("", ".+", "zzz")  = ""
     * RegexUtils.replacePattern("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")       = "z"
     * RegexUtils.replacePattern("ABCabc123", "[a-z]", "_")       = "ABC___123"
     * RegexUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
     * RegexUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
     * RegexUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
     * </pre>
     *
     * @param text        原始字符串
     * @param regex       正则表达式
     * @param replacement 替换的字符串
     * @return 结果
     * @throws java.util.regex.PatternSyntaxException 正则表达式非法
     * @see #replaceAll(String, String, String)
     * @see String#replaceAll(String, String)
     * @see Pattern#DOTALL
     */
    public static String replacePattern(@Nullable final String text, @Nullable final String regex, @Nullable final String replacement) {
        if (ObjectUtils.anyNull(text, regex, replacement)) {
            return text;
        }
        return Pattern.compile(regex, Pattern.DOTALL).matcher(text).replaceAll(replacement);
    }

}
