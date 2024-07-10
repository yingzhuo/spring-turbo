package spring.turbo.util.collection.iterator;

import org.springframework.lang.Nullable;

import java.util.Comparator;
import java.util.Objects;

/**
 * 对 {@code null} 值友好的比较器
 *
 * @param <T> 泛型
 * @author 应卓
 * @deprecated 使用 {@link Comparator#nullsFirst(Comparator)} 或 {@link Comparator#nullsLast(Comparator)} 替代
 */
@Deprecated
@SuppressWarnings({"rawtypes", "unchecked"})
public class NulSafeComparator<T> implements Comparator<T> {

    private final boolean nullGreater;

    @Nullable
    private final Comparator<T> comparator;

    public NulSafeComparator(boolean nullGreater) {
        this(nullGreater, null);
    }

    public NulSafeComparator(boolean nullGreater, @Nullable Comparator<? super T> comparator) {
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
        return new NulSafeComparator<>(nullGreater, comparator == null ? other : comparator.thenComparing(other));
    }

    @Override
    public Comparator<T> reversed() {
        return new NulSafeComparator<>((!nullGreater), comparator == null ? null : comparator.reversed());
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
