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
 * @author 应卓
 * @see DateRange
 * @since 1.3.0
 */
@JsonSerialize(using = DateRangeMixin.DateRangeJsonSerializer.class)
@JsonDeserialize(using = DateRangeMixin.DateRangeJsonDeserializer.class)
public abstract class DateRangeMixin {

    public static class DateRangeJsonSerializer extends PrinterJsonSerializer {
        public DateRangeJsonSerializer() {
            super(DateRangeFormatter.class, new DateRangeFormatter());
        }
    }

    public static class DateRangeJsonDeserializer extends ParserJsonDeserializer {
        public DateRangeJsonDeserializer() {
            super(DateRangeFormatter.class, new DateRangeFormatter());
        }
    }

}
