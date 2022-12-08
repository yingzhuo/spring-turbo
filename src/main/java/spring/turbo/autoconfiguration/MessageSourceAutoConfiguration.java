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
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import spring.turbo.convention.ExtraMessageSourceBasenameConvention;
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.ServiceLoaderUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author 应卓
 * @since 2.0.3
 */
@AutoConfiguration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties
public class MessageSourceAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties() {
            {
                // 重新设置默认值
                super.setBasename(null);
            }
        };
    }

    @Bean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource(MessageSourceProperties properties) {
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

    private String[] mergeBasename(MessageSourceProperties properties) {
        var basename1 = StringUtils
                .commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename()));
        var basename2 = getAllExtraBasename();

        var ret = new ArrayList<String>();
        CollectionUtils.nullSafeAddAll(ret, basename1);
        CollectionUtils.nullSafeAddAll(ret, basename2);
        return ret.toArray(new String[0]);
    }

    public Collection<String> getAllExtraBasename() {
        final var list = new ArrayList<String>();

        final var services = ServiceLoaderUtils.loadQuietly(ExtraMessageSourceBasenameConvention.class);

        for (var service : services) {
            if (service != null) {
                try {
                    CollectionUtils.nullSafeAddAll(list, service.getExtraMessageSourceBasename());
                } catch (Throwable ignored) {
                    // nop
                }
            }
        }
        return Collections.unmodifiableCollection(list);
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

}
