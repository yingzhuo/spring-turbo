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
import spring.turbo.bean.DateRange;
import spring.turbo.bean.DateZones;
import spring.turbo.util.Asserts;
import spring.turbo.util.DateUtils;

import java.util.Date;

/**
 * @author 应卓
 *
 * @since 2.0.1
 */
public class DecentDateZonesConstraintValidator implements ConstraintValidator<DecentDateZones, DateZones> {

    @Nullable
    private DecentDateZones annotation;

    @Override
    public void initialize(DecentDateZones constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(@Nullable DateZones value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Asserts.notNull(this.annotation);

        // 检查长度
        if (value.size() < annotation.mixSize() || value.size() > annotation.maxSize()) {
            return false;
        }

        // 检查是否每一个都是有序的
        for (var current : value) {
            if (!current.isOrdered()) {
                return false;
            }
        }

        // 检查连续性
        if (annotation.mustBeContinuous()) {
            int index = 0;
            DateRange last = null;

            for (final DateRange current : value) {
                if (index != 0) {
                    final Date a = last.getRightInclude();
                    final Date b = current.getLeftInclude();

                    if (!DateUtils.isSameDay(b, DateUtils.addDays(a, 1))) {
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
