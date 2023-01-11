/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import org.springframework.core.env.Environment;
import spring.turbo.core.ApplicationHomeDir;
import spring.turbo.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author 应卓
 * @see spring.turbo.bean.injection.ApplicationName
 * @since 2.0.7
 */
public class ApplicationNameEnvironmentPostProcessor extends AbstractConventionBasedEnvironmentPostProcessor {

    private static final String SPRING_APPLICATION_NAME = "spring.application.name";

    /**
     * 默认构造方法
     */
    public ApplicationNameEnvironmentPostProcessor() {
        super.setOrder(LOWEST_PRECEDENCE);
    }

    @Nullable
    @Override
    public List<ResourceOptionGroup> getResourceOptionGroups(Environment environment, ApplicationHomeDir home) {
        var applicationName = environment.getProperty(SPRING_APPLICATION_NAME);

        if (StringUtils.isEmpty(applicationName)) {
            return null;
        }

        return List.of(
                generateConventionBasedGroup(home, "<application-name>", applicationName)
        );
    }

}
