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
 * @author 应卓
 * @since 1.0.4
 */
public final class DeprecationUtils {

    private static final Class<Deprecated> ANNOTATION_TYPE = Deprecated.class;

    private DeprecationUtils() {
        super();
    }

    public boolean isDeprecated(@NonNull AnnotatedElement annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

    public boolean isDeprecated(@NonNull Method annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

    public boolean isDeprecated(@NonNull Class<?> annotationSupplier) {
        return AnnotationUtils.getAnnotation(annotationSupplier, ANNOTATION_TYPE) != null;
    }

}
