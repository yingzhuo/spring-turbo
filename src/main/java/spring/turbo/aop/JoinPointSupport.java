/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static spring.turbo.core.AnnotationFinder.findAnnotation;
import static spring.turbo.core.AnnotationFinder.findAnnotationAttributes;
import static spring.turbo.util.Asserts.notNull;

/**
 * @author 应卓
 * @since 2.1.0
 */
public final class JoinPointSupport implements Serializable {

    private final JoinPoint joinPoint;
    private final Class<?> targetClass;
    private final Method targetMethod;
    private final Object target;

    public JoinPointSupport(JoinPoint joinPoint) {
        Assert.notNull(joinPoint, "joinPoint is null");
        this.joinPoint = joinPoint;
        this.target = joinPoint.getTarget();
        this.targetClass = this.target.getClass();
        this.targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
    }

    public JoinPoint getJoinPoint() {
        return joinPoint;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object getTarget() {
        return target;
    }

    @Nullable
    public <A extends Annotation> A getClassLevelAnnotation(Class<A> annotationType) {
        notNull(annotationType, "annotationType is null");
        return findAnnotation(this.targetClass, annotationType);
    }

    public <A extends Annotation> AnnotationAttributes getClassLevelAnnotationAttributes(Class<A> annotationType) {
        notNull(annotationType, "annotationType is null");
        return findAnnotationAttributes(this.targetClass, annotationType);
    }

    @Nullable
    public <A extends Annotation> A getMethodLevelAnnotation(Class<A> annotationType) {
        notNull(annotationType, "annotationType is null");
        return findAnnotation(this.targetMethod, annotationType);
    }

    public <A extends Annotation> AnnotationAttributes getMethodLevelAnnotationAttributes(Class<A> annotationType) {
        notNull(annotationType, "annotationType is null");
        return findAnnotationAttributes(this.targetMethod, annotationType);
    }

    @Override
    public String toString() {
        return this.joinPoint.toString();
    }

}
