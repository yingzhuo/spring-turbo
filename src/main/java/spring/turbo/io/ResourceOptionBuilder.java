/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import spring.turbo.bean.Builder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ResourceOptionBuilder implements Builder<ResourceOption> {

    private final List<Resource> list = new LinkedList<>();
    private ResourceLoader resourceLoader = new DefaultResourceLoader(ClassUtils.getDefaultClassLoader());
    private ResourceOptionDiscriminator discriminator = ResourceOptionDiscriminator.getDefault();

    ResourceOptionBuilder() {
        super();
    }

    public ResourceOptionBuilder add(@Nullable Resource... resources) {
        if (resources != null) {
            Collections.addAll(list, resources);
        }
        return this;
    }

    public ResourceOptionBuilder add(@Nullable String... resourceLocations) {
        if (resourceLocations != null) {
            for (String it : resourceLocations) {
                Resource resource = loadQuietly(it);
                if (resource != null) {
                    this.list.add(resource);
                }
            }
        }
        return this;
    }

    public ResourceOptionBuilder resourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        return this;
    }

    public ResourceOptionBuilder discriminator(ResourceOptionDiscriminator discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    @Nullable
    private Resource loadQuietly(String location) {
        try {
            return resourceLoader.getResource(location);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResourceOption build() {
        for (Resource resource : list) {
            if (this.discriminator.isExists(resource)) {
                return new ResourceOptionImpl(resource);
            }
        }
        return ResourceOptionEmpty.getInstance();
    }

}
