/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.integration;

import spring.turbo.bean.Named;
import spring.turbo.util.ServiceLoaderUtils;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 官方模块
 *
 * @author 应卓
 * @since 1.0.0
 */
public enum Modules implements Named {

    SPRING_TURBO("spring.turbo"),
    SPRING_TURBO_FEIGN("spring.turbo.feign"),
    SPRING_TURBO_JACKSON("spring.turbo.jackson"),
    SPRING_TURBO_QUERYSELECTOR("spring.turbo.queryselector"),
    SPRING_TURBO_QUERYSELECTOR_SQL("spring.turbo.queryselector-sql"),
    SPRING_TURBO_SECURITY("spring.turbo.security"),
    SPRING_TURBO_WEBMVC("spring.turbo.webmvc"),
    SPRING_TURBO_MISCELLANEOUS("spring.turbo.miscellaneous"),
    SPRING_TURBO_DATA_ACCESSING("spring.turbo.data-accessing"),
    SPRING_TURBO_DATA_HANDLING("spring.turbo.data-handling");

    private final String name;

    Modules(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isPresent() {
        return SyncAvoid.ALL_MODULE_NAMES.contains(getName());
    }

    /**
     * 懒加载
     */
    private static final class SyncAvoid {

        /**
         * 所有子模块名称 (已排序)
         */
        static final SortedSet<String> ALL_MODULE_NAMES;

        static {
            final Set<String> set =
                    ServiceLoaderUtils.loadQuietly(ModuleNameProvider.class)
                            .stream()
                            .map(ModuleNameProvider::getModuleName)
                            .collect(Collectors.toSet());

            ALL_MODULE_NAMES = Collections.unmodifiableSortedSet(new TreeSet<>(set));
        }
    }

}
