/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.validation.Errors;
import spring.turbo.util.Asserts;

/**
 * 数据检查异常
 *
 * @author 应卓
 * @since 1.0.11
 */
public class ValidationException extends RuntimeException {

    private final Errors errors;

    private ValidationException(Errors errors) {
        Asserts.notNull(errors);
        this.errors = errors;
    }

    public static void raiseIfNecessary(Errors errors) {
        Asserts.notNull(errors);
        if (errors.hasErrors()) {
            throw from(errors);
        }
    }

    public static ValidationException from(Errors errors) {
        Asserts.notNull(errors);
        Asserts.isTrue(errors.hasErrors());
        return new ValidationException(errors);
    }

    public Errors getErrors() {
        return errors;
    }

}
