/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import spring.turbo.core.ApplicationHomeDir;

import java.util.ArrayList;
import java.util.List;

import static spring.turbo.core.Dependencies.*;
import static spring.turbo.util.StringFormatter.format;

/**
 * @author 应卓
 * @since 2.0.7
 */
public abstract class AbstractConventionBasedEnvironmentPostProcessor extends AbstractResourceOptionBasedEnvironmentPostProcessor {

    protected final ResourceOptionGroup generateConventionBasedGroup(ApplicationHomeDir home, String groupName, String filename) {
        final List<String> list = new ArrayList<>();

        // 例如 file:/opt/myapp/filename.?
        list.add(home.resolveResourceLocation(filename + ".properties"));
        list.add(home.resolveResourceLocation(filename + ".xml"));
        if (YAML_PRESENT) {
            list.add(home.resolveResourceLocation(filename + ".yaml"));
            list.add(home.resolveResourceLocation(filename + ".yml"));
        }
        if (HOCON_PRESENT) {
            list.add(home.resolveResourceLocation(filename + ".conf"));
        }
        if (TOML_PRESENT) {
            list.add(home.resolveResourceLocation(filename + ".toml"));
        }

        // 例如 file:/opt/myapp/config/filename.?
        list.add(home.resolveResourceLocation("config", filename + ".properties"));
        list.add(home.resolveResourceLocation("config", filename + ".xml"));
        if (YAML_PRESENT) {
            list.add(home.resolveResourceLocation("config", filename + ".yaml"));
            list.add(home.resolveResourceLocation("config", filename + ".yml"));
        }
        if (HOCON_PRESENT) {
            list.add(home.resolveResourceLocation("config", filename + ".conf"));
        }
        if (TOML_PRESENT) {
            list.add(home.resolveResourceLocation("config", filename + ".toml"));
        }

        // 例如 file:/opt/myapp/.config/filename.?
        list.add(home.resolveResourceLocation(".config", filename + ".properties"));
        list.add(home.resolveResourceLocation(".config", filename + ".xml"));
        if (YAML_PRESENT) {
            list.add(home.resolveResourceLocation(".config", filename + ".yaml"));
            list.add(home.resolveResourceLocation(".config", filename + ".yml"));
        }
        if (HOCON_PRESENT) {
            list.add(home.resolveResourceLocation(".config", filename + ".conf"));
        }
        if (TOML_PRESENT) {
            list.add(home.resolveResourceLocation(".config", filename + ".toml"));
        }

        // 例如 file:/opt/myapp/_config/filename.?
        list.add(home.resolveResourceLocation("_config", filename + ".properties"));
        list.add(home.resolveResourceLocation("_config", filename + ".xml"));
        if (YAML_PRESENT) {
            list.add(home.resolveResourceLocation("_config", filename + ".yaml"));
            list.add(home.resolveResourceLocation("_config", filename + ".yml"));
        }
        if (HOCON_PRESENT) {
            list.add(home.resolveResourceLocation("_config", filename + ".conf"));
        }
        if (TOML_PRESENT) {
            list.add(home.resolveResourceLocation("_config", filename + ".toml"));
        }

        // 例如 classpath:filename.?
        list.add(format("classpath:{}.properties", filename));
        list.add(format("classpath:{}.xml", filename));
        if (YAML_PRESENT) {
            list.add(format("classpath:{}.yaml", filename));
            list.add(format("classpath:{}.yml", filename));
        }
        if (HOCON_PRESENT) {
            list.add(format("classpath:{}.conf", filename));
        }
        if (TOML_PRESENT) {
            list.add(format("classpath:{}.toml", filename));
        }

        // 例如 classpath:config/filename.?
        list.add(format("classpath:config/{}.properties", filename));
        list.add(format("classpath:config/{}.xml", filename));
        if (YAML_PRESENT) {
            list.add(format("classpath:config/{}.yaml", filename));
            list.add(format("classpath:config/{}.yml", filename));
        }
        if (HOCON_PRESENT) {
            list.add(format("classpath:config/{}.conf", filename));
        }
        if (TOML_PRESENT) {
            list.add(format("classpath:config/{}.toml", filename));
        }

        // 例如 classpath:.config/filename.?
        list.add(format("classpath:.config/{}.properties", filename));
        list.add(format("classpath:.config/{}.xml", filename));
        if (YAML_PRESENT) {
            list.add(format("classpath:.config/{}.yaml", filename));
            list.add(format("classpath:.config/{}.yml", filename));
        }
        if (HOCON_PRESENT) {
            list.add(format("classpath:.config/{}.conf", filename));
        }
        if (TOML_PRESENT) {
            list.add(format("classpath:.config/{}.toml", filename));
        }

        // 例如 classpath:_config/filename.?
        list.add(format("classpath:_config/{}.properties", filename));
        list.add(format("classpath:_config/{}.xml", filename));
        if (YAML_PRESENT) {
            list.add(format("classpath:_config/{}.yaml", filename));
            list.add(format("classpath:_config/{}.yml", filename));
        }
        if (HOCON_PRESENT) {
            list.add(format("classpath:_config/{}.conf", filename));
        }
        if (TOML_PRESENT) {
            list.add(format("classpath:_config/{}.toml", filename));
        }

        // 例如 classpath:META-INF/filename.?
        list.add(format("classpath:META-INF/{}.properties", filename));
        list.add(format("classpath:META-INF/{}.xml", filename));
        if (YAML_PRESENT) {
            list.add(format("classpath:META-INF/{}.yaml", filename));
            list.add(format("classpath:META-INF/{}.yml", filename));
        }
        if (HOCON_PRESENT) {
            list.add(format("classpath:META-INF/{}.conf", filename));
        }
        if (TOML_PRESENT) {
            list.add(format("classpath:META-INF/{}.toml", filename));
        }

        return new ResourceOptionGroup(groupName, list);
    }

}