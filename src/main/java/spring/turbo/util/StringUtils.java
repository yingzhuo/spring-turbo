/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class StringUtils {

    private StringUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static String repeat(@Nullable String string, int n) {
        if (string == null) {
            return null;
        }
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

    public static boolean containsWhitespace(@Nullable String string) {
        if (string == null) {
            return false;
        }

        int strLen = string.length();
        for (int i = 0; i < strLen; i++) {
            if (CharUtils.isWhitespace(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAnyChars(@Nullable String string, String charsToCheck) {
        if (string == null) {
            return false;
        }

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return false;
        }

        return toCharStream(charsToCheck).anyMatch(charSet::contains);
    }

    public static boolean containsAllChars(@Nullable String string, String charsToCheck) {
        if (string == null) {
            return false;
        }

        final Set<Character> charSet = toCharSet(string);
        if (charSet.isEmpty()) {
            return false;
        }

        return toCharStream(charsToCheck).allMatch(charSet::contains);
    }

    public static String deleteChars(@Nullable String string, String charsToDelete) {
        if (string == null) {
            return null;
        }
        if (isEmpty(charsToDelete)) {
            return string;
        }

        final Set<Character> charsToDeleteSet = toCharSet(charsToDelete);

        final StringBuilder builder = new StringBuilder();
        toCharList(string).stream().filter(c -> !charsToDeleteSet.contains(c)).forEach(builder::append);
        return builder.toString();
    }

    @NonNull
    public static Stream<Character> toCharStream(@Nullable String string) {
        if (string == null) {
            return Stream.empty();
        }
        return string.chars().mapToObj(ch -> (char) ch);
    }

    @NonNull
    public static List<Character> toCharList(@Nullable String string) {
        return toCharStream(string).collect(Collectors.toList());
    }

    @NonNull
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

    @NonNull
    public static String nullToEmpty(@Nullable String string) {
        return string == null ? EMPTY : string;
    }

    @Nullable
    public static String reverse(@Nullable String string) {
        if (isNull(string)) {
            return null;
        } else {
            return new StringBuilder(string).reverse().toString();
        }
    }
}
