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
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.StringFormatter;

import java.util.ArrayList;

import static spring.turbo.core.Dependencies.*;

/**
 * (内部工具)
 *
 * @author 应卓
 * @since 2.0.7
 */
public abstract class AbstractModuleEnvironmentPostProcessor extends AbstractResourceBasedEnvironmentPostProcessor {

    private final String[] moduleNames;

    public AbstractModuleEnvironmentPostProcessor(int order, String... moduleNames) {
        super(order);
        this.moduleNames = moduleNames;
    }

    @Override
    public final void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final ResourceOption resourceOption = loadResource(application);

        if (resourceOption.isAbsent()) {
            return;
        }

        final var propertySource = toPropertySource(resourceOption);
        if (propertySource != null) {
            final var propertySources = environment.getPropertySources();
            propertySources.addLast(propertySource);
        }
    }

    private ResourceOption loadResource(SpringApplication application) {
        final var fileToTest = new ArrayList<String>();

        for (final var moduleName : moduleNames) {

            // ---
            for (var appDir : getApplicationDirectories(application)) {
                fileToTest.add(StringFormatter.format("file:{}/{}.properties", appDir, moduleName));
                fileToTest.add(StringFormatter.format("file:{}/{}.xml", appDir, moduleName));
                if (YAML_PRESENT) {
                    fileToTest.add(StringFormatter.format("file:{}/{}.yaml", appDir, moduleName));
                    fileToTest.add(StringFormatter.format("file:{}/{}.yml", appDir, moduleName));
                }
                if (HOCON_PRESENT) {
                    fileToTest.add(StringFormatter.format("file:{}/{}.conf", appDir, moduleName));
                }
                if (TOML_PRESENT) {
                    fileToTest.add(StringFormatter.format("file:{}/{}.toml", appDir, moduleName));
                }

                fileToTest.add(StringFormatter.format("file:{}/config/{}.properties", appDir, moduleName));
                fileToTest.add(StringFormatter.format("file:{}/config/{}.xml", appDir, moduleName));
                if (YAML_PRESENT) {
                    fileToTest.add(StringFormatter.format("file:{}/config/{}.yaml", appDir, moduleName));
                    fileToTest.add(StringFormatter.format("file:{}/config/{}.yml", appDir, moduleName));
                }
                if (HOCON_PRESENT) {
                    fileToTest.add(StringFormatter.format("file:{}/config/{}.conf", appDir, moduleName));
                }
                if (TOML_PRESENT) {
                    fileToTest.add(StringFormatter.format("file:{}/config/{}.toml", appDir, moduleName));
                }
            }

            // ---
            fileToTest.add(StringFormatter.format("classpath:{}.properties", moduleName));
            fileToTest.add(StringFormatter.format("classpath:{}.xml", moduleName));
            if (YAML_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:{}.yaml", moduleName));
                fileToTest.add(StringFormatter.format("classpath:{}.yml", moduleName));
            }
            if (HOCON_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:{}.conf", moduleName));
            }
            if (TOML_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:{}.toml", moduleName));
            }

            // ---
            fileToTest.add(StringFormatter.format("classpath:META-INF/{}.properties", moduleName));
            fileToTest.add(StringFormatter.format("classpath:META-INF/{}.xml", moduleName));
            if (YAML_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:META-INF/{}.yaml", moduleName));
                fileToTest.add(StringFormatter.format("classpath:META-INF/{}.yml", moduleName));
            }
            if (HOCON_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:META-INF/{}.conf", moduleName));
            }
            if (TOML_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:META-INF/{}.toml", moduleName));
            }

            // ---
            fileToTest.add(StringFormatter.format("classpath:conf/{}.properties", moduleName));
            fileToTest.add(StringFormatter.format("classpath:conf/{}.xml", moduleName));
            if (YAML_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:conf/{}.yaml", moduleName));
                fileToTest.add(StringFormatter.format("classpath:conf/{}.yml", moduleName));
            }
            if (HOCON_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:conf/{}.conf", moduleName));
            }
            if (TOML_PRESENT) {
                fileToTest.add(StringFormatter.format("classpath:conf/{}.toml", moduleName));
            }
        }

        return ResourceOptions.builder()
                .add(fileToTest)
                .build();
    }

}
