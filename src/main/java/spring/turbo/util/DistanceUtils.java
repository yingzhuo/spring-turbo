/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import static java.lang.Math.*;

/**
 * 基于经纬度的距离计算工具
 *
 * @author 应卓
 *
 * @since 3.2.4
 */
public final class DistanceUtils {

    /**
     * 计算两个点之间的距离
     *
     * @param lat1
     *            第一点纬度
     * @param lon1
     *            第一点经度
     * @param lat2
     *            第二点纬度
     * @param lon2
     *            第二点经度
     * @param unit
     *            距离单位
     *
     * @return 距离
     *
     * @see DistanceUtils.Unit
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, Unit unit) {
        double theta = lon1 - lon2;
        double dist = sin(deg2rad(lat1)) * sin(deg2rad(lat2))
                + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(theta));
        dist = acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return switch (unit) {
        case MILES -> dist;
        case KILOMETERS -> dist * 1.609344;
        case NAUTICAL_MILES -> dist * 0.8684;
        };
    }

    private static double deg2rad(double deg) {
        return (deg * PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / PI);
    }

    /**
     * 距离单位
     */
    public static enum Unit {

        /**
         * 英里
         */
        MILES,

        /**
         * 千米
         */
        KILOMETERS,

        /**
         * 海里
         */
        NAUTICAL_MILES
    }

    /**
     * 私有构造方法
     */
    private DistanceUtils() {
        super();
    }

}
