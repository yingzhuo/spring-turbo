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

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 应卓
 * @see BeanDefinition
 * @since 1.0.2
 */
public final class ClassDefinition implements ClassDefinitionResolvable {

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

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public String getBeanClassName() {
        return beanDefinition.getBeanClassName();
    }

    @Override
    public boolean isPrimary() {
        return beanDefinition.isPrimary();
    }

    @Override
    public boolean isSingleton() {
        return beanDefinition.isSingleton();
    }

    @Override
    public boolean isPrototype() {
        return beanDefinition.isPrototype();
    }

    @Override
    public boolean isAbstractDefinition() {
        return beanDefinition.isAbstract();
    }

    @Override
    public boolean isLazyInit() {
        return beanDefinition.isLazyInit();
    }

    @Override
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

    @Override
    public int getRole() {
        return beanDefinition.getRole();
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

    @Override
    public String toString() {
        return beanDefinition.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDefinition that = (ClassDefinition) o;
        return beanDefinition.equals(that.beanDefinition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanDefinition);
    }

}
