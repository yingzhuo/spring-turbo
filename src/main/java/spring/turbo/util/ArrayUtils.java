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

import java.util.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ArrayUtils {

    private ArrayUtils() {
        super();
    }

    public static <T> int size(@Nullable T[] array) {
        return isNullOrEmpty(array) ? 0 : array.length;
    }

    public static <T> boolean isNullOrEmpty(@Nullable T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean doseNotContainsAnyElements(@Nullable T[] array) {
        if (isNullOrEmpty(array)) return true;
        for (T obj : array) {
            if (obj != null) return false;
        }
        return true;
    }

    @NonNull
    public static <T> List<T> toArrayList(@Nullable T[] array) {
        if (isNullOrEmpty(array)) return new ArrayList<>(0);
        return new ArrayList<>(Arrays.asList(array));
    }

    @NonNull
    public static <T> List<T> toUnmodifiableList(@Nullable T[] array) {
        return Collections.unmodifiableList(toArrayList(array));
    }

    @NonNull
    public static <T> Set<T> toHashSet(@Nullable T[] array) {
        if (isNullOrEmpty(array)) return new HashSet<>();
        return new HashSet<>(Arrays.asList(array));
    }

    @NonNull
    public static <T> Set<T> toUnmodifiableSet(@Nullable T[] array) {
        return Collections.unmodifiableSet(toHashSet(array));
    }

    @NonNull
    public static <T> boolean contains(@Nullable T[] array, T elementToFind) {
        return toUnmodifiableSet(array).contains(elementToFind);
    }


}
