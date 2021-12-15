/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;

import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
class SpringBootAutoConfiguration implements InitializingBean {

    private final FormatterRegistry formatterRegistry;

    @Autowired(required = false)
    public SpringBootAutoConfiguration(FormatterRegistry formatterRegistry) {
        this.formatterRegistry = formatterRegistry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Optional.ofNullable(formatterRegistry).ifPresent(it -> it.addConverter(new StringToNumberConverter()));
    }

}
