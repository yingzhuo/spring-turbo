/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import spring.turbo.util.Asserts;

/**
 * @author 应卓
 * @since 1.2.3
 */
public final class DebugLogUtils {

    /**
     * 私有构造方法
     */
    private DebugLogUtils() {
        super();
    }

    public static boolean isDebugLogEnabled() {
        return isDebugLogEnabled(
                SpringUtils.getEnvironment(),
                SpringUtils.getApplicationArguments()
        );
    }

    public static boolean isDebugLogEnabled(Environment environment, ApplicationArguments applicationArguments) {
        Asserts.notNull(environment);
        Asserts.notNull(applicationArguments);

        return environment.getProperty("debug", Boolean.class, Boolean.FALSE) ||
                applicationArguments.containsOption("debug");
    }

}
