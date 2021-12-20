/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

/**
 * @author 应卓
 * @since 1.0.0
 */
public enum OS {

    WINDOWS, LINUX, MAC, SOLARIS, OTHER;

    public static OS get() {
        return SyncAvoid.os;
    }

    public static boolean isWindows() {
        return get() == WINDOWS;
    }

    public static boolean isLinux() {
        return get() == LINUX;
    }

    public static boolean isMac() {
        return get() == MAC;
    }

    public static boolean isSolaris() {
        return get() == SOLARIS;
    }

    public static boolean isOther() {
        return get() == OTHER;
    }

    // 延迟加载
    private static final class SyncAvoid {
        private static final OS os;

        static {
            final String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                os = OS.WINDOWS;
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
                os = OS.LINUX;
            } else if (osName.contains("mac")) {
                os = OS.MAC;
            } else if (osName.contains("sunos")) {
                os = OS.SOLARIS;
            } else {
                os = OS.OTHER;
            }
        }
    }

}
