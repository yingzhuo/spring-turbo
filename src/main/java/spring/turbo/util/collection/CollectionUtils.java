/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.collection;

import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 集合相关工具
 *
 * @author 应卓
 * @since 1.0.5
 */
public final class CollectionUtils {

    /**
     * 私有构造方法
     */
    private CollectionUtils() {
        super();
    }

    /**
     * 获取collection长度
     *
     * @param collection collection，可为 {@code null}
     * @param <T>        集合的泛型类型
     * @return 长度或0
     */
    public static <T> int size(@Nullable Collection<T> collection) {
        return collection != null ? collection.size() : 0;
    }

    /**
     * 获取map长度
     *
     * @param map map，可为 {@code null}
     * @param <K> KEY类型泛型
     * @param <V> VALUE类型泛型
     * @return 长度或0
     */
    public static <K, V> int size(@Nullable Map<K, V> map) {
        return map != null ? map.size() : 0;
    }

    /**
     * 判断collection是否为空
     *
     * @param collection collection，可为 {@code null}
     * @param <T>        collection的泛型类型
     * @return 空时返回 {@code true} 否则返回 {@code false}
     */
    public static <T> boolean isEmpty(@Nullable Collection<T> collection) {
        return size(collection) == 0;
    }

    /**
     * 判断map是否为空
     *
     * @param map map，可为 {@code null}
     * @param <K> KEY类型泛型
     * @param <V> VALUE类型泛型
     * @return 空时返回 {@code true} 否则返回 {@code false}
     */
    public static <K, V> boolean isEmpty(@Nullable Map<K, V> map) {
        return size(map) == 0;
    }

    /**
     * 判断collection是否不为空
     *
     * @param collection collection，可为 {@code null}
     * @param <T>        collection的泛型类型
     * @return 空时返回 {@code false} ， 否则返回 {@code true}
     */
    public static <T> boolean isNotEmpty(@Nullable Collection<T> collection) {
        return size(collection) != 0;
    }

    /**
     * 判断map是否不为空
     *
     * @param map map，可为 {@code null}
     * @param <K> KEY类型泛型
     * @param <V> VALUE类型泛型
     * @return 空时返回 {@code false} ， 否则返回 {@code true}
     */
    public static <K, V> boolean isNotEmpty(@Nullable Map<K, V> map) {
        return size(map) != 0;
    }

    /**
     * 为集合添加元素，并小心地处理空值
     *
     * @param collection 要加入的集合
     * @param element    待添加的元素
     * @param <T>        集合的泛型类型
     */
    public static <T> void nullSafeAdd(Collection<T> collection, @Nullable T element) {
        Asserts.notNull(collection);
        if (element != null) {
            collection.add(element);
        }
    }

    /**
     * 为集合添加元素，并小心地处理空值
     *
     * @param collection 要加入的集合
     * @param elements   待添加的元素
     * @param <T>        集合的泛型类型
     */
    public static <T> void nullSafeAddAll(Collection<T> collection, @Nullable T[] elements) {
        Asserts.notNull(collection);
        if (elements != null) {
            for (T obj : elements) {
                Optional.ofNullable(obj).ifPresent(collection::add);
            }
        }
    }

    /**
     * 为集合添加元素，并小心地处理空值
     *
     * @param collection 要加入的集合
     * @param elements   待添加的元素
     * @param <T>        集合的泛型类型
     */
    public static <T> void nullSafeAddAll(Collection<T> collection, @Nullable Collection<T> elements) {
        Asserts.notNull(collection);
        if (elements != null) {
            for (T obj : elements) {
                Optional.ofNullable(obj).ifPresent(collection::add);
            }
        }
    }

    /**
     * 为映射添加元素，并小心地处理空值
     *
     * @param map      要加入的映射
     * @param elements 待添加的元素
     * @param <K>      集合的泛型类型 key
     * @param <V>      集合的泛型类型 vakue
     */
    public static <K, V> void nullSafeAddAll(Map<K, V> map, @Nullable Map<K, V> elements) {
        Asserts.notNull(map);
        if (elements != null) {
            map.putAll(elements);
        }
    }

}
