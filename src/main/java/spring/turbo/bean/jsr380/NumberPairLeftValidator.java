/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

import org.springframework.lang.Nullable;
import spring.turbo.bean.NumberPair;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class NumberPairLeftValidator implements ConstraintValidator<NumberPairLeft, NumberPair> {

    private NumberPairLeft annotation;

    public NumberPairLeftValidator() {
        super();
    }

    @Override
    public boolean isValid(@Nullable NumberPair value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        final double left = value.getLeft(Double.class);
        return left >= annotation.min() && left <= annotation.max();
    }

    @Override
    public void initialize(NumberPairLeft constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

}
