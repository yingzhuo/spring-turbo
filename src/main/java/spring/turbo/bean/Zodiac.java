/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

/**
 * 黄道十二宫
 *
 * @author 应卓
 * @see DateDescriptor
 * @since 1.1.4
 */
public enum Zodiac {

    /**
     * 白羊
     */
    ARIES,

    /**
     * 金牛
     */
    TAURUS,

    /**
     * 双子
     */
    GEMINI,

    /**
     * 巨蟹
     */
    CANCER,

    /**
     * 狮子
     */
    LEO,

    /**
     * 处女
     */
    VIRGO,

    /**
     * 天秤
     */
    LIBRA,

    /**
     * 天蝎
     */
    SCORPIO,

    /**
     * 人马
     */
    SAGITTARIUS,

    /**
     * 摩羯
     */
    CAPRICORN,

    /**
     * 水瓶
     */
    AQUARIUS,

    /**
     * 双鱼
     */
    PISCES;

    public static Zodiac getZodiac(int month, int day) {
        switch (month) {
            case 1:
                return day >= 20 ? AQUARIUS : CAPRICORN;
            case 2:
                return day >= 19 ? PISCES : AQUARIUS;
            case 3:
                return day >= 21 ? ARIES : PISCES;
            case 4:
                return day >= 20 ? TAURUS : ARIES;
            case 5:
                return day >= 21 ? GEMINI : TAURUS;
            case 6:
                return day >= 21 ? CANCER : GEMINI;
            case 7:
                return day >= 23 ? LEO : CANCER;
            case 8:
                return day >= 23 ? VIRGO : LEO;
            case 9:
                return day >= 23 ? LIBRA : VIRGO;
            case 10:
                return day >= 23 ? SCORPIO : LIBRA;
            case 11:
                return day >= 22 ? SAGITTARIUS : SCORPIO;
            case 12:
                return day >= 22 ? CAPRICORN : SAGITTARIUS;
            default:
                throw new IllegalArgumentException("invalid day or month");
        }
    }

}
