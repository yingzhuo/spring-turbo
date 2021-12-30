/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * 元注释相关工具类
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class AnnotationUtils {

    /**
     * 私有构造方法
     */
    private AnnotationUtils() {
        super();
    }

    /**
     * 查找元注释并获取AnnotationAttributes实例
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @return AnnotationAttributes实例
     * @see #findAnnotation(Class, Class)
     * @since 1.0.0
     */
    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(@NonNull Class<?> annotationSupplier, @NonNull Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);

        final Annotation annotation = org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType);

        if (annotation == null) {
            return new AnnotationAttributes();
        }

        return org.springframework.core.annotation.AnnotationUtils
                .getAnnotationAttributes(annotation, false, true);
    }

    /**
     * 查找元注释并获取AnnotationAttributes实例
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @return AnnotationAttributes实例
     * @see #findAnnotation(Method, Class)
     * @since 1.0.0
     */
    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(@NonNull Method annotationSupplier, @NonNull Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);

        final Annotation annotation = org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType);

        if (annotation == null) {
            return new AnnotationAttributes();
        }

        return org.springframework.core.annotation.AnnotationUtils
                .getAnnotationAttributes(annotation, false, true);
    }

    /**
     * 查找元注释并获取AnnotationAttributes实例
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @return AnnotationAttributes实例
     * @see #findAnnotation(AnnotatedElement, Class)
     * @since 1.0.0
     */
    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(@NonNull AnnotatedElement annotationSupplier, @NonNull Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);

        final Annotation annotation = org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType);

        if (annotation == null) {
            return new AnnotationAttributes();
        }

        return org.springframework.core.annotation.AnnotationUtils
                .getAnnotationAttributes(annotation, false, true);
    }

    /**
     * 级联查找元注释
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @param <A>                元注释泛型
     * @return 查找结果或null
     * @see #getAnnotation(Class, Class)
     * @since 1.0.0
     */
    @Nullable
    public static <A extends Annotation> A findAnnotation(Class<?> annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(annotationSupplier, annotationType);
    }

    /**
     * 级联查找元注释
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @param <A>                元注释泛型
     * @return 查找结果或null
     * @see #getAnnotation(AnnotatedElement, Class)
     * @since 1.0.0
     */
    @Nullable
    public static <A extends Annotation> A findAnnotation(AnnotatedElement annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(annotationSupplier, annotationType);
    }

    /**
     * 级联查找元注释
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @param <A>                元注释泛型
     * @return 查找结果或null
     * @see #getAnnotation(Method, Class)
     * @since 1.0.0
     */
    @Nullable
    public static <A extends Annotation> A findAnnotation(Method annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(annotationSupplier, annotationType);
    }

    /**
     * 单层查找元注释
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @param <A>                元注释泛型
     * @return 查找结果或null
     * @since 1.0.4
     */
    @Nullable
    public static <A extends Annotation> A getAnnotation(Class<?> annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.getAnnotation(annotationSupplier, annotationType);
    }

    /**
     * 单层查找元注释
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @param <A>                元注释泛型
     * @return 查找结果或null
     * @since 1.0.4
     */
    @Nullable
    public static <A extends Annotation> A getAnnotation(AnnotatedElement annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.getAnnotation(annotationSupplier, annotationType);
    }

    /**
     * 单层查找元注释
     *
     * @param annotationSupplier 查找起点
     * @param annotationType     元注释类型
     * @param <A>                元注释泛型
     * @return 查找结果或null
     * @since 1.0.4
     */
    @Nullable
    public static <A extends Annotation> A getAnnotation(Method annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.getAnnotation(annotationSupplier, annotationType);
    }

}
