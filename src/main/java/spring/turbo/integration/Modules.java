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
    SPRING_TURBO_CAPTCHA("spring.turbo.captcha"),
    SPRING_TURBO_CSV("spring.turbo.csv"),
    SPRING_TURBO_EXCEL("spring.turbo.excel"),
    SPRING_TURBO_FEIGN("spring.turbo.feign"),
    SPRING_TURBO_JACKSON("spring.turbo.jackson"),
    SPRING_TURBO_KAFKA("spring.turbo.kafka"),
    SPRING_TURBO_PREDEFINED("spring.turbo.predefined"),
    SPRING_TURBO_QRCODE("spring.turbo.qrcode"),
    SPRING_TURBO_RABBITMQ("spring.turbo.rabbitmq"),
    SPRING_TURBO_REDIS("spring.turbo.redis"),
    SPRING_TURBO_SECURITY("spring.turbo.security"),
    SPRING_TURBO_SECURITY_JWT("spring.turbo.security-jwt"),
    SPRING_TURBO_SECURITY_HUTOOL("spring.turbo.security-hutool"),
    SPRING_TURBO_WEBMVC("spring.turbo.webmvc");

    private final String name;

    private Modules(String name) {
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
