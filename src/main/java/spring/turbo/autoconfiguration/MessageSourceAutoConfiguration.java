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
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.MessageSourceAccessor;
import spring.turbo.message.CompositeMessageSource;

import java.util.List;
import java.util.Locale;

/**
 * @author 应卓
 * @since 2.0.3
 */
@AutoConfiguration
public class MessageSourceAutoConfiguration {

    /**
     * 构造方法
     */
    public MessageSourceAutoConfiguration() {
        super();
    }

    @Bean
    @Primary
    public CompositeMessageSource compositeMessageSource(List<MessageSource> sources) {
        return new CompositeMessageSource(sources.toArray(new MessageSource[0]));
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

}
