/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import spring.turbo.util.Asserts;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;

/**
 * @author 应卓
 * @see ScannedResultSet
 * @since 1.0.0
 */
public final class ScannedResult implements Serializable, Comparable<ScannedResult> {

    private final BeanDefinition beanDefinition;

    public ScannedResult(BeanDefinition beanDefinition) {
        Asserts.notNull(beanDefinition);

        this.beanDefinition = beanDefinition;
    }

    public String getClassName() {
        return beanDefinition.getBeanClassName();
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public AnnotationAttributes getAnnotationAttributes(Class<? extends Annotation> annotationType) {
        Asserts.notNull(annotationType);

        if (!(beanDefinition instanceof AnnotatedBeanDefinition)) {
            return new AnnotationAttributes(annotationType);
        }

        AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;

        final Map<String, Object> attributes =
                annotatedBeanDefinition.getMetadata()
                        .getAnnotationAttributes(annotationType.getName());

        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(attributes);
        if (annotationAttributes == null) {
            annotationAttributes = new AnnotationAttributes(annotationType);
        }
        return annotationAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScannedResult that = (ScannedResult) o;
        return beanDefinition.equals(that.beanDefinition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanDefinition);
    }

    @Override
    public int compareTo(ScannedResult o) {
        if (o == null) return -1;
        return this.getClassName().compareTo(o.getClassName());
    }

}
