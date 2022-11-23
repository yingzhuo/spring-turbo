/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import spring.turbo.bean.BigIntegerPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.jackson2.support.AbstractNumberPairJsonDeserializer;

/**
 * @author 应卓
 * @see NumberPair
 * @see BigIntegerPair
 * @since 1.3.0
 */
@JsonDeserialize(using = BigIntegerPairMixin.BigIntegerPairJsonDeserializer.class)
public abstract class BigIntegerPairMixin {

    @JsonValue
    public abstract String toString();

    public static class BigIntegerPairJsonDeserializer extends AbstractNumberPairJsonDeserializer<BigIntegerPair> {
        public BigIntegerPairJsonDeserializer() {
            super(BigIntegerPair.class);
        }
    }

}
