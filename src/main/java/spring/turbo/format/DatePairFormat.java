/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import java.lang.annotation.*;

/**
 * @author 应卓
 * @see spring.turbo.format.DateTimeFormat
 * @see org.springframework.format.annotation.DateTimeFormat
 * @since 1.0.8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Deprecated
public @interface DatePairFormat {

    public String delimiter() default " @@ ";

    public String pattern() default "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public String[] fallbackPatterns() default {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss.SSS",
            "yyyy/MM/dd",
            "yyyy-M-d HH:mm:ss",
            "yyyy-M-d HH:mm:ss.SSS",
            "yyyy-M-d",
            "yyyy/M/d HH:mm:ss",
            "yyyy/M/d HH:mm:ss.SSS",
            "yyyy/M/d",
            "HH:mm:ss.SSSXXX"
    };

}
