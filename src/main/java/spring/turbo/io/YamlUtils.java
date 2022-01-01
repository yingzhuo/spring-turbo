/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import spring.turbo.util.Asserts;

import java.util.Properties;

/**
 * YAML文件工具
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class YamlUtils {

    private YamlUtils() {
        super();
    }

    /**
     * YAML文件转换为 {@link Properties}
     *
     * @param resources YAML文件
     * @return {@link Properties}实例
     */
    public static Properties toProperties(@NonNull Resource... resources) {
        Asserts.notEmpty(resources);
        Asserts.noNullElements(resources);
        final YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setSingleton(false);
        factory.setResources(resources);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

}
