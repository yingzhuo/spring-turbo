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
import spring.turbo.util.Asserts;
import spring.turbo.util.DateParseUtils;
import spring.turbo.util.DateUtils;
import spring.turbo.util.StreamFactories;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @see DateUtils
 * @see DatePair
 * @since 1.0.13
 */
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

    public Map<String, List<Date>> partition(DayRangePartitionor partitionor) {
        Asserts.notNull(partitionor);

        final MultiValueMap<String, Date> list = new LinkedMultiValueMap<>();

        for (Date date : this) {
            final String partitionName = partitionor.test(date);
            if (partitionName != null) {
                list.add(partitionName, date);
            }
        }

        return Collections.unmodifiableMap(list);
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

    // -----------------------------------------------------------------------------------------------------------------
    private static class DayRangeIterator implements Iterator<Date> {

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
    }

}
