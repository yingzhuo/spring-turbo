/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.core.OrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * 获取服务的实例
     *
     * @param factoryType 服务的类型
     * @param <T>         服务的类型泛型
     * @return 结果
     */
    public static <T> List<T> loadQuietly(Class<T> factoryType) {
        Asserts.notNull(factoryType);

        final var loader = SpringFactoriesLoader.forDefaultResourceLocation();
        try {
            final List<T> list = new ArrayList<>();
            final var services = loader.load(factoryType, (factoryType1, factoryImplementationName, failure) -> {
            });
            CollectionUtils.nullSafeAddAll(list, services);
            OrderComparator.sort(list);
            return Collections.unmodifiableList(list);
        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }

}
