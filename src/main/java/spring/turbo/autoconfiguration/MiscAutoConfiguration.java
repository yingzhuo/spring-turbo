package spring.turbo.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import spring.turbo.util.crypto.keystore.KeyStoreFormatConverter;

/**
 * 自动配置类
 *
 * @author 应卓
 * @since 3.3.2
 */
@AutoConfiguration
public class MiscAutoConfiguration {

    /**
     * 配置 {@link KeyStoreFormatConverter} 实例
     *
     * @return {@link KeyStoreFormatConverter} 实例
     * @see KeyStoreFormatConverter
     */
    @Bean
    @ConditionalOnMissingBean
    public KeyStoreFormatConverter keyStoreFormatConverter() {
        return KeyStoreFormatConverter.getInstance();
    }

}
