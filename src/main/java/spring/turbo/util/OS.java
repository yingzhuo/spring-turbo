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
 * 操作系统
 *
 * @author 应卓
 *
 * @see spring.turbo.bean.condition.ConditionalOnOS
 *
 * @since 1.0.0
 */
public enum OS {

    WINDOWS, LINUX, MAC, SOLARIS, OTHER;

    /**
     * 获取当前操作系统
     *
     * @return 操作系统
     */
    public static OS get() {
        return SyncAvoid.os;
    }

    /**
     * 判断当前操作系统是否为{@code Windows}
     *
     * @return 结果
     */
    public static boolean isWindows() {
        return get() == WINDOWS;
    }

    /**
     * 判断当前操作系统是否为{@code Linux}
     *
     * @return 结果
     */
    public static boolean isLinux() {
        return get() == LINUX;
    }

    /**
     * 判断当前操作系统是否为{@code MacOS}
     *
     * @return 结果
     */
    public static boolean isMac() {
        return get() == MAC;
    }

    /**
     * 判断当前操作系统是否为{@code Solaris}
     *
     * @return 结果
     */
    public static boolean isSolaris() {
        return get() == SOLARIS;
    }

    /**
     * 判断当前操作系统是否为其他
     *
     * @return 结果
     */
    public static boolean isOther() {
        return get() == OTHER;
    }

    // 延迟加载
    private static class SyncAvoid {
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
