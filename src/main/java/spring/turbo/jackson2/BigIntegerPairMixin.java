/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import spring.turbo.bean.BigIntegerPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.jackson2.support.AbstractNumberPairJsonDeserializer;

import java.io.IOException;

/**
 * @author 应卓
 * @see NumberPair
 * @see BigIntegerPair
 * @since 1.3.0
 */
@JsonSerialize(using = BigIntegerPairMixin.BigIntegerPairJsonSerializer.class)
@JsonDeserialize(using = BigIntegerPairMixin.BigIntegerPairJsonDeserializer.class)
public abstract class BigIntegerPairMixin {

    public static class BigIntegerPairJsonSerializer extends JsonSerializer<BigIntegerPair> {
        @Override
        public void serialize(BigIntegerPair value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class BigIntegerPairJsonDeserializer extends AbstractNumberPairJsonDeserializer<BigIntegerPair> {
        public BigIntegerPairJsonDeserializer() {
            super(BigIntegerPair.class);
        }
    }

}
