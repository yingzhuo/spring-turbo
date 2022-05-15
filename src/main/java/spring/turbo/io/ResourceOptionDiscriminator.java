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
import org.springframework.lang.Nullable;

import java.util.function.Predicate;

/**
 * 资源选项鉴别器
 *
 * @author 应卓
 * @since 1.0.0
 */
@FunctionalInterface
public interface ResourceOptionDiscriminator extends Predicate<Resource> {

    public static ResourceOptionDiscriminator getDefault() {
        return resource -> resource != null && resource.isReadable();
    }

    public boolean isExists(@Nullable Resource resource);

    @Override
    public default boolean test(Resource resource) {
        return isExists(resource);
    }

}
