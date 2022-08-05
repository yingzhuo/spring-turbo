/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import java.time.DayOfWeek;
import java.time.temporal.WeekFields;

/**
 * @author 应卓
 * @see DateDim
 * @since 1.1.3
 */
public enum WeekOption {

    IOS(WeekFields.ISO),
    SUNDAY_START(WeekFields.of(DayOfWeek.SUNDAY, 1)),
    MONDAY_START(WeekFields.of(DayOfWeek.MONDAY, 1));

    private final WeekFields weekFields;

    WeekOption(WeekFields weekFields) {
        this.weekFields = weekFields;
    }

    public WeekFields getWeekFields() {
        return weekFields;
    }

}
