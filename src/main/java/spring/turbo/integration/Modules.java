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
 * 模块
 *
 * @author 应卓
 * @since 1.0.0
 */
public enum Modules {

    SPRING_TURBO("spring.turbo"),
    SPRING_TURBO_PREDEFINED("spring.turbo.predefined"),
    SPRING_TURBO_SECURITY("spring.turbo.security"),
    SPRING_TURBO_SECURITY_JWT("spring.turbo.security-jwt"),
    SPRING_TURBO_WEBMVC("spring.turbo.webmvc"),
    SPRING_TURBO_QRCODE("spring.turbo.qrcode");

    /**
     * 所有子模块名称 (已排序)
     */
    public static final SortedSet<String> ALL_MODULE_NAMES;

    static {
        final Set<String> set =
                ServiceLoaderUtils.loadQuietly(ModuleNameProvider.class)
                        .stream()
                        .map(ModuleNameProvider::getModuleName)
                        .collect(Collectors.toSet());

        ALL_MODULE_NAMES = Collections.unmodifiableSortedSet(new TreeSet<>(set));
    }

    private final String moduleName;

    Modules(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public boolean isPresent() {
        return ALL_MODULE_NAMES.contains(getModuleName());
    }

}
