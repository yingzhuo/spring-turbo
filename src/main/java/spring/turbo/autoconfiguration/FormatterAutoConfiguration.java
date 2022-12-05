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
import spring.turbo.format.*;

/**
 * @author 应卓
 * @since 1.3.0
 */
@AutoConfiguration
public class FormatterAutoConfiguration {

    /**
     * 构造方法
     */
    public FormatterAutoConfiguration() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public DateRangeFormatter defaultDateRangeFormatter() {
        return new DateRangeFormatter();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public NumberZonesFormatter defaultNumberZonesFormatter() {
        return new NumberZonesFormatter();
    }

    @Bean
    @ConditionalOnMissingBean
    public DateZonesFormatter defaultDateZonesFormatter() {
        return new DateZonesFormatter();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    public NumberPairFormatter defaultNumberPairFormatter() {
        return new NumberPairFormatter();
    }

    @Bean
    @ConditionalOnMissingBean
    public LongPairFormatter defaultLongPairFormatter() {
        return new LongPairFormatter();
    }

    @Bean
    @ConditionalOnMissingBean
    public BigIntegerPairFormatter defaultBigIntegerPairFormatter() {
        return new BigIntegerPairFormatter();
    }

    @Bean
    @ConditionalOnMissingBean
    public DoublePairFormatter defaultDoublePairFormatter() {
        return new DoublePairFormatter();
    }

    @Bean
    @ConditionalOnMissingBean
    public BigDecimalPairFormatter defaultBigDecimalPairFormatter() {
        return new BigDecimalPairFormatter();
    }

    // -----------------------------------------------------------------------------------------------------------------

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

    @Bean
    @ConditionalOnMissingBean
    public ResourceOptionConverter resourceOptionConverter() {
        return new ResourceOptionConverter();
    }

}
