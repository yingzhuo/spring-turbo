package spring.turbo.messagesource;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * {@link MessageSourceResolvable} 简单实现
 *
 * @param defaultMessage 默认错误信息
 * @author 应卓
 * @see MessageSourceResolvable#getDefaultMessage()
 * @since 3.3.1
 */
public record StringMessageSourceResolvable(String defaultMessage) implements MessageSourceResolvable, Serializable {

    /**
     * 构造方法
     *
     * @param defaultMessage 默认错误文本
     */
    public StringMessageSourceResolvable {
        Assert.hasText(defaultMessage, "defaultMessage is required");
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String[] getCodes() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Object[] getArguments() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }

}
