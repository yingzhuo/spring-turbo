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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static spring.turbo.core.SpringFactoriesUtils.loadQuietly;

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

    public static List<String> getModuleNames() {
        var ret = new ArrayList<String>();
        var cs = loadQuietly(ModulesConvention.class);
        for (var c : cs) {
            ret.add(c.getModuleName());
        }
        return Collections.unmodifiableList(ret);
    }

}
