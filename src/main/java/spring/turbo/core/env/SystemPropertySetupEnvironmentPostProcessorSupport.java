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
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.Nullable;
import spring.turbo.io.RichResource;

import java.io.IOException;
import java.util.Properties;

import static spring.turbo.util.StringFormatter.format;

/**
 * @author 应卓
 * @since 2.1.3
 */
public class SystemPropertySetupEnvironmentPostProcessorSupport extends EnvironmentPostProcessorSupport {

    @Override
    protected void execute(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            doExecute(environment, application);
        } catch (Throwable ignored) {
            // nop
        }
    }

    private void doExecute(ConfigurableEnvironment environment, SpringApplication application) {
        var props = findProperties(application);
        if (props != null && !props.isEmpty()) {
            for (var key : props.keySet()) {
                var value = props.get(key);
                if ((key instanceof String ks) && (value instanceof String vs)) {
                    debug("adding system-property {}={}", ks, vs);
                    System.setProperty(ks, vs);
                }
            }
        }
    }

    @Nullable
    public Properties findProperties(SpringApplication application) {
        var resourceOption =
                RichResource.builder()
                        .addLocations(
                                format("file:{}/system.properties", getHomeDir(application).toPath().toAbsolutePath()),
                                "classpath:system.properties")
                        .build();

        if (resourceOption.isPresent()) {
            try {
                var props = new Properties();
                props.load(resourceOption.get().getInputStreamQuietly());
                return props;
            } catch (IOException e) {
                return null;
            }
        }

        return null;
    }

}
