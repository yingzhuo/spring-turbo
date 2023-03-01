/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo;

import spring.turbo.convention.ModulesConvention;
import spring.turbo.util.Asserts;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static spring.turbo.core.SpringFactoriesUtils.loadQuietly;
import static spring.turbo.util.StringUtils.blankSafeAdd;

/**
 * 本软件模块
 *
 * @author 应卓
 * @see SpringTurboVersion
 * @see spring.turbo.bean.condition.ConditionalOnSpringTurboModules
 * @since 2.0.13
 */
public final class SpringTurboModules {

    // 官方 (Maven Artifact Id)
    public static final String SPRING_TURBO = "spring-turbo";
    public static final String SPRING_TURBO_MODULE_DATA_ACCESSING = "spring-turbo-module-data-accessing";
    public static final String SPRING_TURBO_MODULE_DATA_HANDLING = "spring-turbo-module-data-handling";
    public static final String SPRING_TURBO_MODULE_FEIGN = "spring-turbo-module-feign";
    public static final String SPRING_TURBO_MODULE_MISC = "spring-turbo-module-misc";
    public static final String SPRING_TURBO_MODULE_QUERYSELECTOR = "spring-turbo-module-queryselector";
    public static final String SPRING_TURBO_MODULE_SECURITY = "spring-turbo-module-security";
    public static final String SPRING_TURBO_MODULE_WEBMVC = "spring-turbo-module-webmvc";

    /**
     * 私有构造方法
     */
    private SpringTurboModules() {
        super();
    }

    /**
     * 获取Classpath中存在的所有模块的名称
     *
     * @return 模块的名称
     */
    public static SortedSet<String> getModuleNames() {
        return SyncAvoid.MODULE_NAMES;
    }

    /**
     * 判断模块是否存在任意一个
     *
     * @param moduleNamesToTest 要测试的模块名称
     * @return 结果
     */
    public static boolean presentAny(String... moduleNamesToTest) {
        Asserts.notNull(moduleNamesToTest);
        Asserts.isTrue(moduleNamesToTest.length > 0);

        var set = getModuleNames();
        for (var moduleNameToTest : moduleNamesToTest) {
            if (set.contains(moduleNameToTest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断模块是否全部存在
     *
     * @param moduleNamesToTest 要测试的模块名称
     * @return 结果
     */
    public static boolean presentAll(String... moduleNamesToTest) {
        Asserts.notNull(moduleNamesToTest);
        Asserts.isTrue(moduleNamesToTest.length > 0);

        var set = getModuleNames();
        for (var moduleNameToTest : moduleNamesToTest) {
            if (!set.contains(moduleNameToTest)) {
                return false;
            }
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // 延迟初始化
    private static class SyncAvoid {
        private static final SortedSet<String> MODULE_NAMES;

        static {
            var set = new TreeSet<String>(Comparator.naturalOrder());
            var services = loadQuietly(ModulesConvention.class);
            for (var service : services) {
                blankSafeAdd(set, service.getModuleName());
            }
            MODULE_NAMES = Collections.unmodifiableSortedSet(set);
        }
    }

}
