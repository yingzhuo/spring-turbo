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
import spring.turbo.util.EnumUtils;

import java.util.Optional;

/**
 * @author 应卓
 * @since 3.3.1
 */
public class KeyStoreFormatEditor extends AbstractPropertyEditor<KeyStoreFormat> {

    @Override
    protected KeyStoreFormat convert(String text) {

        if ("fpx".equalsIgnoreCase(text) || "p12".equalsIgnoreCase(text) || "pkcs#12".equalsIgnoreCase(text) || "pkcs12".equalsIgnoreCase(text)) {
            return KeyStoreFormat.PKCS12;
        }

        if ("jks".equalsIgnoreCase(text)) {
            return KeyStoreFormat.JKS;
        }

        var format = EnumUtils.getEnumIgnoreCase(KeyStoreFormat.class, text);
        return Optional.ofNullable(format)
                .orElseThrow(IllegalArgumentException::new);
    }

}
