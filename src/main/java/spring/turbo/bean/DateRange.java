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
import spring.turbo.bean.function.DateRangePartitionor;
import spring.turbo.bean.function.DateRangePartitionorFactories;
import spring.turbo.util.Asserts;
import spring.turbo.util.DateUtils;
import spring.turbo.util.StringFormatter;
import spring.turbo.util.collection.StreamFactories;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

/**
 * 日期范围
 *
 * @author 应卓
 *
 * @see DateUtils
 *
 * @since 1.1.4
 */
public final class DateRange implements Serializable, Iterable<Date> {

    private final DateDescriptor leftInclude;
    private final DateDescriptor rightInclude;

    public DateRange(DateDescriptor leftInclude, DateDescriptor rightInclude) {
        Asserts.notNull(leftInclude);
        Asserts.notNull(rightInclude);
        this.leftInclude = leftInclude;
        this.rightInclude = rightInclude;
    }

    public boolean isEmpty() {
        return leftInclude.after(rightInclude);
    }

    public Date getLeftInclude() {
        return leftInclude.toDate();
    }

    public DateDescriptor getLeftIncludeAsDateDim() {
        return leftInclude;
    }

    public Date getRightInclude() {
        return rightInclude.toDate();
    }

    public DateDescriptor getRightIncludeAsDateDim() {
        return rightInclude;
    }

    @Override
    public Iterator<Date> iterator() {
        return new DayRangeIterator(getLeftInclude(), getRightInclude());
    }

    public Stream<Date> toStream() {
        return StreamFactories.newStream(iterator());
    }

    /**
     * 分区
     *
     * @param partitionor
     *            分区器实例
     *
     * @return 分区结果 (可变集合)
     *
     * @see DateRangePartitionor
     * @see DateRangePartitionorFactories
     */
    public Map<String, List<Date>> partition(DateRangePartitionor partitionor) {
        Asserts.notNull(partitionor);

        final MultiValueMap<String, Date> list = new LinkedMultiValueMap<>();

        for (Date date : this) {
            final String partitionName = partitionor.test(DateDescriptor.of(date));
            if (partitionName != null) {
                list.add(partitionName, date);
            }
        }

        return list;
    }

    public Map<String, Set<Date>> partitionAsSet(DateRangePartitionor partitionor) {
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DateRange dates = (DateRange) o;
        return leftInclude.equals(dates.leftInclude) && rightInclude.equals(dates.rightInclude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftInclude, rightInclude);
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} @@ {}", leftInclude.getDayString(), rightInclude.getDayString());
    }

    public boolean isOrdered() {
        return this.leftInclude.beforeOrSameDate(this.rightInclude);
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
            final Date nextDay = DateUtils.addDays(it, 1);
            it = nextDay;
            return nextDay;
        }
    }

}
