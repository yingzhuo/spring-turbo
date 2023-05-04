/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.system.ApplicationHome;
import spring.turbo.util.Asserts;

import java.io.File;

import static spring.turbo.util.CollectionUtils.size;

/**
 * @author 应卓
 *
 * @since 2.1.3
 */
public final class SpringApplicationUtils {

    /**
     * 私有构造方法
     */
    private SpringApplicationUtils() {
        super();
    }

    public static File getHomeDir(SpringApplication application) {
        Asserts.notNull(application);

        var sourceClasses = application.getAllSources().stream().filter(o -> o instanceof Class<?>)
                .map(o -> (Class<?>) o).toList();

        if (size(sourceClasses) == 1) {
            return new ApplicationHome(sourceClasses.get(0)).getDir();
        } else {
            return new ApplicationHome().getDir();
        }
    }

    public static String getHomePath(SpringApplication application) {
        return getHomeDir(application).getAbsolutePath();
    }

}
