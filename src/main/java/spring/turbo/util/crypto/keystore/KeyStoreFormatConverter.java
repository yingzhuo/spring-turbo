package spring.turbo.util.crypto.keystore;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * {@link Converter} 实现
 *
 * @author 应卓
 * @see KeyStoreFormat#of(String)
 * @since 3.4.0
 */
public class KeyStoreFormatConverter implements Converter<String, KeyStoreFormat> {

    // 对于 string -> enum
    // EditorProperty 优先级不够，不能生效
    // ObjectToObjectConverter 优先级也不够

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public KeyStoreFormat convert(String source) {
        return KeyStoreFormat.of(source);
    }

}
