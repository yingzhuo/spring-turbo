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
        var props = loadProperties(application);
        if (props != null && !props.isEmpty()) {
            for (var key : props.keySet()) {
                var value = props.get(key);
                if ((key instanceof String ks) && (value instanceof String vs)) {
                    if (System.getProperty(ks) == null) {
                        trace("adding system-property {}=\"{}\"", ks, vs);
                        System.setProperty(ks, vs);
                    }
                }
            }
        }
    }

    @Nullable
    public Properties loadProperties(SpringApplication application) {

        var location1 = format("file:{}/system.properties", getHomeDir(application).toPath().toAbsolutePath());
        var location2 = "classpath:system.properties";

        trace("finding resource: [{}]", String.join(", ", location1, location2));

        var option =
                RichResource.builder()
                        .addLocations(location1, location2)
                        .build();

        if (option.isPresent()) {
            try {
                var props = new Properties();
                props.load(option.get().getInputStreamQuietly());
                return props;
            } catch (IOException ignored) {
                return null;
            }
        }

        return null;
    }

}
