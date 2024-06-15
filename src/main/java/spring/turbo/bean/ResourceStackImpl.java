/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import spring.turbo.core.ResourceLoaders;
import spring.turbo.io.RichResource;

/**
 * @param environment
 *            {@link Environment} 实例
 * @param resourceLoader
 *            {@link ResourceLoader} 实例
 *
 * @author 应卓
 */
@Deprecated(since = "3.3.1")
public record ResourceStackImpl(Environment environment, ResourceLoader resourceLoader) implements ResourceStack {

    public ResourceStackImpl(Environment environment) {
        this(environment, ResourceLoaders.getDefault());
    }

    @Override
    public RichResource find(String environmentName, String... locations) {
        return RichResource.builder().resourceLoader(resourceLoader)
                .blankSafeAddLocations(environment.getProperty(environmentName))
                .discriminator(RichResource.Builder.DEFAULT_DISCRIMINATOR).blankSafeAddLocations(locations).build()
                .orElse(null);
    }

}
