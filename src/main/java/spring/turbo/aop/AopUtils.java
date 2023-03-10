/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationAttributes;
import spring.turbo.core.AnnotationFinder;
import spring.turbo.util.Asserts;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Aspect辅助工具
 *
 * @author 应卓
 * @since 2.1.0
 */
public final class AopUtils {

    /**
     * 私有构造方法
     */
    private AopUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Class<?> getTargetClass(ProceedingJoinPoint jp) {
        Asserts.notNull(jp);
        return jp.getTarget().getClass();
    }

    @Nullable
    public static <A extends Annotation> A getTargetClassAnnotation(ProceedingJoinPoint jp, Class<A> annotationType) {
        Asserts.notNull(annotationType);
        return AnnotationFinder.findAnnotation(getTargetClass(jp), annotationType);
    }

    public static <A extends Annotation> AnnotationAttributes getTargetClassAnnotationAttributes(ProceedingJoinPoint jp, Class<A> annotationType) {
        Asserts.notNull(annotationType);
        return AnnotationFinder.findAnnotationAttributes(getTargetClass(jp), annotationType);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Method getTargetMethod(ProceedingJoinPoint jp) {
        Asserts.notNull(jp);
        return ((MethodSignature) jp.getSignature()).getMethod();
    }

    @Nullable
    public static <A extends Annotation> A getTargetMethodAnnotation(ProceedingJoinPoint jp, Class<A> annotationType) {
        Asserts.notNull(annotationType);
        return AnnotationFinder.findAnnotation(getTargetMethod(jp), annotationType);
    }

    public static <A extends Annotation> AnnotationAttributes getTargetMethodAnnotationAttributes(ProceedingJoinPoint jp, Class<A> annotationType) {
        Asserts.notNull(annotationType);
        return AnnotationFinder.findAnnotationAttributes(getTargetMethod(jp), annotationType);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    public static <A extends Annotation> A getTargetMethodOrClassAnnotation(ProceedingJoinPoint jp, Class<A> annotationType) {
        var annotation = getTargetMethodAnnotation(jp, annotationType);
        if (annotation == null) {
            annotation = getTargetClassAnnotation(jp, annotationType);
        }
        return annotation;
    }

    public static <A extends Annotation> AnnotationAttributes getTargetMethodOrClassAnnotationAttributes(ProceedingJoinPoint jp, Class<A> annotationType) {
        var attr = getTargetMethodAnnotationAttributes(jp, annotationType);
        if (attr.isEmpty()) {
            attr = getTargetClassAnnotationAttributes(jp, annotationType);
        }
        return attr;
    }

}
