/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.NonNull;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 应卓
 * @see NumberPair
 * @since 1.0.8
 */
public final class DatePair implements Serializable {

    private final Date left;
    private final Date right;

    public DatePair(@NonNull Date left, @NonNull Date right) {
        Asserts.notNull(left);
        Asserts.notNull(right);
        this.left = left;
        this.right = right;
    }

    public boolean isOrdered() {
        return left.compareTo(right) <= 0;
    }

    @NonNull
    public DatePair toOrdered() {
        if (isOrdered()) {
            return this;
        } else {
            return new DatePair(right, left);
        }
    }

    @NonNull
    public Date getLeft() {
        return left;
    }

    @NonNull
    public Date getRight() {
        return right;
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

    @NonNull
    public Pair<Date, Date> toPair() {
        return Pair.ofNonNull(getLeft(), getRight());
    }

    @NonNull
    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", left, right);
    }

}
