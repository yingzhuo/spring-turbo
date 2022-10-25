/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import spring.turbo.bean.DateRange;
import spring.turbo.util.collection.SetFactories;

import java.util.Set;

/**
 * @author 应卓
 * @since 1.1.4
 */
public class DateRangeAnnotationFormatterFactory implements AnnotationFormatterFactory<DateRangeFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return SetFactories.newUnmodifiableSet(DateRange.class);
    }

    @Override
    public Printer<?> getPrinter(DateRangeFormat annotation, Class<?> fieldType) {
        return ToStringPrint.getInstance();
    }

    @Override
    public Parser<?> getParser(DateRangeFormat annotation, Class<?> fieldType) {
        return new DateRangeParser(annotation);
    }

}
