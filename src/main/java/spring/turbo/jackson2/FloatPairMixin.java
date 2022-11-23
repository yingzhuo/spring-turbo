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
import spring.turbo.bean.FloatPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.jackson2.support.AbstractNumberPairJsonDeserializer;

/**
 * @author 应卓
 * @see NumberPair
 * @see FloatPair
 * @since 1.3.0
 */
@JsonSerialize(using = FloatPairMixin.FloatPairJsonSerializer.class)
@JsonDeserialize(using = FloatPairMixin.FloatPairJsonDeserializer.class)
public abstract class FloatPairMixin {

    public static class FloatPairJsonSerializer extends AbstractToStringJsonSerializer<FloatPair> {
    }

    public static class FloatPairJsonDeserializer extends AbstractNumberPairJsonDeserializer<FloatPair> {
        public FloatPairJsonDeserializer() {
            super(FloatPair.class);
        }
    }

}
