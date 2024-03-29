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
import spring.turbo.bean.BigIntegerPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.format.BigIntegerPairFormatter;
import spring.turbo.jackson2.support.ParserJsonDeserializer;
import spring.turbo.jackson2.support.PrinterJsonSerializer;

/**
 * {@link BigIntegerPair} Jackson Mixin
 *
 * @author 应卓
 *
 * @see NumberPair
 * @see BigIntegerPair
 *
 * @since 1.3.0
 */
@JsonSerialize(using = BigIntegerPairMixin.S.class)
@JsonDeserialize(using = BigIntegerPairMixin.D.class)
public abstract class BigIntegerPairMixin {

    public static class S extends PrinterJsonSerializer {
        public S() {
            super(BigIntegerPairFormatter.class, new BigIntegerPairFormatter());
        }
    }

    public static class D extends ParserJsonDeserializer {
        public D() {
            super(BigIntegerPairFormatter.class, new BigIntegerPairFormatter());
        }
    }

}
