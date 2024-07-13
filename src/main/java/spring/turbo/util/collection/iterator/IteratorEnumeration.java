package spring.turbo.util.collection.iterator;

import org.springframework.util.Assert;

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

    public IteratorEnumeration(Iterator<T> iterator) {
        Assert.notNull(iterator, "iterator is required");
        this.inner = iterator;
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
