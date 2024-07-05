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
import static spring.turbo.util.StringDefaults.blankToDefault;
import static spring.turbo.util.collection.CollectionUtils.nullSafeAddAll;

/**
 * {@link SpringFactoriesLoader} 相关工具
 *
 * @author 应卓
 * @see SpringFactoriesLoader
 * @since 2.0.5
 */
public final class SpringFactoriesUtils {

    /**
     * 私有构造方法
     */
    private SpringFactoriesUtils() {
    }

    public static <T> List<T> loadQuietly(Class<T> factoryType) {
        return loadQuietly(factoryType, FACTORIES_RESOURCE_LOCATION);
    }

    public static <T> List<T> loadQuietly(Class<T> factoryType, @Nullable String factoriesResourceLocation) {
        return loadQuietly(factoryType, factoriesResourceLocation, null);
    }

    public static <T> List<T> loadQuietly(Class<T> factoryType, @Nullable String factoriesResourceLocation,
                                          @Nullable ClassLoader classLoader) {
        Asserts.notNull(factoryType);

        factoriesResourceLocation = blankToDefault(factoriesResourceLocation, FACTORIES_RESOURCE_LOCATION);

        var factoriesLoader = forResourceLocation(factoriesResourceLocation, classLoader);

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
        }

        @Override
        public void handleFailure(Class<?> factoryType, String factoryImplementationName, Throwable ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

}
