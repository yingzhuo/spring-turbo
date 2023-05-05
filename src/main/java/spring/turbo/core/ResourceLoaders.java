/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * @author 应卓
 *
 * @since 2.2.4
 */
public final class ResourceLoaders {

    public static ResourceLoader getDefault() {
        return SyncAvoid.RESOURCE_LOADER;
    }

    /**
     * 私有构造方法
     */
    private ResourceLoaders() {
        super();
    }

    // 延迟加载
    private static final class SyncAvoid {
        private static final ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();
    }

}
