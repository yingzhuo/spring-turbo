/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import spring.turbo.lang.Mutable;
import spring.turbo.util.Asserts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
@Deprecated
public final class ValueObjectHolder<T> implements Iterable<T> {

    private final int batchSize;
    private final ThreadLocal<List<T>> threadLocal;

    public ValueObjectHolder(int batchSize) {
        Asserts.isTrue(batchSize > 0);
        this.batchSize = batchSize;
        this.threadLocal = ThreadLocal.withInitial(() -> new ArrayList<>(batchSize));
    }

    public void add(T valueObject) {
        Asserts.isTrue(isNotFull(), "holder is full");
        this.threadLocal.get().add(valueObject);
    }

    public boolean isFull() {
        return size() >= batchSize;
    }

    public boolean isNotFull() {
        return !isFull();
    }

    public boolean isEmpty() {
        return this.threadLocal.get().isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public int size() {
        return this.threadLocal.get().size();
    }

    public List<T> getAll() {
        return this.threadLocal.get();
    }

    public void clear() {
        this.threadLocal.get().clear();
    }

    @Override
    public Iterator<T> iterator() {
        return getAll().iterator();
    }

}
