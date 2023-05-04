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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 元注释相关工具
 *
 * @author 应卓
 *
 * @since 2.0.11
 */
public final class AnnotationFinder {

    /**
     * 私有构造方法
     */
    private AnnotationFinder() {
        super();
    }

    @Nullable
    public static <A extends Annotation> A findAnnotation(Class<?> type, Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(type, annotationType);
    }

    public static <A extends Annotation> A findRequiredAnnotation(Class<?> type, Class<A> annotationType) {
        var annotation = findAnnotation(type, annotationType);
        Asserts.notNull(annotation, "Annotation not found");
        return annotation;
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Class<?> type,
            Class<A> annotationType) {
        return findAnnotationAttributes(type, annotationType, false, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Class<?> type,
            Class<A> annotationType, boolean classValueAsString) {
        return findAnnotationAttributes(type, annotationType, classValueAsString, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Class<?> type,
            Class<A> annotationType, boolean classValueAsString, boolean nestedAnnotationsAsMap) {
        var annotation = findAnnotation(type, annotationType);
        if (annotation == null) {
            return new AnnotationAttributes();
        }
        return AnnotationAttributes.fromMap(
                AnnotationUtils.getAnnotationAttributes(annotation, classValueAsString, nestedAnnotationsAsMap));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    public static <A extends Annotation> A findAnnotation(Method method, Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(method, annotationType);
    }

    public static <A extends Annotation> A findRequiredAnnotation(Method method, Class<A> annotationType) {
        var annotation = AnnotationUtils.findAnnotation(method, annotationType);
        Asserts.notNull(annotation, "Annotation not found");
        return annotation;
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Method method,
            Class<A> annotationType) {
        return findAnnotationAttributes(method, annotationType, false, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Method method,
            Class<A> annotationType, boolean classValueAsString) {
        return findAnnotationAttributes(method, annotationType, classValueAsString, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Method method,
            Class<A> annotationType, boolean classValueAsString, boolean nestedAnnotationsAsMap) {
        var annotation = findAnnotation(method, annotationType);
        if (annotation == null) {
            return new AnnotationAttributes();
        }
        return AnnotationAttributes.fromMap(
                AnnotationUtils.getAnnotationAttributes(annotation, classValueAsString, nestedAnnotationsAsMap));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    public static <A extends Annotation> A findAnnotation(Field field, Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(field, annotationType);
    }

    public static <A extends Annotation> A findRequiredAnnotation(Field field, Class<A> annotationType) {
        var annotation = AnnotationUtils.findAnnotation(field, annotationType);
        Asserts.notNull(annotation, "Annotation not found");
        return annotation;
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Field field,
            Class<A> annotationType) {
        return findAnnotationAttributes(field, annotationType, false, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Field field,
            Class<A> annotationType, boolean classValueAsString) {
        return findAnnotationAttributes(field, annotationType, classValueAsString, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Field field,
            Class<A> annotationType, boolean classValueAsString, boolean nestedAnnotationsAsMap) {
        var annotation = findAnnotation(field, annotationType);
        if (annotation == null) {
            return new AnnotationAttributes();
        }
        return AnnotationAttributes.fromMap(
                AnnotationUtils.getAnnotationAttributes(annotation, classValueAsString, nestedAnnotationsAsMap));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    public static <A extends Annotation> A findAnnotation(Constructor<?> constructor, Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(constructor, annotationType);
    }

    public static <A extends Annotation> A findRequiredAnnotation(Constructor<?> constructor, Class<A> annotationType) {
        var annotation = findAnnotation(constructor, annotationType);
        Asserts.notNull(annotation, "Annotation not found");
        return annotation;
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Constructor<?> constructor,
            Class<A> annotationType) {
        return findAnnotationAttributes(constructor, annotationType, false, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Constructor<?> constructor,
            Class<A> annotationType, boolean classValueAsString) {
        return findAnnotationAttributes(constructor, annotationType, classValueAsString, false);
    }

    public static <A extends Annotation> AnnotationAttributes findAnnotationAttributes(Constructor<?> constructor,
            Class<A> annotationType, boolean classValueAsString, boolean nestedAnnotationsAsMap) {
        var annotation = findAnnotation(constructor, annotationType);
        if (annotation == null) {
            return new AnnotationAttributes();
        }
        return AnnotationAttributes.fromMap(
                AnnotationUtils.getAnnotationAttributes(annotation, classValueAsString, nestedAnnotationsAsMap));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <A extends Annotation> AnnotationAttributes toAnnotationAttributes(@Nullable A annotation) {
        return toAnnotationAttributes(annotation, false, false);
    }

    public static <A extends Annotation> AnnotationAttributes toAnnotationAttributes(@Nullable A annotation,
            boolean classValueAsString) {
        return toAnnotationAttributes(annotation, classValueAsString, false);
    }

    public static <A extends Annotation> AnnotationAttributes toAnnotationAttributes(@Nullable A annotation,
            boolean classValueAsString, boolean nestedAnnotationsAsMap) {
        if (annotation == null) {
            return new AnnotationAttributes();
        }
        return AnnotationUtils.getAnnotationAttributes(annotation, classValueAsString, nestedAnnotationsAsMap);
    }

}
