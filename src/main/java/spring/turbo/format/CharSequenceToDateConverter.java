/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;
import spring.turbo.util.DateParseUtils;
import spring.turbo.util.DateUtils;
import spring.turbo.util.SetFactories;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.1.3
 */
public class CharSequenceToDateConverter implements GenericConverter {

    @Nullable
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return SetFactories.newUnmodifiableSet(
                new ConvertiblePair(CharSequence.class, Date.class),
                new ConvertiblePair(CharSequence.class, Calendar.class),
                new ConvertiblePair(CharSequence.class, LocalDate.class),
                new ConvertiblePair(CharSequence.class, LocalDateTime.class),
                new ConvertiblePair(CharSequence.class, Year.class),
                new ConvertiblePair(CharSequence.class, Instant.class),
                new ConvertiblePair(CharSequence.class, java.sql.Date.class)
        );
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (source == null) {
            return null;
        }

        final String string = source.toString();
        final Date date;

        try {
            date = DateParseUtils.parseWildly(string);
        } catch (Exception e) {
            return null;
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(Date.class))) {
            return date;
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(Calendar.class))) {
            return DateUtils.toCalendar(date);
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(LocalDate.class))) {
            return DateUtils.toLocalDate(date);
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(LocalDateTime.class))) {
            return DateUtils.toLocalDateTime(date);
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(Year.class))) {
            return DateUtils.toYear(date);
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(YearMonth.class))) {
            return DateUtils.toYearMonth(date);
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(Instant.class))) {
            return DateUtils.toInstant(date);
        }

        if (targetType.isAssignableTo(TypeDescriptor.valueOf(java.sql.Date.class))) {
            return new java.sql.Date(date.getTime());
        }

        return null;
    }

}
