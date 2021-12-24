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
import org.springframework.util.StringUtils;

import java.util.*;

import static spring.turbo.util.StringFormatter.format;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class SelfConsistentAsserts {

    private SelfConsistentAsserts() {
        super();
    }

    public static <T> void notNull(@Nullable T obj) {
        notNull(obj, null);
    }

    public static <T> void notNull(@Nullable T obj, @Nullable String message, Object... params) {
        if (obj == null) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void equals(@Nullable T obj1, @Nullable T obj2) {
        equals(obj1, obj2, null);
    }

    public static <T> void equals(@Nullable T obj1, @Nullable T obj2, String message, Object... params) {
        if (!Objects.equals(obj1, obj2)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void notEquals(@Nullable T obj1, @Nullable T obj2) {
        notEquals(obj1, obj2, null);
    }

    public static <T> void notEquals(@Nullable T obj1, @Nullable T obj2, String message, Object... params) {
        if (Objects.equals(obj1, obj2)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void notEmpty(@Nullable Collection<T> collection) {
        notEmpty(collection, null);
    }

    public static <T> void notEmpty(@Nullable Collection<T> collection, String message, Object... params) {
        notNull(collection, message, params);
        if (collection.isEmpty()) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void notEmpty(@Nullable T[] array) {
        notEmpty(array, null);
    }

    public static <T> void notEmpty(@Nullable T[] array, String message, Object... params) {
        notNull(array, message, params);
        if (array.length == 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map) {
        notEmpty(map, null);
    }

    public static void notEmpty(@Nullable Map<?, ?> map, String message, Object... params) {
        notNull(map, message, params);
        if (map.isEmpty()) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void isPresent(@Nullable Optional<T> optional) {
        isPresent(optional, null);
    }

    public static <T> void isPresent(@Nullable Optional<T> optional, String message, Object... params) {
        notNull(optional, message, params);
        if (!optional.isPresent()) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T extends CharSequence> void notEmpty(@Nullable T string) {
        notEmpty(string, null);
    }

    public static <T extends CharSequence> void notEmpty(@Nullable T string, String message, Object... params) {
        if (StringUtils.hasLength(string)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T extends CharSequence> void notBlank(@Nullable T string) {
        notBlank(string, null);
    }

    public static <T extends CharSequence> void notBlank(@Nullable T string, String message, Object... params) {
        if (StringUtils.hasText(string)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void noNullElement(@Nullable Collection<T> collection) {
        noNullElement(collection, null);
    }

    public static <T> void noNullElement(@Nullable Collection<T> collection, String message, Object... params) {
        notNull(collection, message, params);
        boolean noNull = collection.stream().noneMatch(Objects::isNull);
        if (!noNull) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void noNullElement(@Nullable T[] array) {
        notNull(array, null);
    }

    public static <T> void noNullElement(@Nullable T[] array, String message, Object... params) {
        notNull(array, message, params);
        boolean noNull = Arrays.stream(array).noneMatch(Objects::isNull);
        if (!noNull) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isTrue(boolean state) {
        isTrue(state, null);
    }

    public static void isTrue(boolean state, String message, Object... params) {
        if (!state) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isFalse(boolean state) {
        isFalse(state, null);
    }

    public static void isFalse(boolean state, String message, Object... params) {
        if (state) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isPositive(@Nullable Double number) {
        isPositive(number, null);
    }

    @Deprecated
    public static void isPositive(@Nullable Double number, String message, Object... params) {
        if (number == null || number <= 0.0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isPositive(@Nullable Integer number) {
        isPositive(number, null);
    }

    @Deprecated
    public static void isPositive(@Nullable Integer number, String message, Object... params) {
        if (number == null || number <= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isPositive(@Nullable Long number) {
        isPositive(number, null);
    }

    @Deprecated
    public static void isPositive(@Nullable Long number, String message, Object... params) {
        if (number == null || number <= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isNegative(@Nullable Double number) {
        isNegative(number, null);
    }

    @Deprecated
    public static void isNegative(@Nullable Double number, String message, Object... params) {
        if (number == null || number >= 0.0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isNegative(@Nullable Integer number) {
        isNegative(number, null);
    }

    @Deprecated
    public static void isNegative(@Nullable Integer number, String message, Object... params) {
        if (number == null || number >= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isNegative(@Nullable Long number) {
        isNegative(number, null);
    }

    @Deprecated
    public static void isNegative(@Nullable Long number, String message, Object... params) {
        if (number == null || number >= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isZero(@Nullable Double number) {
        isZero(number, null);
    }

    @Deprecated
    public static void isZero(@Nullable Double number, String message, Object... params) {
        if (number == null || number != 0.0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isZero(@Nullable Integer number) {
        isZero(number, null);
    }

    @Deprecated
    public static void isZero(@Nullable Integer number, String message, Object... params) {
        if (number == null || number != 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isZero(@Nullable Long number) {
        isZero(number, null);
    }

    @Deprecated
    public static void isZero(@Nullable Long number, String message, Object... params) {
        if (number == null || number != 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    @Deprecated
    public static void isInstanceOf(@Nullable Class<?> type, Object obj) {
        isInstanceOf(type, obj, null);
    }

    @Deprecated
    public static void isInstanceOf(@Nullable Class<?> type, Object obj, String message, Object... params) {
        notNull(type, message, params);
        if (!type.isInstance(obj)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isAssignable(@Nullable Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, null);
    }

    public static void isAssignable(@Nullable Class<?> superType, Class<?> subType, String message, Object... params) {
        notNull(superType, message, params);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

}
