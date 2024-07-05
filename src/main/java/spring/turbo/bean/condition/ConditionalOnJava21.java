package spring.turbo.bean.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;

import java.lang.annotation.*;

/**
 * Java 21
 *
 * @author 应卓
 * @since 3.2.2
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@ConditionalOnJava(value = JavaVersion.TWENTY_ONE, range = ConditionalOnJava.Range.EQUAL_OR_NEWER)
public @interface ConditionalOnJava21 {
}
