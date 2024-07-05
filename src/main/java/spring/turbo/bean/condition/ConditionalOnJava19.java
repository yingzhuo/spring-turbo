package spring.turbo.bean.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;

import java.lang.annotation.*;

/**
 * Java 19
 *
 * @author 应卓
 * @since 2.0.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@ConditionalOnJava(value = JavaVersion.NINETEEN, range = ConditionalOnJava.Range.EQUAL_OR_NEWER)
public @interface ConditionalOnJava19 {
}
