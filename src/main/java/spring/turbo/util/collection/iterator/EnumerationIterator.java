package spring.turbo.util.collection.iterator;

import org.springframework.util.Assert;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Enumeration包装成Iterator
 *
 * @param <T> 泛型
 * @author 应卓
 * @see IteratorEnumeration
 * @since 1.2.2
 */
public class EnumerationIterator<T> implements Iterator<T> {

    private final Enumeration<T> innerEnumeration;

    public EnumerationIterator(Enumeration<T> enumeration) {
        Assert.notNull(enumeration, "enumeration is required");
        this.innerEnumeration = enumeration;
    }

    public static <T> EnumerationIterator<T> newInstance(Enumeration<T> inner) {
        return new EnumerationIterator<>(inner);
    }

    @Override
    public boolean hasNext() {
        return this.innerEnumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return this.innerEnumeration.nextElement();
    }

}
