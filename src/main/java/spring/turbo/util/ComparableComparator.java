/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.Comparator;

/**
 * @param <E> 泛型
 * @author 应卓
 */
@SuppressWarnings({"rawtypes"})
public class ComparableComparator<E extends Comparable<? super E>> implements Comparator<E> {

    /**
     * 私有构造方法
     */
    private ComparableComparator() {
        super();
    }

    public static ComparableComparator getInstance() {
        return AsyncVoid.INSTANCE;
    }

    @Override
    public int compare(final E obj1, final E obj2) {
        return obj1.compareTo(obj2);
    }

    @Override
    public int hashCode() {
        return ComparableComparator.class.getName().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        return this == object || null != object && object.getClass().equals(this.getClass());
    }

    private static class AsyncVoid {
        public static final ComparableComparator INSTANCE = new ComparableComparator<>();
    }

}
