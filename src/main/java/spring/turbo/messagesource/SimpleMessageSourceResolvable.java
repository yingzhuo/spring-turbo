package spring.turbo.messagesource;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;
import spring.turbo.util.ArrayUtils;
import spring.turbo.util.StringUtils;

import java.io.Serializable;

/**
 * {@link MessageSourceResolvable} 简单实现
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class SimpleMessageSourceResolvable implements MessageSourceResolvable, Serializable {

    @Nullable
    private final String code;

    @Nullable
    private final Object[] arguments;

    @Nullable
    private final String defaultMessages;

    public SimpleMessageSourceResolvable(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessages) {
        this.code = StringUtils.blankToNull(code);
        this.arguments = ArrayUtils.emptyToNull(arguments);
        this.defaultMessages = StringUtils.blankToNull(defaultMessages);
    }

    @Nullable
    @Override
    public String[] getCodes() {
        return code != null ? new String[]{code} : null;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessages;
    }

}
