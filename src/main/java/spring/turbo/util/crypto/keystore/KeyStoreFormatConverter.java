package spring.turbo.util.crypto.keystore;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * @author 应卓
 * @since 3.4.0
 */
public class KeyStoreFormatConverter implements Converter<String, KeyStoreFormat> {

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public KeyStoreFormat convert(String source) {
        if (null == source) {
            return null;
        }
        return KeyStoreFormat.fromValue(source);
    }

}
