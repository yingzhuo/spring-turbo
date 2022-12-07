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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spring.turbo.util.StringPool.COMMA;
import static spring.turbo.util.StringPool.EMPTY;

/**
 * {@link String} 相关工具
 *
 * @author 应卓
 * @see StringPool
 * @see StringTokenizerUtils
 * @see RandomStringUtils
 * @since 1.0.0
 */
public final class StringUtils {

    /**
     * 私有构造方法
     */
    private StringUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 判断字符串是否为空串
     *
     * @param string 字符串
     * @return 判断结果
     */
    public static boolean isEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    /**
     * 判断字符串是否不为空串
     *
     * @param string 字符串
     * @return 判断结果
     */
    public static boolean isNotEmpty(@Nullable String string) {
        return !isEmpty(string);
    }

    /**
     * 判断字符串是否为空白串
     *
     * @param string 字符串
     * @return 判断结果
     */
    public static boolean isBlank(@Nullable String string) {
//        if (string == null || EMPTY.equals(string)) {
//            return true;
//        }
//
//        int strLen = string.length();
//        for (int i = 0; i < strLen; i++) {
//            if (!Character.isWhitespace(string.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
        return string == null || string.isBlank(); // since java 11
    }

    /**
     * 判断字符串是否不为空白串
     *
     * @param string 字符串
     * @return 判断结果
     */
    public static boolean isNotBlank(@Nullable String string) {
        return !isBlank(string);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 获取字符串的长度
     *
     * @param string 字符串
     * @return 长度
     */
    public static int length(@Nullable final String string) {
        return string == null ? 0 : string.length();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 判断字符串是否包含白字符
     *
     * @param string 字符串
     * @return 判断结果
     */
    public static boolean containsWhitespace(String string) {
        Asserts.notNull(string);

        int strLen = string.length();
        for (int i = 0; i < strLen; i++) {
            if (CharUtils.isWhitespace(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否包含任意一个指定的字符
     *
     * @param string       字符串
     * @param charsToCheck 需要检查的字符集
     * @return 检测结果
     */
    public static boolean containsAnyChars(String string, String charsToCheck) {
        Asserts.notNull(string);

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return false;
        }
        return toCharStream(charsToCheck).anyMatch(charSet::contains);
    }

    /**
     * 判断字符串是否包含所有的指定的字符
     *
     * @param string       字符串
     * @param charsToCheck 需要检查的字符集
     * @return 检测结果
     */
    public static boolean containsAllChars(String string, String charsToCheck) {
        Asserts.notNull(string);

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return false;
        }
        return toCharStream(charsToCheck).allMatch(charSet::contains);
    }

    /**
     * 判断字符串是否不包含任意一个指定的字符
     *
     * @param string       字符串
     * @param charsToCheck 需要检查的字符集
     * @return 检测结果
     */
    public static boolean containsNoneChars(String string, String charsToCheck) {
        Asserts.notNull(string);

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return true;
        }
        return toCharStream(charsToCheck).noneMatch(charSet::contains);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 字符出现个数计数
     *
     * @param string 字符串
     * @param ch     要查找的字符
     * @return 结果
     */
    public static int countMatches(String string, char ch) {
        Asserts.notNull(string);
        if (isEmpty(string)) {
            return 0;
        }
        int count = 0;
        // We could also call str.toCharArray() for faster look ups but that would generate more garbage.
        for (int i = 0; i < string.length(); i++) {
            if (ch == string.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 删除白字符
     *
     * @param string 字符串
     * @return 结果
     */
    public static String deleteWhitespace(String string) {
        Asserts.notNull(string);

        if (string.isEmpty()) {
            return string;
        }

        final int sz = string.length();
        final char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                chs[count++] = string.charAt(i);
            }
        }
        if (count == sz) {
            return string;
        }
        if (count == 0) {
            return EMPTY;
        }
        return new String(chs, 0, count);
    }

    /**
     * 删除字符串中的指定字符
     *
     * @param string        字符串
     * @param charsToDelete 待删除的字符集合
     * @return 结果
     */
    public static String deleteChars(String string, String charsToDelete) {
        Asserts.notNull(string);
        if (isEmpty(charsToDelete)) {
            return string;
        }

        final Set<Character> charsToDeleteSet = toCharSet(charsToDelete);

        final StringBuilder builder = new StringBuilder();
        toCharList(string).stream().filter(c -> !charsToDeleteSet.contains(c)).forEach(builder::append);
        return builder.toString();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 字符串转换为字符{@link Stream}
     *
     * @param string 字符串
     * @return 实例
     */
    public static Stream<Character> toCharStream(@Nullable String string) {
        if (string == null) {
            return Stream.empty();
        }
        return string.chars().mapToObj(ch -> (char) ch);
    }

    /**
     * 字符串转换为字符{@link List}
     *
     * @param string 字符串
     * @return 实例
     */
    public static List<Character> toCharList(@Nullable String string) {
        return toCharStream(string).collect(Collectors.toList());
    }

    /**
     * 字符串转换为字符{@link Set}
     *
     * @param string 字符串
     * @return 实例
     */
    public static Set<Character> toCharSet(@Nullable String string) {
        return toCharStream(string).collect(Collectors.toSet());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 字符串逆向
     *
     * @param string 字符串
     * @return 结果
     */
    public static String reverse(String string) {
        Asserts.notNull(string);
        return new StringBuilder(string).reverse().toString();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 逗号分隔字符串转化成字符串数组
     *
     * @param string 逗号分隔的字符串
     * @return 结果
     */
    public static String[] commaDelimitedListToStringArray(@Nullable String string) {
        return commaDelimitedListToStringArray(string, false);
    }

    /**
     * 逗号分隔字符串转化成字符串数组
     *
     * @param string          逗号分隔的字符串
     * @param trimAllElements 是否trim所有的子字符串
     * @return 结果
     */
    public static String[] commaDelimitedListToStringArray(@Nullable String string, boolean trimAllElements) {
        if (string == null || isBlank(string)) {
            return new String[0];
        }

        final String[] array = string.split(COMMA);
        if (trimAllElements) {
            return Arrays.stream(array).map(String::trim).toArray(String[]::new);
        } else {
            return array;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 首字母大写
     *
     * @param string 字符串
     * @return 结果
     * @see #uncapitalize(String)
     */
    public static String capitalize(final String string) {
        Asserts.notNull(string);

        final int strLen = length(string);
        if (strLen == 0) {
            return string;
        }

        final int firstCodepoint = string.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return string;
        }

        final int[] newCodePoints = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = string.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    /**
     * 取消首字母大写
     *
     * @param string 字符串
     * @return 结果
     * @see #capitalize(String)
     */
    public static String uncapitalize(final String string) {
        Asserts.notNull(string);
        final int strLen = length(string);
        if (strLen == 0) {
            return string;
        }

        final int firstCodepoint = string.codePointAt(0);
        final int newCodePoint = Character.toLowerCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return string;
        }

        final int[] newCodePoints = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = string.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 非{@code null} 字符串加入集合
     *
     * @param collection 集合
     * @param element    字符串
     */
    public static void nullSafeAdd(Collection<String> collection, @Nullable String element) {
        CollectionUtils.nullSafeAdd(collection, element);
    }

    /**
     * 非{@code null} 字符串加入集合
     *
     * @param collection 集合
     * @param elements   字符串(多个)
     */
    public static void nullSafeAddAll(Collection<String> collection, @Nullable String... elements) {
        CollectionUtils.nullSafeAddAll(collection, elements);
    }

    /**
     * 非{@code null} 字符串加入集合
     *
     * @param collection 集合
     * @param elements   字符串(多个)
     */
    public static void nullSafeAddAll(Collection<String> collection, @Nullable Collection<String> elements) {
        CollectionUtils.nullSafeAddAll(collection, elements);
    }

    /**
     * 非空字符串加入集合
     *
     * @param collection 集合
     * @param element    字符串
     */
    public static void emptySafeAdd(Collection<String> collection, @Nullable String element) {
        emptySafeAddAll(collection, element);
    }

    /**
     * 非空字符串加入集合
     *
     * @param collection 集合
     * @param elements   字符串(多个)
     */
    public static void emptySafeAddAll(Collection<String> collection, @Nullable String... elements) {
        Asserts.notNull(collection);
        if (elements != null) {
            for (String element : elements) {
                if (isNotEmpty(element)) {
                    collection.add(element);
                }
            }
        }
    }

    /**
     * 非空字符串加入集合
     *
     * @param collection 集合
     * @param elements   字符串(多个)
     */
    public static void emptySafeAddAll(Collection<String> collection, @Nullable Collection<String> elements) {
        Asserts.notNull(collection);
        if (elements != null) {
            for (String element : elements) {
                if (isNotEmpty(element)) {
                    collection.add(element);
                }
            }
        }
    }

    /**
     * 非空白字符串加入集合
     *
     * @param collection 集合
     * @param element    字符串
     */
    public static void blankSafeAdd(Collection<String> collection, @Nullable String element) {
        blankSafeAddAll(collection, element);
    }

    /**
     * 非空白字符串加入集合
     *
     * @param collection 集合
     * @param elements   字符串(多个)
     */
    public static void blankSafeAddAll(Collection<String> collection, @Nullable String... elements) {
        Asserts.notNull(collection);
        if (elements != null) {
            for (String element : elements) {
                if (isNotBlank(element)) {
                    collection.add(element);
                }
            }
        }
    }

    /**
     * 非空白字符串加入集合
     *
     * @param collection 集合
     * @param elements   字符串(多个)
     */
    public static void blankSafeAddAll(Collection<String> collection, @Nullable Collection<String> elements) {
        Asserts.notNull(collection);
        if (elements != null) {
            for (String element : elements) {
                if (isNotBlank(element)) {
                    collection.add(element);
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 多个元素合成一个字符串
     *
     * @param iterable  多个元素
     * @param separator 分隔符
     * @return 结果
     */
    public static String nullSafeJoin(@Nullable Iterable<?> iterable, @Nullable String separator) {
        if (iterable == null) {
            return EMPTY;
        }
        return nullSafeJoin(iterable.iterator(), separator);
    }

    /**
     * 多个元素合成一个字符串
     *
     * @param iterator  多个元素
     * @param separator 分隔符
     * @return 结果
     */
    public static String nullSafeJoin(@Nullable Iterator<?> iterator, @Nullable String separator) {
        if (iterator == null) {
            return EMPTY;
        }

        separator = separator == null ? EMPTY : separator;

        if (!iterator.hasNext()) {
            return EMPTY;
        }

        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, EMPTY);
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 判断一个字符串是否有指定的前缀
     *
     * @param string 字符串
     * @param prefix 前缀
     * @return 结果
     */
    public static boolean startsWith(@Nullable String string, String prefix) {
        Asserts.notNull(prefix);
        if (string == null) return false;
        return string.startsWith(prefix);
    }

    /**
     * 判断一个字符串是否有指定的后缀
     *
     * @param string 字符串
     * @param suffix 后缀
     * @return 结果
     */
    public static boolean endsWith(@Nullable String string, String suffix) {
        Asserts.notNull(suffix);
        if (string == null) return false;
        return string.endsWith(suffix);
    }

    /**
     * 判断一个字符串是否有指定的前缀 (大小写不敏感)
     *
     * @param string 字符串
     * @param prefix 前缀
     * @return 结果
     */
    public static boolean startsWithIgnoreCase(@Nullable String string, String prefix) {
        Asserts.notNull(prefix);
        return (string != null && string.length() >= prefix.length() &&
                string.regionMatches(true, 0, prefix, 0, prefix.length()));
    }

    /**
     * 判断一个字符串是否有指定的后缀 (大小写不敏感)
     *
     * @param string 字符串
     * @param suffix 后缀
     * @return 结果
     */
    public static boolean endsWithIgnoreCase(@Nullable String string, String suffix) {
        Asserts.notNull(suffix);
        return (string != null && string.length() >= suffix.length() &&
                string.regionMatches(true, string.length() - suffix.length(), suffix, 0, suffix.length()));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 重复字符串
     *
     * @param string 字符串
     * @param n      重复的次数
     * @return 结果
     */
    public static String repeat(String string, int n) {
        Asserts.notNull(string);
        Asserts.isTrue(n >= 1);

        if (n == 1) {
            return string;
        }

        StringBuilder builder = new StringBuilder();
        while (n-- != 0) {
            builder.append(string);
        }
        return builder.toString();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 比较两个字符串
     *
     * @param string1 字符串1
     * @param string2 字符串2
     * @return 结果
     */
    public static int compare(@Nullable String string1, @Nullable String string2) {
        return compare(string1, string2, true);
    }

    /**
     * 比较两个字符串
     *
     * @param string1    字符串1
     * @param string2    字符串2
     * @param nullIsLess 为{@code true}时 {@code null}排在前面
     * @return 结果
     */
    public static int compare(@Nullable String string1, @Nullable String string2, final boolean nullIsLess) {
        if (string1 == string2) {
            return 0;
        }
        if (string1 == null) {
            return nullIsLess ? -1 : 1;
        }
        if (string2 == null) {
            return nullIsLess ? 1 : -1;
        }
        return string1.compareTo(string2);
    }

    /**
     * 比较两个字符串 (大小写不敏感)
     *
     * @param string1 字符串1
     * @param string2 字符串2
     * @return 结果
     */
    public static int compareIgnoreCase(@Nullable String string1, @Nullable String string2) {
        return compareIgnoreCase(string1, string2, true);
    }

    /**
     * 比较两个字符串 (大小写不敏感)
     *
     * @param string1    字符串1
     * @param string2    字符串2
     * @param nullIsLess 为{@code true}时 {@code null}排在前面
     * @return 结果
     */
    public static int compareIgnoreCase(@Nullable String string1, @Nullable String string2, final boolean nullIsLess) {
        if (string1 == string2) {
            return 0;
        }
        if (string1 == null) {
            return nullIsLess ? -1 : 1;
        }
        if (string2 == null) {
            return nullIsLess ? 1 : -1;
        }
        return string1.compareToIgnoreCase(string2);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 查找第一个非Empty字符串
     *
     * @param strings 字符串组
     * @return 结果或 {@code null}
     */
    @Nullable
    public static String firstNonEmpty(@Nullable String... strings) {
        if (strings != null) {
            for (String val : strings) {
                if (isNotEmpty(val)) {
                    return val;
                }
            }
        }
        return null;
    }

    /**
     * 查找第一个非Blank字符串
     *
     * @param values 字符串组
     * @return 结果或 {@code null}
     */
    @Nullable
    public static String firstNonBlank(@Nullable String... values) {
        if (values != null) {
            for (String val : values) {
                if (isNotBlank(val)) {
                    return val;
                }
            }
        }
        return null;
    }

}
