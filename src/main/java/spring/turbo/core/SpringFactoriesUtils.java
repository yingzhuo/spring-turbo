/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.OrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.core.io.support.SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION;
import static org.springframework.core.io.support.SpringFactoriesLoader.forResourceLocation;
import static spring.turbo.util.CollectionUtils.nullSafeAddAll;
import static spring.turbo.util.StringUtils.blankToDefault;

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
        return loadQuietly(factoryType, null);
    }

    public static <T> List<T> loadQuietly(Class<T> factoryType, @Nullable String factoriesResourceLocation) {
        return loadQuietly(factoryType, factoriesResourceLocation, null);
    }

    public static <T> List<T> loadQuietly(Class<T> factoryType, @Nullable String factoriesResourceLocation, @Nullable ClassLoader classLoader) {
        Asserts.notNull(factoryType);

        var factoriesLoader = forResourceLocation(
                blankToDefault(factoriesResourceLocation, FACTORIES_RESOURCE_LOCATION),
                classLoader
        );

        try {
            final List<T> list = new ArrayList<>();
            var services = factoriesLoader.load(factoryType, LoggingFailureHandler.INSTANCE);
            nullSafeAddAll(list, services);
            OrderComparator.sort(list);
            return Collections.unmodifiableList(list);
        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static class LoggingFailureHandler implements SpringFactoriesLoader.FailureHandler {
        private static final Logger log = LoggerFactory.getLogger(LoggingFailureHandler.class);
        private static final LoggingFailureHandler INSTANCE = new LoggingFailureHandler();

        /**
         * 私有构造方法
         */
        private LoggingFailureHandler() {
            super();
        }

        @Override
        public void handleFailure(Class<?> factoryType, String factoryImplementationName, Throwable ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

}
