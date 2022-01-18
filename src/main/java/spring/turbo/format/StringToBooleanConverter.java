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
import org.springframework.lang.NonNull;
import spring.turbo.util.SetFactories;
import spring.turbo.util.StringFormatter;

import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class StringToBooleanConverter implements GenericConverter {

    private static final Set<ConvertiblePair> CONVERTIBLE_PAIRS = SetFactories.newUnmodifiableSet(new ConvertiblePair(String.class, Boolean.class));

    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String ON = "on";
    private static final String OFF = "off";
    private static final String ONE = "1";
    private static final String ZERO = "0";

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_PAIRS;
    }

    @Override
    public Object convert(Object source, @NonNull TypeDescriptor sourceType, @NonNull TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        final String text = source.toString().trim();

        if (TRUE.equalsIgnoreCase(text) || ON.equalsIgnoreCase(text) || ONE.equalsIgnoreCase(text)) {
            return Boolean.TRUE;
        }

        if (FALSE.equalsIgnoreCase(text) || OFF.equalsIgnoreCase(text) || ZERO.equalsIgnoreCase(text)) {
            return Boolean.FALSE;
        }

        final String msg = StringFormatter.format("'{}' is not a valid boolean", text);
        throw new IllegalArgumentException(msg);
    }

}
