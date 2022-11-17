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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import spring.turbo.bean.NumberZones;
import spring.turbo.format.NumberZonesParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author 应卓
 * @see NumberZones
 * @since 1.3.0
 */
@JsonSerialize(using = NumberZonesMixin.NumberZonesJsonSerializer.class)
@JsonDeserialize(using = NumberZonesMixin.NumberZonesJsonDeserializer.class)
public abstract class NumberZonesMixin {

    private static final NumberZonesParser PARSER = new NumberZonesParser();

    public static class NumberZonesJsonSerializer extends JsonSerializer<NumberZones> {
        @Override
        public void serialize(NumberZones value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class NumberZonesJsonDeserializer extends JsonDeserializer<NumberZones> {
        @Override
        public NumberZones deserialize(JsonParser p, DeserializationContext context) throws IOException {
            try {
                final String source = p.readValueAs(String.class);
                return PARSER.parse(source, Locale.getDefault());
            } catch (ParseException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }

}
