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
import spring.turbo.util.ClassUtils;

/**
 * {@link ResourceLoader} 相关工具
 *
 * @author 应卓
 * @see #getDefault()
 * @since 2.2.4
 */
public final class ResourceLoaders {

    /**
     * 私有构造方法
     */
    private ResourceLoaders() {
        super();
    }

    /**
     * 获取默认的 {@link org.springframework.core.io.ResourceLoader} 实现
     *
     * @return {@link org.springframework.core.io.ResourceLoader} 实现
     */
    public static ResourceLoader getDefault() {
        return SyncAvoid.RESOURCE_LOADER;
    }

    // 延迟加载
    private static final class SyncAvoid {
        private static final ResourceLoader RESOURCE_LOADER =
                new DefaultResourceLoader(ClassUtils.getDefaultClassLoader());
    }

}
