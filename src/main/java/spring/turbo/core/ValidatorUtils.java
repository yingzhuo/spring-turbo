/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

/**
 * @author 应卓
 * @see SpringUtils
 * @see Validator
 * @since 1.0.0
 */
public final class ValidatorUtils {

    /**
     * 私有构造方法
     */
    private ValidatorUtils() {
        super();
    }

    public static boolean support(Class<?> targetType) {
        Asserts.notNull(targetType);
        final Validator validator = SpringUtils.getValidator();
        return validator.supports(targetType);
    }

    public static BindingResult validate(Object obj) {
        Asserts.notNull(obj);
        final String objectName = StringFormatter.format("bean[{}]", System.identityHashCode(obj));
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(obj, objectName);
        final Validator validator = SpringUtils.getValidator();
        validator.validate(obj, errors);
        return errors;
    }

}
