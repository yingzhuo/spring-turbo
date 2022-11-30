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

import static java.util.Optional.ofNullable;

/**
 * 业务异常
 *
 * @author 应卓
 * @since 2.0.1
 */
public class BusinessException extends RuntimeException {

    public static BusinessException of(@Nullable String msg) {
        return new BusinessException(msg);
    }

    public static BusinessException of(@Nullable Throwable ex) {
        return of(ofNullable(ex).map(Throwable::getMessage).orElse(null));
    }

    /**
     * 构造方法
     */
    public BusinessException() {
        this(null);
    }

    /**
     * 构造方法
     *
     * @param message 异常信息
     */
    public BusinessException(@Nullable String message) {
        super(message);
    }

}
