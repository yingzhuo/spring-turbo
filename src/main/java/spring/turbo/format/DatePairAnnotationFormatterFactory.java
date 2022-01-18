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
import org.springframework.lang.NonNull;
import spring.turbo.bean.DatePair;

import java.util.Collections;
import java.util.Set;

/**
 * @author 应卓
 * @see DatePairParser
 * @since 1.0.8
 */
public class DatePairAnnotationFormatterFactory implements AnnotationFormatterFactory<DatePairFormat> {

    @NonNull
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(DatePair.class);
    }

    @NonNull
    @Override
    public Printer<?> getPrinter(@NonNull DatePairFormat annotation, @NonNull Class<?> fieldType) {
        return ToStringPrint.getInstance();
    }

    @NonNull
    @Override
    public Parser<?> getParser(@NonNull DatePairFormat annotation, @NonNull Class<?> fieldType) {
        return new DatePairParser(annotation.delimiter(), annotation.pattern());
    }

}
