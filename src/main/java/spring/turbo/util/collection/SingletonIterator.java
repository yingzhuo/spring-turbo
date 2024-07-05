package spring.turbo.util.collection;

import org.springframework.lang.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 只有单个元素的迭代器
 *
 * @param <T> 元素类型泛型
 * @author 应卓
 * @since 3.3.1
 */
public class SingletonIterator<T> implements Iterator<T> {

    @Nullable
    private T element;

    public SingletonIterator(@Nullable T element) {
        this.element = element;
    }

    public static <T> SingletonIterator<T> of(@Nullable T element) {
        return new SingletonIterator<>(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return element != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T next() {
        if (element != null) {
            var e = element;
            element = null;
            return e;
        } else {
            throw new NoSuchElementException();
        }
    }

}
