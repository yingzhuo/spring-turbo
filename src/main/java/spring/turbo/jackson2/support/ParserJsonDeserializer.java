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
import org.springframework.format.Parser;
import spring.turbo.core.SpringUtils;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.util.Locale;

/**
 * @author 应卓
 * @see Parser
 * @see org.springframework.format.Formatter
 * @since 1.3.1
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class ParserJsonDeserializer extends JsonDeserializer {

    private final Class parserBeanType;
    private final Parser defaultParser;

    public ParserJsonDeserializer(Class parserBeanType, Parser defaultParser) {
        Asserts.notNull(parserBeanType);
        Asserts.notNull(defaultParser);
        this.parserBeanType = parserBeanType;
        this.defaultParser = defaultParser;
    }

    @Override
    public final Object deserialize(JsonParser p, DeserializationContext cxt) throws IOException {
        try {
            final Parser parser = (Parser) SpringUtils.getBean(parserBeanType).orElse(defaultParser);
            return parser.parse(p.getValueAsString(), Locale.getDefault());
        } catch (Throwable e) {
            throw new IOException(e.getMessage());
        }
    }

}
