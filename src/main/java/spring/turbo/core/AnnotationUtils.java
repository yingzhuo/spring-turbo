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
import spring.turbo.util.Asserts;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class AnnotationUtils {

    private AnnotationUtils() {
        super();
    }

    public static boolean isAnnotationPresent(Class<?> annotationSupplier, Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);

        return org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType) != null;
    }

    public static boolean isAnnotationPresent(Method annotationSupplier, Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);

        return org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType) != null;
    }

    public static boolean isAnnotationPresent(AnnotatedElement annotationSupplier, Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);

        return org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType) != null;
    }

    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(Class<?> annotationSupplier, Class<? extends Annotation> annotationType) {
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

    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(Method annotationSupplier, Class<? extends Annotation> annotationType) {
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

    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(AnnotatedElement annotationSupplier, Class<? extends Annotation> annotationType) {
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

    public static <A extends Annotation> A findAnnotation(Class<?> annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(annotationSupplier, annotationType);
    }

    public static <A extends Annotation> A findAnnotation(AnnotatedElement annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(annotationSupplier, annotationType);
    }

    public static <A extends Annotation> A findAnnotation(Method annotationSupplier, Class<A> annotationType) {
        Asserts.notNull(annotationSupplier);
        Asserts.notNull(annotationType);
        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(annotationSupplier, annotationType);
    }

}
