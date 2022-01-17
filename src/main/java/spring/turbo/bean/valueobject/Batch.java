/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import spring.turbo.util.Asserts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一个批次的数据 (单个线程有效)
 *
 * @param <T> ValueObject类型
 * @author 应卓
 * @see BindingResultBatch
 * @since 1.0.9
 */
public class Batch<T> implements Iterable<T>, Serializable {

    private final int maxSize;
    private final ThreadLocal<List<T>> threadLocal;

    public Batch(final int maxSize) {
        Asserts.isTrue(maxSize > 0);
        this.maxSize = maxSize;
        this.threadLocal = ThreadLocal.withInitial(() -> new ArrayList<>(maxSize));
    }

    public void add(final T element) {
        if (isFull()) {
            throw new IllegalArgumentException("batch is full");
        }
        threadLocal.get().add(element);
    }

    public void addAll(final List<T> elements) {
        if (elements != null) {
            for (T element : elements) {
                add(element);
            }
        }
    }

    public int size() {
        return threadLocal.get().size();
    }

    public boolean isEmpty() {
        return threadLocal.get().isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean isFull() {
        return size() >= maxSize;
    }

    public boolean isNotFull() {
        return !isFull();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void clear() {
        threadLocal.get().clear();
    }

    public Stream<T> stream() {
        return threadLocal.get().stream();
    }

    @Override
    public Iterator<T> iterator() {
        return threadLocal.get().iterator();
    }

}
