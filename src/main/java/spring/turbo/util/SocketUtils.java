/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;

/**
 * {@link Socket}相关工具
 *
 * @author 应卓
 *
 * @since 1.0.0
 */
public final class SocketUtils {

    /**
     * 私有构造方法
     */
    private SocketUtils() {
        super();
    }

    /**
     * 通过TCP判断远程socket是否可以联通
     *
     * @param address
     *            地址
     * @param port
     *            端口号
     * @param timeoutInMillis
     *            timeout毫秒数
     *
     * @return 能联通时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isReachable(String address, int port, int timeoutInMillis) {
        Asserts.hasText(address);

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address, port), timeoutInMillis);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过TCP判断远程socket是否可以联通
     *
     * @param address
     *            地址
     * @param port
     *            端口号
     * @param timeout
     *            timeout
     *
     * @return 能联通时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isReachable(String address, int port, Duration timeout) {
        Asserts.notNull(timeout);
        return isReachable(address, port, (int) timeout.toMillis());
    }

}
