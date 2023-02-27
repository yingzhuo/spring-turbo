/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo;

import org.springframework.lang.Nullable;
import spring.turbo.convention.ModulesConvention;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static spring.turbo.core.SpringFactoriesUtils.loadQuietly;
import static spring.turbo.util.StringUtils.blankSafeAdd;

/**
 * @author 应卓
 * @since 2.0.13
 */
public final class SpringTurboModules {

    /**
     * 私有构造方法
     */
    private SpringTurboModules() {
        super();
    }

    public static Set<String> getModuleNames() {
        return SyncAvoid.MODULE_NAMES;
    }

    public static boolean presentAny(@Nullable String... moduleNamesToTest) {
        if (moduleNamesToTest == null) {
            return false;
        }

        var set = getModuleNames();
        for (var moduleNameToTest : moduleNamesToTest) {
            if (set.contains(moduleNameToTest)) {
                return true;
            }
        }

        return false;
    }

    public static boolean presentAll(@Nullable String... moduleNamesToTest) {
        if (moduleNamesToTest == null) {
            return false;
        }

        var set = getModuleNames();
        for (var moduleNameToTest : moduleNamesToTest) {
            if (!set.contains(moduleNameToTest)) {
                return false;
            }
        }

        return true;
    }

    // 延迟初始化
    private static class SyncAvoid {
        private static final Set<String> MODULE_NAMES;

        static {
            var set = new TreeSet<String>(Comparator.naturalOrder());
            var services = loadQuietly(ModulesConvention.class);
            for (var service : services) {
                blankSafeAdd(set, service.getModuleName());
            }
            MODULE_NAMES = Collections.unmodifiableSet(set);
        }
    }

}
