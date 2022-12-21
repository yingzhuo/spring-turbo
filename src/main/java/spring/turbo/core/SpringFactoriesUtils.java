/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link SpringFactoriesLoader} 相关工具
 *
 * @author 应卓
 * @since 2.0.5
 */
public final class SpringFactoriesUtils {

    /**
     * 私有构造方法
     */
    private SpringFactoriesUtils() {
        super();
    }

    public static <T> List<T> loadQuietly(Class<T> factoryType) {
        final var loader = SpringFactoriesLoader.forDefaultResourceLocation();
        try {
            return loader.load(factoryType, (factoryType1, factoryImplementationName, failure) -> {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
