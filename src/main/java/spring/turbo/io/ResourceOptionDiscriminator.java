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

    /**
     * 获取默认实现
     *
     * @return 默认实现的实例
     */
    public static ResourceOptionDiscriminator newDefault() {
        return new Default();
    }

    public boolean isExists(@Nullable Resource resource);

    @Override
    public default boolean test(Resource resource) {
        return isExists(resource);
    }

    /**
     * 默认实现
     */
    public static class Default implements ResourceOptionDiscriminator {
        @Override
        public boolean isExists(@Nullable Resource resource) {
            return resource != null && resource.isReadable();
        }
    }

}
