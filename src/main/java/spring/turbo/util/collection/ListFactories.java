package spring.turbo.util.collection;

import org.springframework.lang.Nullable;

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
        return Collections.unmodifiableList(newArrayList(elements));
    }

    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... elements) {
        final var list = new ArrayList<T>();
        Collections.addAll(list, elements);
        return list;
    }

    @SafeVarargs
    public static <T> LinkedList<T> newLinkedList(T... elements) {
        return new LinkedList<T>(Arrays.asList(elements));
    }

    @SafeVarargs
    public static <T> Vector<T> newVector(T... elements) {
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
