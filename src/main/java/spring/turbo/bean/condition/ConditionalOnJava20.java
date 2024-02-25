/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;

import java.lang.annotation.*;

/**
 * Java 20
 *
 * @author 应卓
 *
 * @since 3.2.2
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ ElementType.TYPE, ElementType.METHOD })
@ConditionalOnJava(value = JavaVersion.TWENTY, range = ConditionalOnJava.Range.EQUAL_OR_NEWER)
public @interface ConditionalOnJava20 {
}
