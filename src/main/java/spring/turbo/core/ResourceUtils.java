package spring.turbo.core;

import org.springframework.boot.io.ApplicationResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;

/**
 * {@link Resource}等相关工具
 *
 * @author 应卓
 * @since 3.3.2
 */
public final class ResourceUtils {

    private static final ResourceLoader RESOURCE_LOADER
            = new ApplicationResourceLoader(ClassUtils.getDefaultClassLoader());

    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER
            = ResourcePatternUtils.getResourcePatternResolver(RESOURCE_LOADER);

    /**
     * 私有构造方法
     */
    private ResourceUtils() {
    }

    /**
     * 获取{@link ResourceLoader}实例
     *
     * @return {@link ResourceLoader}实例
     * @see #getResourcePatternResolver()
     */
    public static ResourceLoader getResourceLoader() {
        return RESOURCE_LOADER;
    }

    /**
     * 获取{@link ResourcePatternResolver}实例
     *
     * @return {@link ResourcePatternResolver}实例
     * @see #getResourceLoader()
     */
    public static ResourcePatternResolver getResourcePatternResolver() {
        return RESOURCE_PATTERN_RESOLVER;
    }

    /**
     * 加载资源
     *
     * @param location 资源位置
     * @return 资源
     */
    public static Resource load(String location) {
        return RESOURCE_LOADER.getResource(location);
    }

    /**
     * 尝试多个资源位置，直到找到一个存在的资源为止
     *
     * @param locations 资源位置
     * @return 结果或{@code null}
     */
    @Nullable
    public static Resource loadFirstExists(Iterable<String> locations) {
        for (var location : locations) {
            try {
                var resource = RESOURCE_LOADER.getResource(location);
                if (resource.exists()) {
                    return resource;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    /**
     * 通过某种模式，加载多个资源
     *
     * @param locationPattern 资源为止模式
     * @return 结果
     * @throws UncheckedIOException I/O错误
     */
    public static List<Resource> loadResources(String locationPattern) {
        try {
            return Arrays.asList(RESOURCE_PATTERN_RESOLVER.getResources(locationPattern));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
