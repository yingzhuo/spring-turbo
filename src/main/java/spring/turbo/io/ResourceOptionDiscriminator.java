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

    @Deprecated(forRemoval = true)
    public static ResourceOptionDiscriminator newDefault() {
        return new Readable();
    }

    public static ResourceOptionDiscriminator newReadableImpl() {
        return new Readable();
    }

    public static ResourceOptionDiscriminator newExistsImpl() {
        return new Exists();
    }

    @Override
    public boolean test(@Nullable Resource resource);

    /**
     * 默认实现
     */
    public static class Readable implements ResourceOptionDiscriminator {
        @Override
        public boolean test(@Nullable Resource resource) {
            return resource != null && resource.isReadable();
        }
    }

    /**
     * 只要存在就算数
     */
    public static class Exists implements ResourceOptionDiscriminator {
        @Override
        public boolean test(@Nullable Resource resource) {
            return resource != null && resource.exists();
        }
    }

}
