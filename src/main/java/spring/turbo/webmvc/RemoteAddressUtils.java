/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import spring.turbo.util.Asserts;

/**
 * 远程IP地址工具
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class RemoteAddressUtils {

    /**
     * 私有构造方法
     */
    private RemoteAddressUtils() {
        super();
    }

    /**
     * 获取远程IP地址
     *
     * @param request HTTP请求
     * @return ip地址
     */
    @Nullable
    public static String getIpAddress(HttpServletRequest request) {
        Asserts.notNull(request);
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取远程IP地址
     *
     * @param request HTTP请求
     * @return ip地址
     */
    public static String getRequiredIpAddress(HttpServletRequest request) {
        String ip = getIpAddress(request);
        Asserts.notNull(ip);
        return ip;
    }

    /**
     * 获取远程IP地址
     *
     * @param request HTTP请求
     * @return ip地址
     */
    @Nullable
    public static String getIpAddress(NativeWebRequest request) {
        Asserts.notNull(request);
        final HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
        Asserts.notNull(httpServletRequest);
        return getIpAddress(httpServletRequest);
    }

    /**
     * 获取远程IP地址
     *
     * @param request HTTP请求
     * @return ip地址
     */
    public static String getRequiredIpAddress(NativeWebRequest request) {
        String ip = getIpAddress(request);
        Asserts.notNull(ip);
        return ip;
    }

}
