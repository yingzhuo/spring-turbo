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

    SPRING_TURBO("spring.turbo", false),
    SPRING_TURBO_CAPTCHA("spring.turbo.captcha", false),
    SPRING_TURBO_CSV("spring.turbo.csv", true),
    SPRING_TURBO_DATA("spring.turbo.data", true),
    SPRING_TURBO_DATASOURCE("spring.turbo.datasource", false),
    SPRING_TURBO_EXCEL("spring.turbo.excel", true),
    SPRING_TURBO_FEIGN("spring.turbo.feign", false),
    SPRING_TURBO_JACKSON("spring.turbo.jackson", false),
    SPRING_TURBO_JAVASSIST("spring.turbo.javassist", true),
    SPRING_TURBO_KAFKA("spring.turbo.kafka", false),
    SPRING_TURBO_PDF("spring.turbo.pdf", true),
    SPRING_TURBO_QRCODE("spring.turbo.qrcode", false),
    SPRING_TURBO_QUERYSELECTOR("spring.turbo.queryselector", false),
    SPRING_TURBO_QUERYSELECTOR_SQL("spring.turbo.queryselector-sql", false),
    SPRING_TURBO_RABBITMQ("spring.turbo.rabbitmq", false),
    SPRING_TURBO_REDIS("spring.turbo.redis", false),
    SPRING_TURBO_SECURITY("spring.turbo.security", false),
    SPRING_TURBO_WEBMVC("spring.turbo.webmvc", false),
    SPRING_TURBO_ZOOKEEPER("spring.turbo.zookeeper", false),
    ;

    private final String name;
    private final boolean beta;

    private Modules(String name, boolean beta) {
        this.name = name;
        this.beta = beta;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isBeta() {
        return beta;
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
