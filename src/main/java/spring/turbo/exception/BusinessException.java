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
import spring.turbo.core.SpringUtils;

import java.util.Locale;

import static spring.turbo.core.SpringUtils.UNSUPPORTED;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class BusinessException extends RuntimeException {

    @Nullable
    public final String code;

    public BusinessException(String message) {
        this(null, message);
    }

    public BusinessException(@Nullable String code, String message) {
        super(message);
        this.code = code;
    }

    public static BusinessException createInstance(String code) {
        return createInstance(code, null, (Object[]) null);
    }

    public static BusinessException createInstance(String code, @Nullable Object... args) {
        return createInstance(code, null, args);
    }

    public static BusinessException createInstance(String code, @Nullable Locale local) {
        return createInstance(code, local, (Object[]) null);
    }

    public static BusinessException createInstance(String code, @Nullable Locale locale, @Nullable Object... args) {
        return SpringUtils.getBean(BusinessExceptionFactory.class)
                .orElseThrow(UNSUPPORTED)
                .create(code, locale, args);
    }

    @Nullable
    public String getCode() {
        return code;
    }

}
