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
 * {@link Set} 创建工具
 *
 * @author 应卓
 * @see ListFactories
 * @see StreamFactories
 * @since 1.0.9
 */
public final class SetFactories {

    /**
     * 私有构造方法
     */
    private SetFactories() {
        super();
    }

    @SafeVarargs
    public static <T> Set<T> newUnmodifiableSet(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        return Collections.unmodifiableSet(newHashSet(elements));
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        final var set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> newLinkedHashSet(T... elements) {
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        final var set = new LinkedHashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> TreeSet<T> newTreeSet(@Nullable T... elements) {
        final var set = new TreeSet<T>(Comparator.naturalOrder());
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T> TreeSet<T> newTreeSet(Comparator<T> comparator, T... elements) {
        Asserts.notNull(comparator);
        Asserts.notNull(elements);
        Asserts.noNullElements(elements);
        final var set = new TreeSet<T>(comparator);
        Collections.addAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> HashSet<T> nullSafeNewHashSet(@Nullable T... elements) {
        final var set = new HashSet<T>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> nullSafeNewLinkedHashSet(@Nullable T... elements) {
        final var set = new LinkedHashSet<T>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> TreeSet<T> nullSafeNewTreeSet(@Nullable T... elements) {
        final var set = new TreeSet<T>(Comparator.naturalOrder());
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> TreeSet<T> nullSafeNewTreeSet(Comparator<T> comparator, @Nullable T... elements) {
        Asserts.notNull(comparator);
        final var set = new TreeSet<T>(comparator);
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

}
