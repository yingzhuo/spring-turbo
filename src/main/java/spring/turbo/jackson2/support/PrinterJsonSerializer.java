/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.format.Printer;
import spring.turbo.core.SpringUtils;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.util.Locale;

/**
 * @author 应卓
 *
 * @see Printer
 * @see org.springframework.format.Formatter
 *
 * @since 1.3.1
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class PrinterJsonSerializer extends JsonSerializer {

    private final Class printerBeanType;
    private final Printer defaultPrinter;

    public PrinterJsonSerializer(Class printerBeanType, Printer defaultPrinter) {
        Asserts.notNull(printerBeanType);
        Asserts.notNull(printerBeanType);
        this.printerBeanType = printerBeanType;
        this.defaultPrinter = defaultPrinter;
    }

    @Override
    public final void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            final Printer printer = (Printer) SpringUtils.getBean(printerBeanType).orElse(defaultPrinter);
            gen.writeString(printer.print(value, Locale.getDefault()));
        } catch (Throwable e) {
            throw new IOException(e.getMessage());
        }
    }

}
