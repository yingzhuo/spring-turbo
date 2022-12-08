/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.message;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import spring.turbo.util.ClassUtils;
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.ServiceLoaderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 复合型消息源
 *
 * @author 应卓
 * @since 2.0.3
 */
public final class CompositeMessageSource implements MessageSource {

    private final List<MessageSource> sources = new ArrayList<>();

    public CompositeMessageSource(@Nullable MessageSource... sources) {
        // 指定的全部添加
        CollectionUtils.nullSafeAddAll(this.sources, sources);

        // SPI
        MessageSource loadedFromServices = this.loadFromServices();
        if (loadedFromServices != null) {
            this.sources.add(loadedFromServices);
        }

        // 如果实在没有拉个垫背的
        if (CollectionUtils.isEmpty(this.sources)) {
            this.sources.add(NullMessageSource.getInstance());
        }
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        for (final var source : this.sources) {
            var msg = source.getMessage(code, args, defaultMessage, locale);
            if (msg != null) {
                return msg;
            }
        }
        return null;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        for (final var source : this.sources) {
            try {
                return source.getMessage(code, args, locale);
            } catch (NoSuchMessageException ignored) {
                // nop
            }
        }
        throw new NoSuchMessageException(code, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        for (final var source : this.sources) {
            try {
                return source.getMessage(resolvable, locale);
            } catch (NoSuchMessageException ignored) {
                // nop
            }
        }

        final var codes = resolvable.getCodes();
        final var code = (codes != null && codes.length > 0 ? codes[0] : EMPTY);
        throw new NoSuchMessageException(code, locale);
    }

    @Nullable
    private MessageSource loadFromServices() {
        var services = ServiceLoaderUtils.loadQuietly(MessageSourceLocatorService.class, ClassUtils.getDefaultClassLoader());
        if (CollectionUtils.isEmpty(services)) {
            return null;
        }

        final var basenames = new ArrayList<String>();
        for (var service : services) {
            CollectionUtils.nullSafeAddAll(basenames, service.getBasenames());
        }

        var source = new ResourceBundleMessageSource();
        source.setBeanClassLoader(CompositeMessageSource.class.getClassLoader());
        source.addBasenames(basenames.toArray(new String[0]));
        return source;
    }

}
