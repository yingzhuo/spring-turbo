/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.collection;

import spring.turbo.util.Asserts;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author 应卓
 * @see SetFactories
 * @see ListFactories
 * @see StringObjectMap
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

    public static <T> Stream<T> newStream(Iterator<T> iterator) {
        return newStream(iterator, false);
    }

    public static <T> Stream<T> newStream(Iterator<T> iterator, boolean parallel) {
        Asserts.notNull(iterator);
        final Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, parallel);
    }

    @SafeVarargs
    public static <T> Stream<T> nullSafeNewStream(T... elements) {
        return ListFactories.nullSafeNewArrayList(elements).stream();
    }

}
