/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * {@link ResourceOption}相关工具
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class ResourceOptions {

    private ResourceOptions() {
        super();
    }

    public static ResourceOption empty() {
        return ResourceOptionEmpty.getInstance();
    }

    public static ResourceOptionBuilder builder() {
        return new ResourceOptionBuilder();
    }

    public static ResourceOption of(String location) {
        return builder()
                .add(location)
                .build();
    }

    public static ResourceOption of(Resource resource) {
        return builder()
                .add(resource)
                .build();
    }

    public static ResourceOption fromSeparatedLocations(String locations) {
        final String[] array =
                Arrays.stream(locations.split("[\\s,]"))
                        .filter(StringUtils::hasText)
                        .map(StringUtils::trimWhitespace)
                        .toArray(String[]::new);

        return builder()
                .add(array)
                .build();
    }

    public static ResourceOption contact(ResourceOption... options) {
        for (final ResourceOption option : options) {
            if (option != null && option.isPresent()) {
                return option;
            }
        }
        return ResourceOptionEmpty.getInstance();
    }

}
