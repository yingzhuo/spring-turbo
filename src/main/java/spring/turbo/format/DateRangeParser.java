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
import org.springframework.format.Parser;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import spring.turbo.bean.DateDescriptor;
import spring.turbo.bean.DateRange;
import spring.turbo.bean.WeekOption;
import spring.turbo.util.DateParseUtils;
import spring.turbo.util.SetFactories;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.1.4
 */
public class DateRangeParser implements Parser<DateRange>, GenericConverter {

    private final DateRangeFormat annotation;

    public DateRangeParser() {
        this(
                new DateRangeFormat() {
                    @Override
                    public String datePattern() {
                        return "yyyy-MM-dd";
                    }

                    @Override
                    public String delimiter() {
                        return " @@ ";
                    }

                    @Override
                    public String timezone() {
                        return "Etc/GMT";
                    }

                    @Override
                    public WeekOption weekOption() {
                        return WeekOption.SUNDAY_START;
                    }

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return DateRangeFormat.class;
                    }
                }
        );
    }

    public DateRangeParser(DateRangeFormat annotation) {
        this.annotation = annotation;
    }

    @Override
    public DateRange parse(String text, Locale locale) throws ParseException {
        final String error = "text is not a valid DateRange.";
        final String[] parts = StringUtils.split(text, annotation.delimiter());
        if (parts == null || parts.length != 2) {
            throw new ParseException(error, 0);
        }

        final String leftDateString = parts[0];
        final String rightDateString = parts[1];

        try {
            final Date left = DateParseUtils.parse(leftDateString, annotation.datePattern());
            final Date right = DateParseUtils.parse(rightDateString, annotation.datePattern());

            return new DateRange(
                    DateDescriptor.of(left, ZoneId.of(annotation.timezone()), annotation.weekOption()),
                    DateDescriptor.of(right, ZoneId.of(annotation.timezone()), annotation.weekOption())
            );
        } catch (Exception e) {
            throw new ParseException(error, 0);
        }
    }

    @Nullable
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return SetFactories.newUnmodifiableSet(
                new ConvertiblePair(String.class, DateRange.class)
        );
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        try {
            return this.parse((String) source, Locale.getDefault());
        } catch (ParseException e) {
            return null;
        }
    }

}
