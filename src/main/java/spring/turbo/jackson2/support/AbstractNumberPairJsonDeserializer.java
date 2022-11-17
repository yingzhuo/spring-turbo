/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.core.convert.TypeDescriptor;
import spring.turbo.bean.NumberPair;
import spring.turbo.format.StringToNumberPairConverter;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.util.Objects;

/**
 * (内部使用)
 *
 * @author 应卓
 * @since 1.0.14
 */
public abstract class AbstractNumberPairJsonDeserializer<T extends NumberPair> extends JsonDeserializer<T> {

    protected static final StringToNumberPairConverter CONVERTER = new StringToNumberPairConverter();

    private final TypeDescriptor descTypeDescriptor;

    protected AbstractNumberPairJsonDeserializer(Class<T> descType) {
        Asserts.notNull(descType);
        this.descTypeDescriptor = TypeDescriptor.valueOf(descType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        final String source = p.readValueAs(String.class);
        return (T) Objects.requireNonNull(CONVERTER.convert(source, TypeDescriptor.valueOf(String.class), descTypeDescriptor));
    }

}
