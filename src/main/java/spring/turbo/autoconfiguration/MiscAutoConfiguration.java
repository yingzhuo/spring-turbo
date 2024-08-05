package spring.turbo.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import spring.turbo.util.crypto.keystore.KeyStoreFormatConverter;

@AutoConfiguration
public class MiscAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public KeyStoreFormatConverter keyStoreFormatConverter() {
        return new KeyStoreFormatConverter();
    }

}
