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
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import spring.turbo.bean.ValueStack;
import spring.turbo.bean.ValueStackImpl;

/**
 * @author 应卓
 *
 * @since 2.2.4
 */
@AutoConfiguration
@ConditionalOnMissingBean(ValueStack.class)
public class ValueStackAutoConfiguration {

    @Bean(name = { "valueStack", "valueFinder" })
    public ValueStack valueStack(Environment environment, ResourceLoader resourceLoader,
            ConversionService conversionService) {
        return new ValueStackImpl(environment, resourceLoader, conversionService);
    }

}
