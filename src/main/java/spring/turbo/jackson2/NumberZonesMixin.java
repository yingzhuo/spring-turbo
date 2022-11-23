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
import spring.turbo.bean.NumberZones;
import spring.turbo.format.NumberPairFormatter;
import spring.turbo.format.NumberZonesFormatter;
import spring.turbo.jackson2.support.ParserJsonDeserializer;
import spring.turbo.jackson2.support.PrinterJsonSerializer;

/**
 * @author 应卓
 * @see NumberZones
 * @since 1.3.1
 */
@JsonSerialize(using = NumberZonesMixin.S.class)
@JsonDeserialize(using = NumberZonesMixin.D.class)
public abstract class NumberZonesMixin {

    public static class S extends PrinterJsonSerializer {
        public S() {
            super(NumberZonesFormatter.class, new NumberPairFormatter());
        }
    }

    public static class D extends ParserJsonDeserializer {
        public D() {
            super(NumberZonesFormatter.class, new NumberPairFormatter());
        }
    }

}
