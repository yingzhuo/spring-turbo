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
import spring.turbo.bean.NumberPair;
import spring.turbo.jackson2.support.AbstractNumberPairJsonDeserializer;

import java.io.IOException;

/**
 * @author 应卓
 * @see NumberPair
 * @since 1.3.0
 */
@JsonSerialize(using = NumberPairMixin.NumberPairJsonSerializer.class)
@JsonDeserialize(using = NumberPairMixin.NumberPairJsonDeserializer.class)
public abstract class NumberPairMixin {

    public static class NumberPairJsonSerializer extends JsonSerializer<NumberPair> {
        @Override
        public void serialize(NumberPair value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class NumberPairJsonDeserializer extends AbstractNumberPairJsonDeserializer<NumberPair> {
        public NumberPairJsonDeserializer() {
            super(NumberPair.class);
        }
    }

}
