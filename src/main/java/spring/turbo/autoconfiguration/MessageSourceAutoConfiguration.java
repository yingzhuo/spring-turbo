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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import spring.turbo.autoconfiguration.properties.MessageSourceProps;
import spring.turbo.convention.ExtraMessageSourceBasenameConvention;
import spring.turbo.core.SpringFactoriesUtils;
import spring.turbo.util.collection.CollectionUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * {@link MessageSource} 相关自动加载
 *
 * @author 应卓
 *
 * @see MessageSource
 * @see org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
 *
 * @since 2.0.3
 */
@AutoConfiguration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(MessageSourceProps.class)
public class MessageSourceAutoConfiguration {

    /**
     * 默认构造方法
     */
    public MessageSourceAutoConfiguration() {
        super();
    }

    @Bean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource(MessageSourceProps properties) {
        final var bean = new ResourceBundleMessageSource();
        bean.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        bean.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        bean.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        bean.setBasenames(this.mergeBasename(properties));

        if (properties.getEncoding() != null) {
            bean.setDefaultEncoding(properties.getEncoding().name());
        }

        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            bean.setCacheMillis(cacheDuration.toMillis());
        }
        return bean;
    }

    private String[] mergeBasename(MessageSourceProps properties) {
        final var list = new LinkedList<String>();
        CollectionUtils.nullSafeAddAll(list, properties.getBasenameArray());
        CollectionUtils.nullSafeAddAll(list, getExtraBasename());
        return list.toArray(new String[0]);
    }

    public Collection<String> getExtraBasename() {
        final var list = new ArrayList<String>();
        final var services = SpringFactoriesUtils.loadQuietly(ExtraMessageSourceBasenameConvention.class);

        for (final var service : services) {
            if (service != null) {
                try {
                    CollectionUtils.nullSafeAddAll(list, service.getExtraMessageSourceBasename());
                } catch (Throwable ignored) {
                    // nop
                }
            }
        }
        return list;
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

}
