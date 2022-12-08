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
import spring.turbo.util.CollectionUtils;

import java.util.*;

/**
 * {@link List} 创建工具
 *
 * @author 应卓
 * @see SetFactories
 * @see StreamFactories
 * @since 1.0.9
 */
public final class ListFactories {

    /**
     * 私有构造方法
     */
    private ListFactories() {
        super();
    }

    @SafeVarargs
    public static <T> List<T> newUnmodifiableList(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        return Collections.unmodifiableList(newArrayList(elements));
    }

    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        final var list = new ArrayList<T>();
        Collections.addAll(list, elements);
        return list;
    }

    @SafeVarargs
    public static <T> LinkedList<T> newLinkedList(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        return new LinkedList<T>(Arrays.asList(elements));
    }

    @SafeVarargs
    public static <T> Vector<T> newVector(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        return new Vector<T>(Arrays.asList(elements));
    }

    @SafeVarargs
    public static <T> ArrayList<T> nullSafeNewArrayList(@Nullable T... elements) {
        final var list = new ArrayList<T>();
        CollectionUtils.nullSafeAddAll(list, elements);
        return list;
    }

    @SafeVarargs
    public static <T> LinkedList<T> nullSafeNewLinkedList(@Nullable T... elements) {
        final var list = new LinkedList<T>();
        CollectionUtils.nullSafeAddAll(list, elements);
        return list;
    }

    @SafeVarargs
    public static <T> Vector<T> nullSafeNewVector(@Nullable T... elements) {
        final var list = new Vector<T>();
        CollectionUtils.nullSafeAddAll(list, elements);
        return list;
    }

}
