/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import spring.turbo.format.StringToNumberConverter;
import spring.turbo.format.StringToNumberPairConverter;

/**
 * @author 应卓
 * @since 1.0.0
 */
class SpringBootAutoConfiguration {

    @Autowired(required = false)
    public SpringBootAutoConfiguration(FormatterRegistry registry) {
        if (registry != null) {
            registry.addConverter(new StringToNumberConverter());
            registry.addConverter(new StringToNumberPairConverter());
        }
    }

}
