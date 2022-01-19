/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class SelfConsistent {

    private SelfConsistent() {
        super();
    }

    public static void assertNotNull(@Nullable Object o, String code) {
        assertNotNull(o, code, (Object[]) null);
    }

    public static void assertNotNull(@Nullable Object o, String code, @Nullable Object... args) {
        if (o == null) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static <T> void assertNotEmpty(@Nullable Collection<T> o, String code) {
        assertNotEmpty(o, code, (Object[]) null);
    }

    public static <T> void assertNotEmpty(@Nullable Collection<T> o, String code, @Nullable Object... args) {
        if (CollectionUtils.isEmpty(o)) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static <K, V> void assertNotEmpty(@Nullable Map<K, V> o, String code) {
        assertNotEmpty(o, code, (Object[]) null);
    }

    public static <K, V> void assertNotEmpty(@Nullable Map<K, V> o, String code, @Nullable Object... args) {
        if (CollectionUtils.isEmpty(o)) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static <T> void assertNoNullElements(@Nullable Collection<T> o, String code) {
        assertNoNullElements(o, code, (Object[]) null);
    }

    public static <T> void assertNoNullElements(@Nullable Collection<T> o, String code, @Nullable Object... args) {
        if (CollectionUtils.isEmpty(o)) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
        for (T it : o) {
            if (it == null) {
                throw new SelfConsistentException(new String[]{code}, args, null);
            }
        }
    }

    public static void assertNotEmpty(@Nullable CharSequence string, String code) {
        assertNotEmpty(string, code, (Object[]) null);
    }

    public static void assertNotEmpty(@Nullable CharSequence string, String code, @Nullable Object... args) {
        if (!StringUtils.hasLength(string)) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertNotBlank(@Nullable CharSequence string, String code) {
        assertNotBlank(string, code, (Object[]) null);
    }

    public static void assertNotBlank(@Nullable CharSequence string, String code, @Nullable Object... args) {
        if (!StringUtils.hasText(string)) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertPositive(@Nullable Number number, String code) {
        assertPositive(number, code, (Object[]) null);
    }

    public static void assertPositive(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() <= 0D) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertPositiveOrZero(@Nullable Number number, String code) {
        assertPositiveOrZero(number, code, (Object[]) null);
    }

    public static void assertPositiveOrZero(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() < 0D) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertZero(@Nullable Number number, String code) {
        assertZero(number, code, (Object[]) null);
    }

    public static void assertZero(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() != 0D) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertNegative(@Nullable Number number, String code) {
        assertNegative(number, code, (Object[]) null);
    }

    public static void assertNegative(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() >= 0D) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertNegativeOrZero(@Nullable Number number, String code) {
        assertNegativeOrZero(number, code, (Object[]) null);
    }

    public static void assertNegativeOrZero(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() > 0D) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertEquals(@Nullable Object o1, @Nullable Object o2, String code) {
        assertEquals(o1, o2, code, (Object[]) null);
    }

    public static void assertEquals(@Nullable Object o1, @Nullable Object o2, String code, @Nullable Object... args) {
        if (!Objects.equals(o1, o2)) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertSameObject(@Nullable Object o1, @Nullable Object o2, String code) {
        assertSameObject(o1, o2, code, (Object[]) null);
    }

    public static void assertSameObject(@Nullable Object o1, @Nullable Object o2, String code, @Nullable Object... args) {
        if (o1 != o2) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertTrue(boolean state, String code) {
        assertTrue(state, code, (Object[]) null);
    }

    public static void assertTrue(boolean state, String code, @Nullable Object... args) {
        if (!state) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

    public static void assertFalse(boolean state, String code) {
        assertFalse(state, code, (Object[]) null);
    }

    public static void assertFalse(boolean state, String code, @Nullable Object... args) {
        if (state) {
            throw new SelfConsistentException(new String[]{code}, args, null);
        }
    }

}
