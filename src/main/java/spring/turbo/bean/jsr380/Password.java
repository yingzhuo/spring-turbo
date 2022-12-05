/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 口令验证元注释
 *
 * @author 应卓
 * @since 1.0.0
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    public static final String DEFAULT_SPECIAL_CHARS = "\"',./<>?;:'{}[]+=-_!@#$%^&*()`~";

    public Complexity complexity() default Complexity.ANY;

    public String specialChars() default DEFAULT_SPECIAL_CHARS;

    public int min() default Integer.MIN_VALUE;

    public int max() default Integer.MAX_VALUE;

    public String message();

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * 密码的复杂度
     */
    public enum Complexity {

        /**
         * 无要求
         */
        ANY,

        /**
         * 包含数字 (0-9)
         */
        HAS_NUMERIC,

        /**
         * 包含且只包含数字 (0-9)
         */
        ONLY_NUMERIC,

        /**
         * 包含字母 (a-z, A-Z)
         */
        HAS_ALPHABETIC,

        /**
         * 包含且只包含字母 (a-z, A-Z)
         */
        ONLY_ALPHABETIC,

        /**
         * 字母 + 数字 (a-z, A-Z, 0-9)
         */
        ALPHABETIC_AND_NUMERIC,

        /**
         * 字母 + 数字 + 特殊字符
         */
        ALPHABETIC_AND_NUMERIC_AND_SPECIAL_CHARS,

        /**
         * 小写字母 + 大写字母 + 数字
         */
        LOWER_AND_UPPER_AND_NUMERIC,

        /**
         * 小写字母 + 大写字母 + 数字 + 特殊字符
         */
        LOWER_AND_UPPER_AND_NUMERIC_AND_SPECIAL_CHARS,

        /**
         * 字母 数字 特殊字符 中至少两种
         */
        AT_LEAST_TWO_KIND_OF_ALPHABETIC_AND_NUMERIC_AND_SPECIAL_CHARS
    }

}
