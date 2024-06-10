/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import spring.turbo.format.StringToBooleanConverter;
import spring.turbo.format.StringToDateConverter;
import spring.turbo.format.StringToEnumConverterFactory;
import spring.turbo.format.StringToNumberConverter;

/**
 * @author 应卓
 *
 * @since 1.3.0
 */
@AutoConfiguration
public class FormatterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public StringToBooleanConverter extendedStringToBooleanConverter() {
        return new StringToBooleanConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringToDateConverter extendedStringToDateConverter() {
        return new StringToDateConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringToNumberConverter extendedStringToNumberConverter() {
        return new StringToNumberConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringToEnumConverterFactory extendedStringToEnumConverterFactory() {
        return new StringToEnumConverterFactory();
    }

}
