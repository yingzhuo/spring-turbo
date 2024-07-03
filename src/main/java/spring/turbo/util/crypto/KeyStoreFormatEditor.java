/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.databinding.AbstractPropertyEditor;

import java.util.Optional;

/**
 * {@link KeyStoreFormat} 专用转换器
 *
 * @author 应卓
 * @since 3.3.1
 */
public class KeyStoreFormatEditor extends AbstractPropertyEditor<KeyStoreFormat> {

    /**
     * 默认构造方法
     */
    public KeyStoreFormatEditor() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected KeyStoreFormat convert(String text) {
        return Optional.ofNullable(KeyStoreFormat.fromValue(text))
                .orElseThrow(IllegalArgumentException::new);
    }

}
