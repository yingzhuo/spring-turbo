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
    }

    /**
     * 查询是否支持验证
     *
     * @param targetType 待验证的类型
     * @return 结果
     */
    public static boolean support(Class<?> targetType) {
        Asserts.notNull(targetType);
        final Validator validator = SpringUtils.getValidator();
        return validator.supports(targetType);
    }

    /**
     * 验证
     *
     * @param obj 待验证的对象
     * @return 验证结果
     */
    public static BindingResult validate(Object obj) {
        Asserts.notNull(obj);
        final String objectName = StringFormatter.format("bean[{}]", System.identityHashCode(obj));
        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(obj, objectName);
        final Validator validator = SpringUtils.getValidator();
        validator.validate(obj, errors);
        return errors;
    }

}
