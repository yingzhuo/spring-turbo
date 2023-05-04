/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.lang.Nullable;
import spring.turbo.util.NumberParseUtils;
import spring.turbo.webmvc.api.Json;

import java.io.IOException;

/**
 * {@link Json} 相关Mixin
 *
 * @author 应卓
 *
 * @see Json
 *
 * @since 1.3.0
 */
public final class JsonMixins {

    /**
     * 私有构造方法
     */
    private JsonMixins() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @JsonIgnoreProperties("deprecated")
    public static abstract class Style1 {
        @JsonSerialize(using = ToLong.class)
        public abstract String getCode();

        @JsonProperty("error")
        public abstract String getErrorMessage();

        @JsonProperty("data")
        @JsonPropertyOrder(alphabetic = true)
        public abstract Object getPayload();

        public static class ToLong extends JsonSerializer<String> {
            @Override
            public void serialize(@Nullable String value, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                if (value == null) {
                    gen.writeNull();
                    return;
                }
                final Long n = NumberParseUtils.parse(value, Long.class);
                gen.writeNumber(n);
            }
        }
    }

    @JsonIgnoreProperties("deprecated")
    public static abstract class Style2 {

        @JsonProperty("error")
        public abstract String getErrorMessage();

        @JsonProperty("data")
        @JsonPropertyOrder(alphabetic = true)
        public abstract Object getPayload();
    }

}
