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
package spring.turbo.bean.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
import spring.turbo.util.CollectionUtils;

import java.util.List;

/**
 * @author 应卓
 * @see MessageSource
 * @see MessageSourceAccessor
 * @see spring.turbo.core.MessageUtils
 * @since 1.0.11
 */
class SpringBootAutoConfiguration {

    @Autowired(required = false)
    private List<MessageSource> sources;

    @Bean
    @ConditionalOnMissingBean
    MessageSourceAccessor messageSourceAccessor() {
        if (CollectionUtils.isEmpty(sources)) {
            return new MessageSourceAccessor(new NoResourceBundleMessageSource());
        } else {
            return new MessageSourceAccessor(new DelegatingMessageSource(sources.get(0)));
        }
    }

}
