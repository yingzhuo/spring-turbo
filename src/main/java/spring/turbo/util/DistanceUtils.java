package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

/**
 * 基于经纬度的距离计算工具
 *
 * @author 应卓
 * @since 3.2.4
 */
public final class DistanceUtils {

    /**
     * 私有构造方法
     */
    private DistanceUtils() {
    }

    /**
     * 计算两个点之间的距离
     *
     * @param lat1 第一点纬度
     * @param lon1 第一点经度
     * @param lat2 第二点纬度
     * @param lon2 第二点经度
     * @param unit 距离单位
     * @return 距离
     * @see DistanceUnit
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnit unit) {
        return distance(lat1, lon1, lat2, lon2, unit, Integer.MIN_VALUE, null);
    }

    /**
     * 计算两个点之间的距离
     *
     * @param lat1         第一点纬度
     * @param lon1         第一点经度
     * @param lat2         第二点纬度
     * @param lon2         第二点经度
     * @param unit         距离单位
     * @param scale        保留小数点后位数
     * @param roundingMode roundingMode
     * @return 距离
     * @see DistanceUnit
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnit unit, int scale,
                                  @Nullable RoundingMode roundingMode) {

        double theta = lon1 - lon2;
        double dist = sin(deg2rad(lat1)) * sin(deg2rad(lat2))
                + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(theta));
        dist = acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = switch (unit) {
            case MILES -> dist;
            case KILOMETERS -> dist * 1.609344;
            case NAUTICAL_MILES -> dist * 0.8684;
        };

        if (roundingMode == null) {
            return dist;
        } else {
            return BigDecimal.valueOf(dist).setScale(scale, roundingMode).doubleValue();
        }
    }

    private static double deg2rad(double deg) {
        return (deg * PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / PI);
    }

}
