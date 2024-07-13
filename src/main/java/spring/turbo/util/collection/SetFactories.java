package spring.turbo.util.collection;

import org.springframework.lang.Nullable;

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
        return Collections.unmodifiableSet(newHashSet(elements));
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... elements) {
        final var set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> newLinkedHashSet(T... elements) {
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
        final var set = new TreeSet<T>(comparator);
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

}
