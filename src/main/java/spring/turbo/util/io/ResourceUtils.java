package spring.turbo.util.io;

import org.springframework.boot.io.ApplicationResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

/**
 * @author 应卓
 * @since 3.3.2
 */
public final class ResourceUtils {

    private static final ResourceLoader RESOURCE_LOADER
            = new ApplicationResourceLoader(ClassUtils.getDefaultClassLoader());

    /**
     * 私有构造方法
     */
    private ResourceUtils() {
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

}
