package spring.turbo.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;

import java.util.Locale;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 空消息源
 *
 * @author 应卓
 * @see #getInstance()
 * @since 2.0.3
 */
public final class NullMessageSource implements MessageSource {

    /**
     * 私有构造方法
     */
    private NullMessageSource() {
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static NullMessageSource getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Nullable
    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return null;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        throw new NoSuchMessageException(code, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        final var codes = resolvable.getCodes();
        final var code = (codes != null && codes.length >= 1) ? codes[0] : EMPTY;
        throw new NoSuchMessageException(code, locale);
    }

    // -----------------------------------------------------------------------------------------------------------------

    // 延迟加载
    private static class SyncAvoid {
        private static final NullMessageSource INSTANCE = new NullMessageSource();
    }

}
