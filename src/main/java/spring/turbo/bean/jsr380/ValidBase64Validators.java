/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;
import spring.turbo.util.Base64;

/**
 * @author 应卓
 *
 * @since 2.2.4
 */
public final class ValidBase64Validators {

    public final static class ForString implements ConstraintValidator<ValidBase64, String> {

        @Override
        public boolean isValid(@Nullable String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return Base64.isBase64(value);
        }
    }

    public final static class ForBytes implements ConstraintValidator<ValidBase64, byte[]> {

        @Override
        public boolean isValid(@Nullable byte[] value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return Base64.isBase64(value);
        }
    }

}
