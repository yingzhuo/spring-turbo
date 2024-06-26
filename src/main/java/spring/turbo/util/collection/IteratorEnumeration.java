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

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Iterator包装成Enumeration
 *
 * @param <T> 泛型
 * @author 应卓
 * @see EnumerationIterator
 * @since 1.2.2
 */
public class IteratorEnumeration<T> implements Enumeration<T> {

    private final Iterator<T> inner;

    public IteratorEnumeration(Iterator<T> inner) {
        Asserts.notNull(inner);
        this.inner = inner;
    }

    public static <T> IteratorEnumeration<T> newInstance(Iterator<T> inner) {
        return new IteratorEnumeration<>(inner);
    }

    @Override
    public boolean hasMoreElements() {
        return inner.hasNext();
    }

    @Override
    public T nextElement() {
        return inner.next();
    }

}
