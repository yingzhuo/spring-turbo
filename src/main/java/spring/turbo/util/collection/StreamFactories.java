package spring.turbo.util.collection;

import org.springframework.lang.Nullable;
import spring.turbo.util.collection.iterator.EnumerationIterator;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * {@link Stream} 创建工具
 *
 * @author 应卓
 * @see SetFactories
 * @see ListFactories
 * @since 1.0.13
 */
public final class StreamFactories {

    /**
     * 私有构造方法
     */
    private StreamFactories() {
        super();
    }

    @SafeVarargs
    public static <T> Stream<T> newStream(T... elements) {
        return Stream.of(elements);
    }

    @SafeVarargs
    public static <T> Stream<T> nullSafeNewStream(@Nullable T... elements) {
        return ListFactories.nullSafeNewArrayList(elements).stream();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <T> Stream<T> newStream(@Nullable Iterator<T> iterator) {
        if (iterator == null) {
            return Stream.empty();
        }
        final Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }

    public static <T> Stream<T> newStream(@Nullable Iterator<T> iterator, boolean parallel) {
        if (iterator == null) {
            return Stream.empty();
        }
        final Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, parallel);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <T> Stream<T> newSteam(@Nullable Enumeration<T> enumeration) {
        if (enumeration == null) {
            return Stream.empty();
        }
        final Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(new EnumerationIterator<>(enumeration),
                0);
        return StreamSupport.stream(spliterator, false);
    }

    public static <T> Stream<T> newSteam(@Nullable Enumeration<T> enumeration, boolean parallel) {
        if (enumeration == null) {
            return Stream.empty();
        }
        final Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(new EnumerationIterator<>(enumeration),
                0);
        return StreamSupport.stream(spliterator, parallel);
    }

}
