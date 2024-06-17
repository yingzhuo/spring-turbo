/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import java.util.List;
import java.util.Set;

/**
 * @author 应卓
 * @see SpringUtils
 * @see org.springframework.boot.ApplicationArguments
 * @since 3.0.7
 */
public final class ApplicationArgumentsUtils {

    /**
     * 私有构造方法
     */
    private ApplicationArgumentsUtils() {
        super();
    }

    public static Set<String> getOptionNames() {
        return SpringUtils.getApplicationArguments().getOptionNames();
    }

    public static boolean containsOption(String name) {
        return SpringUtils.getApplicationArguments().containsOption(name);
    }

    public static List<String> getOptionValues(String name) {
        return SpringUtils.getApplicationArguments().getOptionValues(name);
    }

    public static List<String> getNonOptionArgs() {
        return SpringUtils.getApplicationArguments().getNonOptionArgs();
    }

}
