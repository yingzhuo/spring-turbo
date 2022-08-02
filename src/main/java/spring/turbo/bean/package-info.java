/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
@NonNullApi
@NonNullFields
package spring.turbo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
import org.springframework.lang.Nullable;
import spring.turbo.format.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
@AutoConfiguration
class SpringBootAutoConfiguration {

    @Autowired(required = false)
    public SpringBootAutoConfiguration(@Nullable FormatterRegistry registry) {
        if (registry != null) {
            registry.addConverter(new ResourceOptionConverter());
            registry.addConverter(new StringToBooleanConverter());
            registry.addConverter(new StringToNumberConverter());
            registry.addConverter(new StringToNumberPairConverter());
            registry.addConverter(new StringToDayRangeConverter());
            registry.addConverter(new StringToDatePairConverter());
            registry.addFormatterForFieldAnnotation(new DatePairAnnotationFormatterFactory());
            registry.addConverter(new CharSequenceToDateConverter());
        }
    }

}
