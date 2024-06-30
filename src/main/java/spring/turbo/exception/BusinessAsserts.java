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
import spring.turbo.util.Asserts;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 业务自洽性检查工具
 *
 * @author 应卓
 * @see BusinessException
 * @since 1.0.0
 */
public final class BusinessAsserts {

    /**
     * 私有构造方法
     */
    private BusinessAsserts() {
        super();
    }

    public static void assertNotNull(@Nullable Object o, String message) {
        Asserts.notNull(message);
        if (o == null) {
            throw new BusinessException(message);
        }
    }

    public static void assertNotNull(@Nullable Object o, String code, @Nullable Object... args) {
        if (o == null) {
            throw new BusinessException(code, args);
        }
    }

    public static <T> void assertNotEmpty(@Nullable Collection<T> o, String message) {
        Asserts.notNull(message);
        if (CollectionUtils.isEmpty(o)) {
            throw new BusinessException(message);
        }
    }

    public static <T> void assertNotEmpty(@Nullable Collection<T> o, String code, @Nullable Object... args) {
        if (CollectionUtils.isEmpty(o)) {
            throw new BusinessException(code, args);
        }
    }

    public static <K, V> void assertNotEmpty(@Nullable Map<K, V> o, String message) {
        Asserts.notNull(message);
        if (CollectionUtils.isEmpty(o)) {
            throw new BusinessException(message);
        }
    }

    public static <K, V> void assertNotEmpty(@Nullable Map<K, V> o, String code, @Nullable Object... args) {
        if (CollectionUtils.isEmpty(o)) {
            throw new BusinessException(code, args);
        }
    }

    public static <T> void assertNoNullElements(@Nullable Collection<T> o, String message) {
        Asserts.notNull(message);
        if (CollectionUtils.isEmpty(o)) {
            throw new BusinessException(message);
        }
        for (T it : o) {
            if (it == null) {
                throw new BusinessException(message);
            }
        }
    }

    public static <T> void assertNoNullElements(@Nullable Collection<T> o, String code, @Nullable Object... args) {
        if (CollectionUtils.isEmpty(o)) {
            throw new BusinessException(code, args);
        }
        for (T it : o) {
            if (it == null) {
                throw new BusinessException(code, args);
            }
        }
    }

    public static void assertNotEmpty(@Nullable CharSequence string, String message) {
        Asserts.notNull(message);
        if (!StringUtils.hasLength(string)) {
            throw new BusinessException(message);
        }
    }

    public static void assertNotEmpty(@Nullable CharSequence string, String code, @Nullable Object... args) {
        if (!StringUtils.hasLength(string)) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertNotBlank(@Nullable CharSequence string, String message) {
        Asserts.notNull(message);
        if (!StringUtils.hasText(string)) {
            throw new BusinessException(message);
        }
    }

    public static void assertNotBlank(@Nullable CharSequence string, String code, @Nullable Object... args) {
        if (!StringUtils.hasText(string)) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertPositive(@Nullable Number number, String message) {
        Asserts.notNull(message);
        if (number == null || number.doubleValue() <= 0D) {
            throw new BusinessException(message);
        }
    }

    public static void assertPositive(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() <= 0D) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertPositiveOrZero(@Nullable Number number, String message) {
        Asserts.notNull(message);
        if (number == null || number.doubleValue() < 0D) {
            throw new BusinessException(message);
        }
    }

    public static void assertPositiveOrZero(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() < 0D) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertZero(@Nullable Number number, String message) {
        Asserts.notNull(message);
        if (number == null || number.doubleValue() != 0D) {
            throw new BusinessException(message);
        }
    }

    public static void assertZero(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() != 0D) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertNegative(@Nullable Number number, String message) {
        Asserts.notNull(message);
        if (number == null || number.doubleValue() >= 0D) {
            throw new BusinessException(message);
        }
    }

    public static void assertNegative(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() >= 0D) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertNegativeOrZero(@Nullable Number number, String message) {
        Asserts.notNull(message);
        if (number == null || number.doubleValue() > 0D) {
            throw new BusinessException(message);
        }
    }

    public static void assertNegativeOrZero(@Nullable Number number, String code, @Nullable Object... args) {
        if (number == null || number.doubleValue() > 0D) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertEquals(@Nullable Object o1, @Nullable Object o2, String message) {
        Asserts.notNull(message);
        if (!Objects.equals(o1, o2)) {
            throw new BusinessException(message);
        }
    }

    public static void assertEquals(@Nullable Object o1, @Nullable Object o2, String code, @Nullable Object... args) {
        if (!Objects.equals(o1, o2)) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertSameObject(@Nullable Object o1, @Nullable Object o2, String message) {
        Asserts.notNull(message);
        if (o1 != o2) {
            throw new BusinessException(message);
        }
    }

    public static void assertSameObject(@Nullable Object o1, @Nullable Object o2, String code,
                                        @Nullable Object... args) {
        if (o1 != o2) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertTrue(boolean state, String message) {
        Asserts.notNull(message);
        if (!state) {
            throw new BusinessException(message);
        }
    }

    public static void assertTrue(boolean state, String code, @Nullable Object... args) {
        if (!state) {
            throw new BusinessException(code, args);
        }
    }

    public static void assertFalse(boolean state, String message) {
        Asserts.notNull(message);
        if (state) {
            throw new BusinessException(message);
        }
    }

    public static void assertFalse(boolean state, String code, @Nullable Object... args) {
        if (state) {
            throw new BusinessException(code, args);
        }
    }

}
