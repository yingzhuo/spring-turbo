/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.propertysource;

import com.moandjiezana.toml.Toml;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import spring.turbo.lang.NotRecommended;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * (内部使用)
 *
 * @author 应卓
 * @since 2.0.6
 */
@NotRecommended
@Deprecated(forRemoval = false)
@SuppressWarnings("unchecked")
class TomlPropertySourceLoader implements PropertySourceLoader {

    @Override
    public String[] getFileExtensions() {
        return new String[]{"toml"};
    }

    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        try (InputStream in = resource.getInputStream()) {
            Toml toml = new Toml().read(in);
            Map<String, Object> source = toml.toMap();

            Map<String, Object> result = new LinkedHashMap<>();
            buildFlattenedMap(result, source, null);
            return Collections.singletonList(new MapPropertySource(name, result));
        }
    }

    private void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String root) {
        boolean rootHasText = (null != root && !root.trim().isEmpty());

        source.forEach((key, value) -> {
            String path;

            if (rootHasText) {
                if (key.startsWith("[")) {
                    path = root + key;
                } else {
                    path = root + "." + key;
                }
            } else {
                path = key;
            }

            if (value instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) value;
                buildFlattenedMap(result, map, path);
            } else if (value instanceof Collection) {
                Collection<Object> collection = (Collection<Object>) value;
                int count = 0;
                for (Object object : collection) {
                    buildFlattenedMap(result, Collections.singletonMap("[" + (count++) + "]", object), path);
                }
            } else {
                result.put(path, null == value ? "" : value);
            }
        });
    }

}