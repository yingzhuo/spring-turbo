/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.core.AnnotationUtils;
import spring.turbo.util.Asserts;
import spring.turbo.util.ClassUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.2
 */
public final class ClassDefinition implements Serializable {

    private final BeanDefinition beanDefinition;
    private final Class<?> beanClass;

    public ClassDefinition(@NonNull BeanDefinition beanDefinition) {
        Asserts.notNull(beanDefinition);
        this.beanDefinition = beanDefinition;
        this.beanClass = ClassUtils.forNameOrThrow(Optional.ofNullable(beanDefinition.getBeanClassName())
                .orElseThrow(NullPointerException::new));
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public boolean isPrimary() {
        return beanDefinition.isPrimary();
    }

    public boolean isSingleton() {
        return beanDefinition.isSingleton();
    }

    public boolean isPrototype() {
        return beanDefinition.isPrototype();
    }

    public boolean isAbstract() {
        return beanDefinition.isAbstract();
    }

    public boolean isLazyInit() {
        return beanDefinition.isLazyInit();
    }

    public boolean isEagerInit() {
        return !beanDefinition.isLazyInit();
    }

    @Nullable
    public String getScope() {
        return beanDefinition.getScope();
    }

    @Nullable
    public String getDescription() {
        return beanDefinition.getDescription();
    }

    @Nullable
    public String getResourceDescription() {
        return beanDefinition.getResourceDescription();
    }

    public <T extends Annotation> boolean isAnnotationPresent(@NonNull Class<T> annotationType) {
        return findAnnotation(annotationType) != null;
    }

    @Nullable
    public <T extends Annotation> T findAnnotation(@NonNull Class<T> annotationType) {
        return AnnotationUtils.findAnnotation(this.beanClass, annotationType);
    }

    @NonNull
    public AnnotationAttributes findAnnotationAttributes(@NonNull Class<? extends Annotation> annotationType) {
        return AnnotationUtils.findAnnotationAttributes(this.beanClass, annotationType);
    }

}
