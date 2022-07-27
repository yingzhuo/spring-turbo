/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import spring.turbo.bean.function.DayRangePartitionor;
import spring.turbo.lang.Immutable;
import spring.turbo.util.*;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @see DateUtils
 * @see DatePair
 * @see DayRangeFunctions
 * @since 1.0.13
 */
@Immutable
public final class DayRange implements Serializable, Iterable<Date> {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    private final Date leftInclude;
    private final Date rightInclude;

    public DayRange(String left, String right) {
        this(DEFAULT_DATE_PATTERN, left, right);
    }

    public DayRange(String datePattern, String left, String right) {
        this(DateParseUtils.parse(left, datePattern), DateParseUtils.parse(right, datePattern));
    }

    public DayRange(Date leftInclude, Date rightInclude) {
        Asserts.notNull(leftInclude);
        Asserts.notNull(rightInclude);
        this.leftInclude = DateUtils.truncate(leftInclude, Calendar.DATE);
        this.rightInclude = DateUtils.truncate(rightInclude, Calendar.DATE);

        if (this.leftInclude.after(this.rightInclude)) {
            throw new IllegalArgumentException("left is after right");
        }
    }

    public Date getLeftInclude() {
        return leftInclude;
    }

    public Date getRightInclude() {
        return rightInclude;
    }

    @Override
    public Iterator<Date> iterator() {
        return new DayRangeIterator(leftInclude, rightInclude);
    }

    public Stream<Date> toStream() {
        return StreamFactories.newStream(iterator());
    }

    /**
     * 分区
     *
     * @param partitionor 分区器实例
     * @return 分区结果 (可变集合)
     * @see DayRangePartitionor
     * @see spring.turbo.bean.function.DayRangePartitionorFactories
     */
    public Map<String, List<Date>> partition(DayRangePartitionor partitionor) {
        Asserts.notNull(partitionor);

        final MultiValueMap<String, Date> list = new LinkedMultiValueMap<>();

        for (Date date : this) {
            final String partitionName = partitionor.test(date);
            if (partitionName != null) {
                list.add(partitionName, date);
            }
        }

        return list;
    }

    public Map<String, Set<Date>> partitionAsSet(DayRangePartitionor partitionor) {
        final Map<String, Set<Date>> set = new HashMap<>();
        final Map<String, List<Date>> ps = this.partition(partitionor);
        for (final String partitionName : ps.keySet()) {
            final List<Date> list = ps.get(partitionName);
            set.put(partitionName, new HashSet<>(list));
        }
        return set;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayRange dates = (DayRange) o;
        return leftInclude.equals(dates.leftInclude) && rightInclude.equals(dates.rightInclude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftInclude, rightInclude);
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} @@ {}",
                DateUtils.format(leftInclude, "yyyy-MM-dd"),
                DateUtils.format(rightInclude, "yyyy-MM-dd")
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    public DayRange map(final Function<Date, Date> fnForLeftAndRight) {
        return map(fnForLeftAndRight, fnForLeftAndRight);
    }

    // since 1.1.3
    public DayRange map(final Function<Date, Date> fnForLeft, final Function<Date, Date> fnFoRight) {
        Asserts.notNull(fnForLeft);
        Asserts.notNull(fnFoRight);

        final Date newLeft = fnForLeft.apply(this.getLeftInclude());
        final Date newRight = fnFoRight.apply(this.getRightInclude());
        return new DayRange(newLeft, newRight);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final static class DayRangeIterator implements Iterator<Date> {

        private final Date last;
        private Date it;

        public DayRangeIterator(Date it, Date last) {
            this.it = DateUtils.addDays(it, -1);
            this.last = last;
        }

        @Override
        public boolean hasNext() {
            Date nextDay = DateUtils.addDays(it, 1);
            return nextDay.before(last) || DateUtils.isSameDay(last, nextDay);
        }

        @Override
        public Date next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no such day");
            }
            Date nextDay = DateUtils.addDays(it, 1);
            it = nextDay;
            return nextDay;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
