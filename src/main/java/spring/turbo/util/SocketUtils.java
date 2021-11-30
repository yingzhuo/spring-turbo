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

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class SocketUtils {

    private SocketUtils() {
        super();
    }

    public static boolean isReachable(String address, int port) {
        return isReachable(address, port, 0);
    }

    public static boolean isReachable(String address, int port, int timeoutInMilliseconds) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address, port), timeoutInMilliseconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
