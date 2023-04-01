/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static spring.turbo.io.CloseUtils.closeQuietly;
import static spring.turbo.util.StringFormatter.format;

/**
 * @author 应卓
 * @since 2.1.3
 */
public class LoadmeEnvironmentPostProcessor extends EnvironmentPostProcessorSupport implements EnvironmentPostProcessor {

    private static final Properties EMPTY_PROPS = new Properties();

    @Override
    protected void execute(ConfigurableEnvironment environment, SpringApplication application) {

        var newProps = mergeProperties(
                loadFromClassPath(),
                loadFromHomeDir(application)
        );

        for (var key : newProps.keySet()) {
            var value = newProps.get(key);
            if (System.getProperty(key) == null) {
                trace("adding system-property: \"{}\":\"{}\"", key, value);
                System.setProperty(key, value);
            }
        }
    }

    private Properties loadFromClassPath() {
        var resource = new ClassPathResource("loadme.properties");
        if (resource.exists() && resource.isReadable()) {
            debug("loading \"classpath:loadme.properties\"");
            return resourceToProperties(resource);
        } else {
            return EMPTY_PROPS;
        }
    }

    private Properties loadFromHomeDir(SpringApplication application) {
        var location = format("{}/loadme.properties", super.getHomeDirPath(application));
        var resource = new FileSystemResource(location);
        if (resource.exists() && resource.isReadable()) {
            debug("loading \"file:{}\"", location);
            return resourceToProperties(resource);
        } else {
            return EMPTY_PROPS;
        }
    }

    private Properties resourceToProperties(Resource resource) {
        try {
            var props = new Properties();
            props.load(resource.getInputStream());
            return props;
        } catch (IOException ignored) {
            return new Properties();
        } finally {
            closeQuietly(resource);
        }
    }

    private Map<String, String> mergeProperties(Properties props1, Properties props2) {
        final var map = new HashMap<String, String>();

        for (var keyObj : props1.keySet()) {
            var valueObj = props1.get(keyObj);
            if ((keyObj instanceof String key) && (valueObj instanceof String value)) {
                map.put(key, value);
            }
        }

        for (var keyObj : props2.keySet()) {
            var valueObj = props2.get(keyObj);
            if ((keyObj instanceof String key) && (valueObj instanceof String value)) {
                map.put(key, value);
            }
        }

        return map;
    }

}
