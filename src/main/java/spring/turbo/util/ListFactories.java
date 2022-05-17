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
 * @author 应卓
 * @see SetFactories
 * @see StreamFactories
 * @see StringObjectMap
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
    public static <T> List<T> newUnmodifiableList(@Nullable T... elements) {
        if (elements == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(newArrayList(elements));
    }

    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(@Nullable T... elements) {
        final ArrayList<T> list = new ArrayList<>();
        if (elements != null) {
            Collections.addAll(list, elements);
        }
        return list;
    }

    @SafeVarargs
    public static <T> LinkedList<T> newLinkedList(@Nullable T... elements) {
        final LinkedList<T> list = new LinkedList<>();
        if (elements != null) {
            list.addAll(Arrays.asList(elements));
        }
        return list;
    }

    @SafeVarargs
    public static <T> ArrayList<T> nullSafeNewArrayList(@Nullable T... elements) {
        final ArrayList<T> list = new ArrayList<>();
        CollectionUtils.nullSafeAddAll(list, elements);
        return list;
    }

    @SafeVarargs
    public static <T> LinkedList<T> nullSafeNewLinkedList(@Nullable T... elements) {
        final LinkedList<T> list = new LinkedList<>();
        CollectionUtils.nullSafeAddAll(list, elements);
        return list;
    }

}
