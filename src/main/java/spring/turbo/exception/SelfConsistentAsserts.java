/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

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

    public static <T> void notNull(T obj) {
        notNull(obj, null);
    }

    public static <T> void notNull(T obj, String message, Object... params) {
        if (obj == null) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void equals(T obj1, T obj2) {
        equals(obj1, obj2, null);
    }

    public static <T> void equals(T obj1, T obj2, String message, Object... params) {
        if (!Objects.equals(obj1, obj2)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void notEquals(T obj1, T obj2) {
        notEquals(obj1, obj2, null);
    }

    public static <T> void notEquals(T obj1, T obj2, String message, Object... params) {
        if (Objects.equals(obj1, obj2)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void notEmpty(Collection<T> collection) {
        notEmpty(collection, null);
    }

    public static <T> void notEmpty(Collection<T> collection, String message, Object... params) {
        notNull(collection, message, params);
        if (collection.isEmpty()) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void notEmpty(T[] array) {
        notEmpty(array, null);
    }

    public static <T> void notEmpty(T[] array, String message, Object... params) {
        notNull(array, message, params);
        if (array.length == 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, null);
    }

    public static void notEmpty(Map<?, ?> map, String message, Object... params) {
        notNull(map, message, params);
        if (map.isEmpty()) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void isPresent(Optional<T> optional) {
        isPresent(optional, null);
    }

    public static <T> void isPresent(Optional<T> optional, String message, Object... params) {
        notNull(optional, message, params);
        if (!optional.isPresent()) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T extends CharSequence> void notEmpty(T string) {
        notEmpty(string, null);
    }

    public static <T extends CharSequence> void notEmpty(T string, String message, Object... params) {
        if (StringUtils.hasLength(string)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T extends CharSequence> void notBlank(T string) {
        notBlank(string, null);
    }

    public static <T extends CharSequence> void notBlank(T string, String message, Object... params) {
        if (StringUtils.hasText(string)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void noNullElement(Collection<T> collection) {
        noNullElement(collection, null);
    }

    public static <T> void noNullElement(Collection<T> collection, String message, Object... params) {
        notNull(collection, message, params);
        boolean noNull = collection.stream().noneMatch(Objects::isNull);
        if (!noNull) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static <T> void noNullElement(T[] array) {
        notNull(array, null);
    }

    public static <T> void noNullElement(T[] array, String message, Object... params) {
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

    public static void isPositive(Double number) {
        isPositive(number, null);
    }

    public static void isPositive(Double number, String message, Object... params) {
        if (number == null || number <= 0.0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isPositive(Integer number) {
        isPositive(number, null);
    }

    public static void isPositive(Integer number, String message, Object... params) {
        if (number == null || number <= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isPositive(Long number) {
        isPositive(number, null);
    }

    public static void isPositive(Long number, String message, Object... params) {
        if (number == null || number <= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isNegative(Double number) {
        isNegative(number, null);
    }

    public static void isNegative(Double number, String message, Object... params) {
        if (number == null || number >= 0.0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isNegative(Integer number) {
        isNegative(number, null);
    }

    public static void isNegative(Integer number, String message, Object... params) {
        if (number == null || number >= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isNegative(Long number) {
        isNegative(number, null);
    }

    public static void isNegative(Long number, String message, Object... params) {
        if (number == null || number >= 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isZero(Double number) {
        isZero(number, null);
    }

    public static void isZero(Double number, String message, Object... params) {
        if (number == null || number != 0.0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isZero(Integer number) {
        isZero(number, null);
    }

    public static void isZero(Integer number, String message, Object... params) {
        if (number == null || number != 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isZero(Long number) {
        isZero(number, null);
    }

    public static void isZero(Long number, String message, Object... params) {
        if (number == null || number != 0) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        isInstanceOf(type, obj, null);
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message, Object... params) {
        notNull(type, message, params);
        if (!type.isInstance(obj)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, null);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message, Object... params) {
        notNull(superType, message, params);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new SelfConsistentException(format(message, params));
        }
    }

}
