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
 * @see StringObjectMap
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
    public static <T> Set<T> newUnmodifiableSet(@Nullable T... elements) {
        if (elements == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(newHashSet(elements));
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(@Nullable T... elements) {
        final HashSet<T> set = new HashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> newLinkedHashSet(@Nullable T... elements) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> TreeSet<T> newTreeSet(@Nullable T... elements) {
        final TreeSet<T> set = new TreeSet<>(Comparator.naturalOrder());
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T> TreeSet<T> newTreeSet(Comparator<T> comparator, @Nullable T... elements) {
        Asserts.notNull(comparator);
        final TreeSet<T> set = new TreeSet<>(comparator);
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T> HashSet<T> nullSafeNewHashSet(@Nullable T... elements) {
        final HashSet<T> set = new HashSet<>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> nullSafeNewLinkedHashSet(@Nullable T... elements) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> TreeSet<T> nullSafeNewTreeSet(@Nullable T... elements) {
        final TreeSet<T> set = new TreeSet<>(Comparator.naturalOrder());
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> TreeSet<T> nullSafeNewTreeSet(Comparator<T> comparator, @Nullable T... elements) {
        Asserts.notNull(comparator);

        final TreeSet<T> set = new TreeSet<>(comparator);
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

}
