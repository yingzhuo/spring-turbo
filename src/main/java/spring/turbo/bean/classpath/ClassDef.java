/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;
import spring.turbo.util.ClassUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * {@link BeanDefinition} 的装饰器
 *
 * @author 应卓
 * @since 2.0.9
 */
public final class ClassDef implements BeanDefinition, Comparable<ClassDef>, Serializable {

    private final BeanDefinition bd;
    private final Class<?> clazz;

    /**
     * 构造方法
     *
     * @param beanDefinition beanDefinition实例
     */
    public ClassDef(@NonNull BeanDefinition beanDefinition) {
        Asserts.notNull(beanDefinition);
        this.bd = beanDefinition;
        var className = beanDefinition.getBeanClassName();
        Asserts.notNull(className);
        this.clazz = ClassUtils.forNameOrThrow(className);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Class<?> getBeanClass() {
        return this.clazz;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public MergedAnnotations getMergedAnnotations() {
        return MergedAnnotations.search(MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
                .withRepeatableContainers(RepeatableContainers.none())
                .from(clazz);
    }

    public <A extends Annotation> MergedAnnotation<A> getMergedAnnotation(Class<A> annotationType) {
        return getMergedAnnotations().get(annotationType);
    }

    public <A extends Annotation> boolean isAnnotationPresent(Class<A> annotationType) {
        return getMergedAnnotations().isPresent(annotationType);
    }

    public <A extends Annotation> boolean isAnnotationPresentDirectly(Class<A> annotationType) {
        return getMergedAnnotations().isDirectlyPresent(annotationType);
    }

    @Nullable
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        return AnnotationUtils.getAnnotation(clazz, annotationType);
    }

    public <A extends Annotation> A getRequiredAnnotation(Class<A> annotationType) {
        A annotation = getAnnotation(annotationType);
        Asserts.notNull(annotation);
        return annotation;
    }

    public <A extends Annotation> AnnotationAttributes getAnnotationAttributes(Class<A> annotationType) {
        return getAnnotationAttributes(annotationType, false, false);
    }

    public <A extends Annotation> AnnotationAttributes getAnnotationAttributes(Class<A> annotationType, boolean classValuesAsString) {
        return getAnnotationAttributes(annotationType, classValuesAsString, false);
    }

    public <A extends Annotation> AnnotationAttributes getAnnotationAttributes(Class<A> annotationType, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        try {
            return AnnotationUtils.getAnnotationAttributes(
                    getRequiredAnnotation(annotationType),
                    classValuesAsString,
                    nestedAnnotationsAsMap
            );
        } catch (Exception e) {
            return new AnnotationAttributes(annotationType);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public int compareTo(@Nullable ClassDef o) {
        var o1 = this.getBeanClassName();
        var o2 = Optional.ofNullable(o).map(ClassDef::getBeanClassName).orElse(null);

        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o1.compareTo(o2);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void setParentName(String parentName) {
        bd.setParentName(parentName);
    }

    @Override
    public String getParentName() {
        return bd.getParentName();
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        bd.setBeanClassName(beanClassName);
    }

    @Nullable
    @Override
    public String getBeanClassName() {
        return bd.getBeanClassName();
    }

    @Override
    @Nullable
    public void setScope(String scope) {
        bd.setScope(scope);
    }

    @Override
    public String getScope() {
        return bd.getScope();
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        bd.setLazyInit(lazyInit);
    }

    @Override
    public boolean isLazyInit() {
        return bd.isLazyInit();
    }

    @Override
    public void setDependsOn(String... dependsOn) {
        bd.setDependsOn(dependsOn);
    }

    @Nullable
    @Override
    public String[] getDependsOn() {
        return bd.getDependsOn();
    }

    @Override
    public void setAutowireCandidate(boolean autowireCandidate) {
        bd.setAutowireCandidate(autowireCandidate);
    }

    @Override
    public boolean isAutowireCandidate() {
        return bd.isAutowireCandidate();
    }

    @Override
    public void setPrimary(boolean primary) {
        bd.setPrimary(primary);
    }

    @Override
    public boolean isPrimary() {
        return bd.isPrimary();
    }

    @Override
    public void setFactoryBeanName(String factoryBeanName) {
        bd.setFactoryBeanName(factoryBeanName);
    }

    @Nullable
    @Override
    public String getFactoryBeanName() {
        return bd.getFactoryBeanName();
    }

    @Override
    public void setFactoryMethodName(String factoryMethodName) {
        bd.setFactoryMethodName(factoryMethodName);
    }

    @Nullable
    @Override
    public String getFactoryMethodName() {
        return bd.getFactoryMethodName();
    }

    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        return bd.getConstructorArgumentValues();
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return bd.getPropertyValues();
    }

    @Override
    public void setInitMethodName(String initMethodName) {
        bd.setInitMethodName(initMethodName);
    }

    @Nullable
    @Override
    public String getInitMethodName() {
        return bd.getInitMethodName();
    }

    @Override
    public void setDestroyMethodName(String destroyMethodName) {
        bd.setDestroyMethodName(destroyMethodName);
    }

    @Override
    public String getDestroyMethodName() {
        return bd.getDestroyMethodName();
    }

    @Override
    public void setRole(int role) {
        bd.setRole(role);
    }

    @Override
    public int getRole() {
        return bd.getRole();
    }

    @Override
    public void setDescription(String description) {
        bd.setDescription(description);
    }

    @Nullable
    @Override
    public String getDescription() {
        return bd.getDescription();
    }

    @Override
    public ResolvableType getResolvableType() {
        return bd.getResolvableType();
    }

    @Override
    public boolean isSingleton() {
        return bd.isSingleton();
    }

    @Override
    public boolean isPrototype() {
        return bd.isPrototype();
    }

    @Override
    public boolean isAbstract() {
        return bd.isAbstract();
    }

    @Override
    public String getResourceDescription() {
        return bd.getDescription();
    }

    @Nullable
    @Override
    public BeanDefinition getOriginatingBeanDefinition() {
        return bd.getOriginatingBeanDefinition();
    }

    @Override
    public void setAttribute(String name, Object value) {
        bd.setAttribute(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return bd.getAttribute(name);
    }

    @Override
    public Object removeAttribute(String name) {
        return bd.removeAttribute(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        return bd.hasAttribute(name);
    }

    @Override
    public String[] attributeNames() {
        return bd.attributeNames();
    }

}
