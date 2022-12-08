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
import spring.turbo.bean.NumberPair;
import spring.turbo.util.Asserts;

/**
 * @author 应卓
 * @since 2.0.3
 */
public class DecentNumberPairValidator implements ConstraintValidator<DecentNumberPair, NumberPair> {

    private DecentNumberPair annotation;

    @Override
    public void initialize(DecentNumberPair annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(@Nullable NumberPair value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // 检查左右顺序
        if (!value.isOrdered()) {
            return false;
        }

        Asserts.notNull(annotation);

        final var min = annotation.min();
        final var max = annotation.max();
        final double left = value.getLeft(Double.class);
        final double right = value.getLeft(Double.class);
        return (min <= left && left <= max) && (min <= right && right <= max);
    }

}
