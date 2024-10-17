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
public final class KeyStoreFormatConverter implements Converter<String, KeyStoreFormat> {

    // 对于 string -> enum
    // EditorProperty 优先级不够，不能生效
    // ObjectToObjectConverter 优先级也不够

    /**
     * 私有构造方法
     */
    private KeyStoreFormatConverter() {
    }

    public static KeyStoreFormatConverter getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public KeyStoreFormat convert(String source) {
        return KeyStoreFormat.of(source);
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final KeyStoreFormatConverter INSTANCE = new KeyStoreFormatConverter();
    }

}
