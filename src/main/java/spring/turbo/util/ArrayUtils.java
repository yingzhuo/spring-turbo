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

/**
 * 数组相关工具
 *
 * @author 应卓
 * @see ArrayDefaults
 * @since 1.0.0
 */
public final class ArrayUtils {

    /**
     * 私有构造方法
     */
    private ArrayUtils() {
        super();
    }

    /**
     * 获取数组长度
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 长度或0
     */
    public static <T> int length(@Nullable T[] array) {
        return array != null ? array.length : 0;
    }

    /**
     * 判断数组是否为 {@code null} 或者 长度为0
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 长度为0或数组为 {@code null} 返回 {@code true} 其他情况返回 {@code false}
     */
    public static <T> boolean isNullOrEmpty(@Nullable T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不包含任何元素
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 数组不包含任何元素时返回 {@code true} 否则返回 {@code false}
     */
    public static <T> boolean doseNotContainsAnyElements(@Nullable T[] array) {
        if (isNullOrEmpty(array))
            return true;
        for (T obj : array) {
            if (obj != null)
                return false;
        }
        return true;
    }

    /**
     * 数组转换成{@link ArrayList}
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 结果
     */
    public static <T> List<T> toArrayList(@Nullable T[] array) {
        if (isNullOrEmpty(array))
            return new ArrayList<>(0);
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * 数组转换成{@link ArrayList} (不可变)
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 结果
     */
    public static <T> List<T> toUnmodifiableList(@Nullable T[] array) {
        return Collections.unmodifiableList(toArrayList(array));
    }

    /**
     * 数组转换成{@link HashSet}
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 结果
     */
    public static <T> Set<T> toHashSet(@Nullable T[] array) {
        if (isNullOrEmpty(array))
            return new HashSet<>();
        return new HashSet<>(Arrays.asList(array));
    }

    /**
     * 数组转换成{@link HashSet} (不可变)
     *
     * @param array 数组
     * @param <T>   数组元素类型
     * @return 结果
     */
    public static <T> Set<T> toUnmodifiableSet(@Nullable T[] array) {
        return Collections.unmodifiableSet(toHashSet(array));
    }

    /**
     * 判断字符数组是否包含指定的元素
     *
     * @param array         数组
     * @param elementToFind 要查找的元素
     * @param <T>           数组元素类型
     * @return 结果
     */
    public static <T> boolean contains(@Nullable T[] array, T elementToFind) {
        return toUnmodifiableSet(array).contains(elementToFind);
    }

    @Nullable
    public static <T> T[] emptyToNull(@Nullable T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array;
    }

}
