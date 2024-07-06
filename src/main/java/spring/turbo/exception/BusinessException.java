package spring.turbo.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;

/**
 * 业务异常
 *
 * @author 应卓
 * @see BusinessAsserts
 * @since 3.3.1
 */
public final class BusinessException extends AbstractMessageResolvableException implements MessageSourceResolvable {

    /**
     * 构造方法
     *
     * @param code      解析错误信息用code
     * @param arguments 解析错误信息用参数
     * @see MessageSourceResolvable
     */
    public BusinessException(@Nullable String code, @Nullable Object... arguments) {
        super(code, arguments);
    }

    /**
     * 构造方法
     *
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public BusinessException(@Nullable String defaultMessage) {
        super(defaultMessage);
    }

    /**
     * 构造方法
     *
     * @param code           解析错误信息用code
     * @param arguments      解析错误信息用参数
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public BusinessException(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(code, arguments, defaultMessage);
    }

}
