/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.lang.Nullable;

/**
 * 业务异常
 *
 * @author 应卓
 * @since 3.3.2
 */
public final class BusinessException extends AbstractMessageResolvableException {

    public static BusinessException of(String defaultMsg) {
        throw new BusinessException(defaultMsg);
    }

    public static BusinessException of(String code, @Nullable Object[] arguments) {
        throw new BusinessException(code, arguments);
    }

    public static BusinessException of(String code, @Nullable Object[] arguments, String defaultMessage) {
        throw new BusinessException(code, arguments, defaultMessage);
    }

    private BusinessException(String code, @Nullable Object[] arguments) {
        super(code, arguments);
    }

    private BusinessException(String defaultMessage) {
        super(defaultMessage);
    }

    private BusinessException(String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(code, arguments, defaultMessage);
    }

}
