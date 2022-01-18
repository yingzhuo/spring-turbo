/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
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

    private static final int DEFAULT_MAX_SIZE = 1000;

    private final int maxSize;
    private final ThreadLocal<List<T>> threadLocal;

    /**
     * 构造方法，默认maxSize为1000
     *
     * @see #Batch()
     */
    public Batch() {
        this(DEFAULT_MAX_SIZE);
    }

    /**
     * 构造方法
     *
     * @param maxSize 一批最大包含的ValueObject数量，必须大于零
     */
    public Batch(final int maxSize) {
        Asserts.isTrue(maxSize > 0);
        this.maxSize = maxSize;
        this.threadLocal = ThreadLocal.withInitial(() -> new ArrayList<>(maxSize));
    }

    public final void add(@Nullable final T element) {
        if (element == null) {
            return;
        }

        if (isFull()) {
            throw new IllegalArgumentException("batch is full");
        }
        threadLocal.get().add(element);
    }

    public final void addAll(@Nullable final List<T> elements) {
        if (elements != null) {
            for (T vo : elements) {
                if (vo != null) {
                    add(vo);
                }
            }
        }
    }

    public final int size() {
        return threadLocal.get().size();
    }

    public final boolean isEmpty() {
        return threadLocal.get().isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final boolean isFull() {
        return size() >= maxSize;
    }

    public final boolean isNotFull() {
        return !isFull();
    }

    public final int getMaxSize() {
        return maxSize;
    }

    public final void clear() {
        threadLocal.get().clear();
    }

    @NonNull
    public final Stream<T> stream() {
        return threadLocal.get().stream();
    }

    @NonNull
    @Override
    public final Iterator<T> iterator() {
        return threadLocal.get().iterator();
    }

}
