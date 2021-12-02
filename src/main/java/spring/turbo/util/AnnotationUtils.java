/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

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
        Assert.notNull(annotationSupplier, "annotationSupplier is null");
        Assert.notNull(annotationType, "annotationType is null");

        return org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType) != null;
    }

    public static boolean isAnnotationPresent(Method annotationSupplier, Class<? extends Annotation> annotationType) {
        Assert.notNull(annotationSupplier, "annotationSupplier is null");
        Assert.notNull(annotationType, "annotationType is null");

        return org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType) != null;
    }

    public static boolean isAnnotationPresent(AnnotatedElement annotationSupplier, Class<? extends Annotation> annotationType) {
        Assert.notNull(annotationSupplier, "annotationSupplier is null");
        Assert.notNull(annotationType, "annotationType is null");

        return org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType) != null;
    }

    @NonNull
    public static AnnotationAttributes findAnnotationAttributes(Class<?> annotationSupplier, Class<? extends Annotation> annotationType) {
        Assert.notNull(annotationSupplier, "annotationSupplier is null");
        Assert.notNull(annotationType, "annotationType is null");

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
        Assert.notNull(annotationSupplier, "annotationSupplier is null");
        Assert.notNull(annotationType, "annotationType is null");

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
        Assert.notNull(annotationSupplier, "annotationSupplier is null");
        Assert.notNull(annotationType, "annotationType is null");

        final Annotation annotation = org.springframework.core.annotation.AnnotationUtils
                .findAnnotation(annotationSupplier, annotationType);

        if (annotation == null) {
            return new AnnotationAttributes();
        }

        return org.springframework.core.annotation.AnnotationUtils
                .getAnnotationAttributes(annotation, false, true);
    }

}
