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
import spring.turbo.bean.NumberZones;
import spring.turbo.util.Asserts;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
    public boolean isValid(NumberZones value, ConstraintValidatorContext context) {
        Asserts.notNull(value);
        // TODO: 有时间补全
        return true;
    }

}
