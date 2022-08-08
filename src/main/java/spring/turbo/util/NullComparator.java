/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * @param <T> 泛型
 * @author 应卓
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class NullComparator<T> implements Comparator<T>, Serializable {

    private final boolean nullGreater;

    @Nullable
    private final Comparator<T> comparator;

    public NullComparator(boolean nullGreater, @Nullable Comparator<? super T> comparator) {
        this.nullGreater = nullGreater;
        this.comparator = (Comparator<T>) comparator;
    }

    @Override
    public int compare(@Nullable T a, @Nullable T b) {
        if (a == b) {
            return 0;
        }
        if (a == null) {
            return nullGreater ? 1 : -1;
        } else if (b == null) {
            return nullGreater ? -1 : 1;
        } else {
            return doCompare(a, b);
        }
    }

    @Override
    public Comparator<T> thenComparing(Comparator<? super T> other) {
        Objects.requireNonNull(other);
        return new NullComparator<>(nullGreater, comparator == null ? other : comparator.thenComparing(other));
    }

    @Override
    public Comparator<T> reversed() {
        return new NullComparator<>((!nullGreater), comparator == null ? null : comparator.reversed());
    }

    /**
     * 不检查{@code null}的比较方法<br>
     * 用户可自行重写此方法自定义比较方式
     *
     * @param a A值
     * @param b B值
     * @return 比较结果，-1:a小于b，0:相等，1:a大于b
     */
    private int doCompare(T a, T b) {
        if (null == comparator) {
            if (a instanceof Comparable && b instanceof Comparable) {
                return ((Comparable) a).compareTo(b);
            }
            return 0;
        }

        return comparator.compare(a, b);
    }
}
