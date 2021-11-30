/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.integration;

import spring.turbo.util.ServiceLoaderUtils;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class Modules {

    public static final String SPRING_TURBO = "spring.turbo";
    public static final String SPRING_TURBO_SECURITY = "spring.turbo.security";
    public static final String SPRING_TURBO_SECURITY_JWT = "spring.turbo.security-jwt";

    /**
     * 所有子模块名称
     */
    public static final SortedSet<String> ALL_MODULE_NAMES;

    static {
        final Set<String> set = ServiceLoaderUtils.loadQuietly(ModuleNameProvider.class)
                .stream()
                .map(ModuleNameProvider::getModuleName)
                .collect(Collectors.toSet());
        ALL_MODULE_NAMES = Collections.unmodifiableSortedSet(new TreeSet<>(set));
    }

    private Modules() {
        super();
    }

}
