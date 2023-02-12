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
import spring.turbo.lang.Immutable;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * 区间 (左闭右闭)
 *
 * <p>#ThreadSafe# 如果比较器是线程安全的，那么区间对象也是线程安全的。</p>
 *
 * @param <T> 元素类型
 * @author 应卓
 * @since 2.0.11
 */
@Immutable
@SuppressWarnings({"rawtypes", "unchecked"})
public final class Range<T> implements Serializable {

    private enum ComparableComparator implements Comparator {
        INSTANCE;

        @Override
        public int compare(final Object obj1, final Object obj2) {
            return ((Comparable) obj1).compareTo(obj2);
        }
    }

    public static <T extends Comparable<T>> Range<T> between(T fromInclusive, T toInclusive) {
        return between(fromInclusive, toInclusive, null);
    }

    public static <T> Range<T> between(T fromInclusive, T toInclusive, @Nullable Comparator<T> comparator) {
        return new Range<>(fromInclusive, toInclusive, comparator);
    }

    public static <T extends Comparable<T>> Range<T> is(T element) {
        return between(element, element, null);
    }

    public static <T> Range<T> is(T element, Comparator<T> comparator) {
        return between(element, element, comparator);
    }

    private final Comparator<T> comparator;
    private transient int hashCode;
    private final T maximum;
    private final T minimum;

    /**
     * 构造方法
     *
     * @param element1 区间左值
     * @param element2 区间右值
     * @param comp     比较器
     */
    private Range(T element1, T element2, @Nullable Comparator<T> comp) {
        Asserts.notNull(element1);
        Asserts.notNull(element2);

        this.comparator = Objects.requireNonNullElse(comp, ComparableComparator.INSTANCE);

        if (this.comparator.compare(element1, element2) < 1) {
            this.minimum = element1;
            this.maximum = element2;
        } else {
            this.minimum = element2;
            this.maximum = element1;
        }
    }

    public boolean contains(@Nullable T element) {
        if (element == null) {
            return false;
        }
        return comparator.compare(element, minimum) > -1 && comparator.compare(element, maximum) < 1;
    }

    public boolean containsRange(@Nullable Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return contains(otherRange.minimum)
                && contains(otherRange.maximum);
    }

    public int elementCompareTo(final T element) {
        // Comparable API says throw NPE on null
        Asserts.notNull(element, "element");
        if (isAfter(element)) {
            return -1;
        } else if (isBefore(element)) {
            return 1;
        } else {
            return 0;
        }
    }

    // Element tests
    //--------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        } else {
            @SuppressWarnings("unchecked") // OK because we checked the class above
            final Range<T> range = (Range<T>) obj;
            return minimum.equals(range.minimum) &&
                    maximum.equals(range.maximum);
        }
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public T getMaximum() {
        return maximum;
    }

    public T getMinimum() {
        return minimum;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (hashCode == 0) {
            result = 17;
            result = 37 * result + getClass().hashCode();
            result = 37 * result + minimum.hashCode();
            result = 37 * result + maximum.hashCode();
            hashCode = result;
        }
        return result;
    }

    public Range<T> intersectionWith(Range<T> other) {
        if (!this.isOverlappedBy(other)) {
            throw new IllegalArgumentException(String.format(
                    "Cannot calculate intersection with non-overlapping range %s", other));
        }
        if (this.equals(other)) {
            return this;
        }
        final T min = getComparator().compare(minimum, other.minimum) < 0 ? other.minimum : minimum;
        final T max = getComparator().compare(maximum, other.maximum) < 0 ? maximum : other.maximum;
        return between(min, max, getComparator());
    }

    public boolean isAfter(@Nullable T element) {
        if (element == null) {
            return false;
        }
        return comparator.compare(element, minimum) < 0;
    }

    public boolean isAfterRange(@Nullable Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return isAfter(otherRange.maximum);
    }

    public boolean isBefore(@Nullable T element) {
        if (element == null) {
            return false;
        }
        return comparator.compare(element, maximum) > 0;
    }

    public boolean isBeforeRange(@Nullable Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return isBefore(otherRange.minimum);
    }

    public boolean isEndedBy(@Nullable T element) {
        if (element == null) {
            return false;
        }
        return comparator.compare(element, maximum) == 0;
    }

    public boolean isNaturalOrdering() {
        return comparator == ComparableComparator.INSTANCE;
    }

    public boolean isOverlappedBy(@Nullable Range<T> otherRange) {
        if (otherRange == null) {
            return false;
        }
        return otherRange.contains(minimum)
                || otherRange.contains(maximum)
                || contains(otherRange.minimum);
    }

    public boolean isStartedBy(@Nullable T element) {
        if (element == null) {
            return false;
        }
        return comparator.compare(element, minimum) == 0;
    }
}
