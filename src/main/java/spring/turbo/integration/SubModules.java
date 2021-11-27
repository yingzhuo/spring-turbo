/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.integration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class SubModules {

    /**
     * 所有子模块名称
     */
    public static final SortedSet<String> ALL_MODULE_NAMES;

    /**
     * Converter(s)
     */
    public static final List<Converter<?, ?>> ALL_CONVERTERS_CONVERTERS;

    /**
     * GenericConverter(s)
     */
    public static final List<GenericConverter> ALL_GENERIC_CONVERTERS;

    /**
     * ConverterFactory(S)
     */
    public static final List<ConverterFactory<?, ?>> ALL_CONVERTER_FACTORIES;

    /**
     * Provider(s)
     */
    private static final List<ModuleConvertersProvider> ALL_CONVERTERS_PROVIDERS = ServiceLoaderUtils.loadQuietly(ModuleConvertersProvider.class);

    static {
        final Set<String> set = ServiceLoaderUtils.loadQuietly(ModuleNameProvider.class)
                .stream()
                .map(ModuleNameProvider::getModuleName)
                .collect(Collectors.toSet());

        ALL_MODULE_NAMES = Collections.unmodifiableSortedSet(new TreeSet<>(set));
    }

    static {
        List<Converter<?, ?>> list = new LinkedList<>();

        for (ModuleConvertersProvider provider : ALL_CONVERTERS_PROVIDERS) {
            Collection<Converter<?, ?>> cs = provider.getConverters();
            if (cs != null) {
                list.addAll(cs);
            }
        }

        ALL_CONVERTERS_CONVERTERS = Collections.unmodifiableList(list);
    }

    static {
        List<GenericConverter> list = new LinkedList<>();

        for (ModuleConvertersProvider provider : ALL_CONVERTERS_PROVIDERS) {
            Collection<GenericConverter> cs = provider.getGenericConverters();
            if (cs != null) {
                list.addAll(cs);
            }
        }

        ALL_GENERIC_CONVERTERS = Collections.unmodifiableList(list);
    }

    static {
        List<ConverterFactory<?, ?>> list = new LinkedList<>();

        for (ModuleConvertersProvider provider : ALL_CONVERTERS_PROVIDERS) {
            Collection<ConverterFactory<?, ?>> cs = provider.getConverterFactories();
            if (cs != null) {
                list.addAll(cs);
            }
        }

        ALL_CONVERTER_FACTORIES = Collections.unmodifiableList(list);
    }

    private SubModules() {
        super();
    }

}
