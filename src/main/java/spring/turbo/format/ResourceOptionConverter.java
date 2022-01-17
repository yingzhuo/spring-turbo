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
import org.springframework.core.io.Resource;
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.SetFactories;

import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class ResourceOptionConverter implements GenericConverter {

    private static final Set<ConvertiblePair> CONVERTIBLE_PAIRS = SetFactories.newUnmodifiableSet(
            new ConvertiblePair(CharSequence.class, ResourceOption.class),
            new ConvertiblePair(Resource.class, ResourceOption.class)
    );

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_PAIRS;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        if (source instanceof CharSequence) {
            return ResourceOptions.fromSeparatedLocations(source.toString());
        }

        if (source instanceof Resource) {
            return ResourceOptions.of((Resource) source);
        }

        return null;
    }

}
