/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;
import spring.turbo.util.StringFormatter;

import java.util.Set;

import static spring.turbo.util.StringPool.*;

/**
 * @author 应卓
 *
 * @since 1.0.8
 */
public class StringToBooleanConverter implements GenericConverter {

    /**
     * 构造方法
     */
    public StringToBooleanConverter() {
        super();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Set.of(new ConvertiblePair(String.class, Boolean.class));
    }

    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        final String text = source.toString().trim();

        if (TRUE.equalsIgnoreCase(text) || ON.equalsIgnoreCase(text) || ONE.equalsIgnoreCase(text)) {
            return TRUE;
        }

        if (FALSE.equalsIgnoreCase(text) || OFF.equalsIgnoreCase(text) || ZERO.equalsIgnoreCase(text)) {
            return Boolean.FALSE;
        }

        final String msg = StringFormatter.format("'{}' is not a valid boolean", text);
        throw new IllegalArgumentException(msg);
    }

}
