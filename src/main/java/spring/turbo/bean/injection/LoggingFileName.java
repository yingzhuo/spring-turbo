package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * {@code @Value("${logging.file.name}")} 的快捷方式
 *
 * @author 应卓
 * @see Value
 * @since 1.1.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Value("${logging.file.name}")
public @interface LoggingFileName {
}
