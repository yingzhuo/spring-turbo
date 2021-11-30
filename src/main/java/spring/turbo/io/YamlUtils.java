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
import org.springframework.util.Assert;

import java.util.Properties;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class YamlUtils {

    private YamlUtils() {
        super();
    }

    public static Properties readProperties(Resource... resources) {
        Assert.notEmpty(resources, "resources is empty");
        Assert.noNullElements(resources, "resources is null or has null element(s)");
        final YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setSingleton(false);
        factory.setResources(resources);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

}
