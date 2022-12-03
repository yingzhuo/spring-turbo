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
import spring.turbo.bean.BigDecimalPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.format.BigDecimalPairFormatter;
import spring.turbo.jackson2.support.ParserJsonDeserializer;
import spring.turbo.jackson2.support.PrinterJsonSerializer;

/**
 * {@link BigDecimalPair} Jackson Mixin
 *
 * @author 应卓
 * @see NumberPair
 * @see BigDecimalPair
 * @since 1.3.0
 */
@JsonSerialize(using = BigDecimalPairMixin.S.class)
@JsonDeserialize(using = BigDecimalPairMixin.D.class)
public abstract class BigDecimalPairMixin {

    public static class S extends PrinterJsonSerializer {
        public S() {
            super(BigDecimalPairFormatter.class, new BigDecimalPairFormatter());
        }
    }

    public static class D extends ParserJsonDeserializer {
        public D() {
            super(BigDecimalPairFormatter.class, new BigDecimalPairFormatter());
        }
    }

}
