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
import spring.turbo.bean.LongPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.format.LongPairFormatter;
import spring.turbo.jackson2.support.ParserJsonDeserializer;
import spring.turbo.jackson2.support.PrinterJsonSerializer;

/**
 * @author 应卓
 * @see NumberPair
 * @see LongPair
 * @since 1.3.0
 */
@JsonSerialize(using = LongPairMixin.S.class)
@JsonDeserialize(using = LongPairMixin.D.class)
public abstract class LongPairMixin {

    public static class S extends PrinterJsonSerializer {
        public S() {
            super(LongPairFormatter.class, new LongPairFormatter());
        }
    }

    public static class D extends ParserJsonDeserializer {
        public D() {
            super(LongPairFormatter.class, new LongPairFormatter());
        }
    }

}
