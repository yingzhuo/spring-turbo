/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.integration.impl;

import org.springframework.core.convert.converter.GenericConverter;
import spring.turbo.integration.ModuleConvertersProvider;
import spring.turbo.io.ResourceOptionConverter;
import spring.turbo.util.crypto.CryptoConverter;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ModuleConvertersProviderImpl implements ModuleConvertersProvider {

    public ModuleConvertersProviderImpl() {
        super();
    }

    @Override
    public Collection<GenericConverter> getGenericConverters() {
        return Arrays.asList(
                new ResourceOptionConverter(),
                new CryptoConverter()
        );
    }

}
