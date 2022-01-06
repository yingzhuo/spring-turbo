/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;
import spring.turbo.core.AnnotationUtils;

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
        super();
    }

    /**
     * 检查{@code AnnotatedElement}实例是否已经过期
     *
     * @param annotationSupplier {code AnnotatedElement}实例
     * @return 已过期时返回  {@code true}  否则返回  {@code false}
     * @see java.lang.reflect.Constructor
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Parameter
     */
    public boolean isDeprecated(@NonNull AnnotatedElement annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

    /**
     * 检查方法是否已经过期
     *
     * @param annotationSupplier 方法
     * @return 已过期时返回  {@code true}  否则返回  {@code false}
     */
    public boolean isDeprecated(@NonNull Method annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

    /**
     * 检查类型是否已经过期
     *
     * @param annotationSupplier 类型
     * @return 已过期时返回  {@code true}  否则返回  {@code false}
     */
    public boolean isDeprecated(@NonNull Class<?> annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

}
