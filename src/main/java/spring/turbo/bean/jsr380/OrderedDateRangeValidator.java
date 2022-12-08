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

/**
 * @author 应卓
 * @since 2.0.1
 */
@Deprecated(forRemoval = true) // 逻辑过于混乱，不好用
public class OrderedDateRangeValidator implements ConstraintValidator<OrderedDateRange, DateRange> {

    @Override
    public boolean isValid(@Nullable DateRange value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.isOrdered();
    }

}
