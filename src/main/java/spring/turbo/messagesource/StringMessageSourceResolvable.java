package spring.turbo.messagesource;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.io.Serializable;

/**
 * {@link MessageSourceResolvable} 简易实现
 *
 * @param errorMessage 默认错误信息
 * @author 应卓
 * @see MessageSourceResolvable#getDefaultMessage()
 * @since 3.3.1
 */
public record StringMessageSourceResolvable(String errorMessage) implements MessageSourceResolvable, Serializable {

    public StringMessageSourceResolvable {
        Asserts.hasText(errorMessage, "errorMessage is required");
    }

    @Nullable
    @Override
    public String[] getCodes() {
        return null;
    }

    @Nullable
    @Override
    public Object[] getArguments() {
        return null;
    }

    @NonNull
    @Override
    public String getDefaultMessage() {
        return errorMessage;
    }

}
