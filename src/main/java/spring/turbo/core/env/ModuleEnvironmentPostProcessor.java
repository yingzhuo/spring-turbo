/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import spring.turbo.core.ApplicationHomeDir;

import java.util.List;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class ModuleEnvironmentPostProcessor extends AbstractConventionBasedEnvironmentPostProcessor {

    /**
     * 默认构造方法
     */
    public ModuleEnvironmentPostProcessor() {
        super.setOrder(Ordered.LOWEST_PRECEDENCE - 100);
    }

    @Override
    public List<ResourceOptionGroup> getResourceOptionGroups(Environment environment, ApplicationHomeDir home) {
        return List.of(
                generateConventionBasedGroup(home, "<spring-turbo>", "spring-turbo")
        );
    }

}
