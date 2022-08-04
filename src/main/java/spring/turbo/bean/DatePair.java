/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.format.StringToDatePairConverter;
import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

/**
 * {@link Date}对
 *
 * @author 应卓
 * @see spring.turbo.format.DatePairFormat
 * @see StringToDatePairConverter
 * @see spring.turbo.format.DatePairAnnotationFormatterFactory
 * @see spring.turbo.bean.jsr380.OrderedDatePair
 * @see NumberPair
 * @since 1.0.8
 * @deprecated 请使用 {@link DayRange}
 */
@Immutable
@Deprecated
public final class DatePair implements Iterable<Date>, Serializable {

    public static final DatePair DEFAULT =
            new DatePair(
                    Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.of(2100, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())
            );

    private final Date left;
    private final Date right;

    public DatePair(Date left, Date right) {
        Asserts.notNull(left);
        Asserts.notNull(right);
        this.left = left;
        this.right = right;
    }

    public boolean isOrdered() {
        return left.compareTo(right) <= 0;
    }

    public DatePair toOrdered() {
        if (isOrdered()) {
            return this;
        } else {
            return new DatePair(right, left);
        }
    }

    public Date getLeft() {
        return left;
    }

    public Date getRight() {
        return right;
    }

    public DayRange toDayRange() {
        return new DayRange(left, right);
    }

    // since 1.0.8
    @Override
    public Iterator<Date> iterator() {
        return Arrays.asList(left, right).iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatePair datePair = (DatePair) o;
        return left.equals(datePair.left) && right.equals(datePair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public Pair<Date, Date> toPair() {
        return Pair.ofNonNull(getLeft(), getRight());
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
