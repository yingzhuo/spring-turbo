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
import spring.turbo.core.AnnotationFinder;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static spring.turbo.core.AnnotationFinder.findAnnotation;
import static spring.turbo.core.AnnotationFinder.findAnnotationAttributes;
import static spring.turbo.util.Asserts.notNull;

/**
 * AOP相关工具
 *
 * @author 应卓
 *
 * @see AnnotationFinder
 *
 * @since 2.1.1
 */
public final class AopUtils {

    /**
     * 私有构造方法
     */
    private AopUtils() {
        super();
    }

    /**
     * 获取切面方法
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     *
     * @return 切面方法
     *
     * @throws IllegalArgumentException
     *             不能获取切面方法
     */
    public static Method getMethod(JoinPoint joinPoint) {
        notNull(joinPoint, "joinPoint is null");
        var signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature methodSignature) {
            return methodSignature.getMethod();
        } else {
            throw new IllegalArgumentException("Cannot get proxies method");
        }
    }

    /**
     * 获取切面拦截对象
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     *
     * @return 切面拦截对象
     */
    public static Object getTarget(JoinPoint joinPoint) {
        notNull(joinPoint, "joinPoint is null");
        return joinPoint.getTarget();
    }

    /**
     * 获取切面拦截对象类型
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     *
     * @return 切面拦截对象类型
     */
    public static Class<?> getTargetType(JoinPoint joinPoint) {
        return getTarget(joinPoint).getClass();
    }

    /**
     * 查找被拦截方法上的元注释
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     * @param annotationType
     *            元注释类型
     * @param <A>
     *            元注释类型泛型
     *
     * @return 元注释实例或 {@code null}
     */
    @Nullable
    public static <A extends Annotation> A getMethodAnnotation(JoinPoint joinPoint, Class<A> annotationType) {
        return findAnnotation(getMethod(joinPoint), annotationType);
    }

    /**
     * 获取被拦截方法上的元注释相关的 {@link AnnotationAttributes} 实例
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     * @param annotationType
     *            元注释类型
     * @param <A>
     *            元注释类型泛型
     *
     * @return {@link AnnotationAttributes} 实例
     */
    public static <A extends Annotation> AnnotationAttributes getMethodAnnotationAttributes(JoinPoint joinPoint,
            Class<A> annotationType) {
        return findAnnotationAttributes(getMethod(joinPoint), annotationType);
    }

    /**
     * 查找被拦截对象类型上的元注释
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     * @param annotationType
     *            元注释类型
     * @param <A>
     *            元注释类型泛型
     *
     * @return 元注释实例或 {@code null}
     */
    @Nullable
    public static <A extends Annotation> A getObjectTypeAnnotation(JoinPoint joinPoint, Class<A> annotationType) {
        return findAnnotation(getTargetType(joinPoint), annotationType);
    }

    /**
     * 获取被拦截对象类型上的元注释相关的 {@link AnnotationAttributes} 实例
     *
     * @param joinPoint
     *            {@link JoinPoint} 实例
     * @param annotationType
     *            元注释类型
     * @param <A>
     *            元注释类型泛型
     *
     * @return {@link AnnotationAttributes} 实例
     */
    public static <A extends Annotation> AnnotationAttributes getTargetTypeAnnotationAttributes(JoinPoint joinPoint,
            Class<A> annotationType) {
        return findAnnotationAttributes(getTargetType(joinPoint), annotationType);
    }

}
