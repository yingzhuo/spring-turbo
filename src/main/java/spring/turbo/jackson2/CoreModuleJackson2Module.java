/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import spring.turbo.SpringTurboVersion;

import static com.fasterxml.jackson.core.util.VersionUtil.parseVersion;

/**
 * @author 应卓
 *
 * @since 1.3.0
 */
public class CoreModuleJackson2Module extends SimpleModule {

    private static final Version VERSION = parseVersion(SpringTurboVersion.CURRENT, "com.github.yingzhuo",
            "spring-turbo");

    /**
     * 默认构造方法
     */
    public CoreModuleJackson2Module() {
        super(CoreModuleJackson2Module.class.getName(), VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
    }

}
