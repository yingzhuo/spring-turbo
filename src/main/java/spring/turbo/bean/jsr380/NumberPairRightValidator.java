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
public class NumberPairRightValidator implements ConstraintValidator<NumberPairRight, NumberPair> {

    private NumberPairRight annotation;

    public NumberPairRightValidator() {
        super();
    }

    @Override
    public boolean isValid(@Nullable NumberPair value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        final double right = value.getLeft(Double.class);
        return right >= annotation.min() && right <= annotation.max();
    }

    @Override
    public void initialize(NumberPairRight constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

}
