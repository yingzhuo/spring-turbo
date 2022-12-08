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
import spring.turbo.lang.Singleton;

import java.util.Locale;

/**
 * @author 应卓
 * @since 2.0.3
 */
@Singleton
public final class NullMessageSource implements MessageSource {

    public static NullMessageSource getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * 私有构造方法
     */
    private NullMessageSource() {
        super();
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return null;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return null;
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }

    private static class SyncAvoid {
        private static final NullMessageSource INSTANCE = new NullMessageSource();
    }

}
