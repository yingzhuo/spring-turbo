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
import spring.turbo.bean.NumberZones;
import spring.turbo.util.Asserts;

import java.math.BigDecimal;

/**
 * @author 应卓
 * @see NumberZones
 * @since 1.1.4
 */
public class DecentNumberZonesValidator implements ConstraintValidator<DecentNumberZones, NumberZones> {

    @Nullable
    private DecentNumberZones annotation;

    @Override
    public void initialize(DecentNumberZones annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(@Nullable NumberZones value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        Asserts.notNull(this.annotation);

        // 检查长度
        if (value.size() < annotation.mixSize() || value.size() > annotation.maxSize()) {
            return false;
        }

        // 检查连续性
        if (annotation.mustBeContinuous()) {

            int index = 0;
            NumberPair last = null;

            for (final NumberPair current : value) {
                if (index != 0) {
                    final BigDecimal a = last.getRight(BigDecimal.class);
                    final BigDecimal b = current.getLeft(BigDecimal.class);

                    if (b.subtract(a).compareTo(BigDecimal.valueOf(annotation.interval())) != 0) {
                        return false;
                    }
                }
                index++;
                last = current;
            }

        }

        return true;
    }

}
