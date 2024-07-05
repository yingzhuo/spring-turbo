package spring.turbo.util;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * 代码过期性检查工具
 *
 * @author 应卓
 * @since 1.0.4
 */
public final class DeprecationUtils {

    /**
     * {@link Deprecated}类型
     */
    private static final Class<Deprecated> ANNOTATION_TYPE = Deprecated.class;

    /**
     * 私有构造方法
     */
    private DeprecationUtils() {
    }

    /**
     * 检查{@code AnnotatedElement}实例是否已经过期
     *
     * @param annotationSupplier {code AnnotatedElement}实例
     * @return 已过期时返回 {@code true} 否则返回 {@code false}
     * @see java.lang.reflect.Constructor
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Parameter
     */
    public boolean isDeprecated(AnnotatedElement annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

    /**
     * 检查方法是否已经过期
     *
     * @param annotationSupplier 方法
     * @return 已过期时返回 {@code true} 否则返回 {@code false}
     */
    public boolean isDeprecated(Method annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

    /**
     * 检查类型是否已经过期
     *
     * @param annotationSupplier 类型
     * @return 已过期时返回 {@code true} 否则返回 {@code false}
     */
    public boolean isDeprecated(Class<?> annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

}
