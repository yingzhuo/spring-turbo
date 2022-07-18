/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.util.StringUtils;

import static spring.turbo.util.StringPool.SLASH;

/**
 * @author 应卓
 * @since 1.1.2
 */
public final class ClassPathDirUtils {

    /**
     * 私有构造方法
     */
    private ClassPathDirUtils() {
        super();
    }

    public static String getClassPathDir(Class<?> clz) {
        return getClassPathDir(clz, true, true);
    }

    public static String getClassPathDir(Class<?> clz, boolean forceStartsWithSlash, boolean forceEndsWithSlash) {
        String path = ClassUtils.getPackageName(clz).replaceAll("\\.", SLASH);
        if (forceStartsWithSlash) {
            if (!StringUtils.startsWithIgnoreCase(path, SLASH)) {
                path = SLASH + path;
            }
        }
        if (forceEndsWithSlash) {
            if (!StringUtils.endsWithIgnoreCase(path, SLASH)) {
                path += SLASH;
            }
        }
        return path;
    }

}
