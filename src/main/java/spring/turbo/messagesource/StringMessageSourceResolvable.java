/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.messagesource;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

/**
 * {@link MessageSourceResolvable} 简易实现
 *
 * @param errorMessage 默认错误信息
 * @author 应卓
 * @see MessageSourceResolvable#getDefaultMessage()
 * @since 3.3.2
 */
public record StringMessageSourceResolvable(String errorMessage) implements MessageSourceResolvable {

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

    @Override
    public String getDefaultMessage() {
        return errorMessage;
    }

}
