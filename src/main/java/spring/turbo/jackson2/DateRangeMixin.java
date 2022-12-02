/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import spring.turbo.bean.DateRange;
import spring.turbo.format.DateRangeFormatter;
import spring.turbo.jackson2.support.ParserJsonDeserializer;
import spring.turbo.jackson2.support.PrinterJsonSerializer;

/**
 * {@link DateRange} Jackson Mixin
 *
 * @author 应卓
 * @see DateRange
 * @since 1.3.0
 */
@JsonSerialize(using = DateRangeMixin.S.class)
@JsonDeserialize(using = DateRangeMixin.D.class)
public abstract class DateRangeMixin {

    public static class S extends PrinterJsonSerializer {
        public S() {
            super(DateRangeFormatter.class, new DateRangeFormatter());
        }
    }

    public static class D extends ParserJsonDeserializer {
        public D() {
            super(DateRangeFormatter.class, new DateRangeFormatter());
        }
    }

}
