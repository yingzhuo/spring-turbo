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
 * @author 应卓
 * @since 1.0.0
 */
public final class StringUtils {

    /**
     * 私有构造方法
     */
    private StringUtils() {
        super();
    }

    public static String repeat(String string, int n) {
        Asserts.notNull(string);
        StringBuilder builder = new StringBuilder();
        while (n-- != 0) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static boolean isNull(@Nullable String string) {
        return string == null;
    }

    public static boolean isNotNull(@Nullable String string) {
        return string != null;
    }

    public static boolean isEmpty(@Nullable String string) {
        return isNull(string) || EMPTY.equals(string);
    }

    public static boolean isNotEmpty(@Nullable String string) {
        return !isEmpty(string);
    }

    public static boolean isBlank(@Nullable String string) {
        if (isNull(string) || EMPTY.equals(string)) {
            return true;
        }

        int strLen = string.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(@Nullable String string) {
        return !isBlank(string);
    }

    public static int size(@Nullable String string) {
        return isNull(string) ? 0 : string.length();
    }

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

    public static boolean containsAnyChars(String string, String charsToCheck) {
        Asserts.notNull(string);

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return false;
        }
        return toCharStream(charsToCheck).anyMatch(charSet::contains);
    }

    public static boolean containsAllChars(String string, String charsToCheck) {
        Asserts.notNull(string);

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return false;
        }
        return toCharStream(charsToCheck).allMatch(charSet::contains);
    }

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

    public static Stream<Character> toCharStream(@Nullable String string) {
        if (string == null) {
            return Stream.empty();
        }
        return string.chars().mapToObj(ch -> (char) ch);
    }

    public static List<Character> toCharList(@Nullable String string) {
        return toCharStream(string).collect(Collectors.toList());
    }

    public static Set<Character> toCharSet(@Nullable String string) {
        return toCharStream(string).collect(Collectors.toSet());
    }

    @Nullable
    public static String emptyToNull(@Nullable String string) {
        return isEmpty(string) ? null : string;
    }

    @Nullable
    public static String blankToNull(@Nullable String string) {
        return isBlank(string) ? null : string;
    }

    public static String nullToEmpty(@Nullable String string) {
        return string == null ? EMPTY : string;
    }

    public static String reverse(String string) {
        Asserts.notNull(string);
        return new StringBuilder(string).reverse().toString();
    }

    public static String[] commaDelimitedListToStringArray(@Nullable String string) {
        return commaDelimitedListToStringArray(string, false);
    }

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

    public static int length(@Nullable final String string) {
        return string != null ? string.length() : 0;
    }

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

    public static String wrap(final String string, final String wrapWith) {
        Asserts.notNull(string);
        Asserts.notNull(wrapWith);
        return wrapWith.concat(string).concat(wrapWith);
    }

    public static String wrapIfMissing(final String string, final String wrapWith) {
        Asserts.notNull(string);
        Asserts.notNull(wrapWith);

        final boolean wrapStart = !string.startsWith(wrapWith);
        final boolean wrapEnd = !string.endsWith(wrapWith);
        if (!wrapStart && !wrapEnd) {
            return string;
        }

        final StringBuilder builder = new StringBuilder(string.length() + wrapWith.length() + wrapWith.length());
        if (wrapStart) {
            builder.append(wrapWith);
        }
        builder.append(string);
        if (wrapEnd) {
            builder.append(wrapWith);
        }
        return builder.toString();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static void nullSafeAdd(Collection<String> collection, @Nullable String element) {
        CollectionUtils.nullSafeAdd(collection, element);
    }

    public static void nullSafeAddAll(Collection<String> collection, @Nullable String... elements) {
        CollectionUtils.nullSafeAddAll(collection, elements);
    }

    public static void nullSafeAddAll(Collection<String> collection, @Nullable Collection<String> elements) {
        CollectionUtils.nullSafeAddAll(collection, elements);
    }

    public static void emptySafeAdd(Collection<String> collection, @Nullable String element) {
        emptySafeAddAll(collection, element);
    }

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

    public static void blankSafeAdd(Collection<String> collection, @Nullable String element) {
        blankSafeAddAll(collection, element);
    }

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

    public static String nullSafeJoin(@Nullable Iterable<?> iterable, @Nullable String separator) {
        if (iterable == null) {
            return EMPTY;
        }
        return nullSafeJoin(iterable.iterator(), separator);
    }

    public static String nullSafeJoin(@Nullable Iterator<?> iterator, @Nullable String separator) {
        if (iterator == null) {
            return EMPTY;
        }

        if (separator == null) {
            separator = EMPTY;
        }

        if (!iterator.hasNext()) {
            return EMPTY;
        }

        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, EMPTY);
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
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

    public static String nullSafeJoinWithComma(@Nullable Iterable<?> iterable) {
        return nullSafeJoin(iterable, COMMA);
    }

    public static String nullSafeJoinWithComma(@Nullable Iterator<?> iterator) {
        return nullSafeJoin(iterator, COMMA);
    }

    public static boolean startsWith(@Nullable String str, String prefix) {
        Asserts.notNull(prefix);
        if (str == null) return false;
        return str.startsWith(prefix);
    }

    public static boolean endsWith(@Nullable String str, String suffix) {
        Asserts.notNull(suffix);
        if (str == null) return false;
        return str.endsWith(suffix);
    }

    public static boolean startsWithIgnoreCase(@Nullable String str, String prefix) {
        Asserts.notNull(prefix);
        return (str != null && str.length() >= prefix.length() &&
                str.regionMatches(true, 0, prefix, 0, prefix.length()));
    }

    public static boolean endsWithIgnoreCase(@Nullable String str, String suffix) {
        Asserts.notNull(suffix);
        return (str != null && str.length() >= suffix.length() &&
                str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length()));
    }

}
