/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.Nullable;
import spring.turbo.format.*;

/**
 * @author 应卓
 * @since 1.3.0
 */
@AutoConfiguration
public class FormatterEditingAutoConfiguration {

    @Autowired(required = false)
    public FormatterEditingAutoConfiguration(@Nullable FormatterRegistry registry) {
        if (registry != null) {
            registry.addConverter(new StringToDateConverter());
            registry.addConverter(new ResourceOptionConverter());
            registry.addConverter(new StringToBooleanConverter());
            registry.addConverter(new StringToNumberConverter());
            registry.addConverter(new StringToNumberPairConverter());

            registry.addConverter(new DateRangeParser());
            registry.addFormatterForFieldAnnotation(new DateRangeAnnotationFormatterFactory());

            registry.addConverter(new NumberZonesParser());
            registry.addFormatterForFieldAnnotation(new NumberZonesAnnotationFormatterFactory());

            registry.addConverterFactory(new StringToEnumConverterFactory());
        }
    }

}
