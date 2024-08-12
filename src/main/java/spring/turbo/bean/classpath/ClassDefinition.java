package spring.turbo.bean.classpath;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.*;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;

/**
 * 类路径扫描的结果
 *
 * @author 应卓
 * @see BeanDefinition
 * @since 2.0.9
 */
public final class ClassDefinition implements BeanDefinition, Comparable<ClassDefinition>, Serializable {

    private final Class<?> clazz;
    private final BeanDefinition delegating;

    /**
     * 构造方法
     *
     * @param beanDefinition beanDefinition实例
     */
    public ClassDefinition(BeanDefinition beanDefinition) {
        this(beanDefinition, null);
    }

    /**
     * 构造方法
     *
     * @param beanDefinition beanDefinition实例
     * @param classLoader    类加载器
     */
    public ClassDefinition(BeanDefinition beanDefinition, @Nullable ClassLoader classLoader) {
        Assert.notNull(beanDefinition, "beanDefinition is required");
        classLoader = Objects.requireNonNullElseGet(classLoader, ClassUtils::getDefaultClassLoader);

        this.delegating = beanDefinition;
        var className = beanDefinition.getBeanClassName();
        Assert.notNull(className, "className is null");

        try {
            this.clazz = ClassUtils.forName(className, classLoader);
        } catch (Throwable e) {
            throw new IllegalStateException("Cannot load class: '" + className + "'");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 获取类型
     *
     * @return 类型
     * @see #getBeanClassName()
     */
    public Class<?> getBeanClass() {
        return this.clazz;
    }

    public TypeDescriptor getBeanTypeDescriptor() {
        return TypeDescriptor.valueOf(getBeanClass());
    }

    // -----------------------------------------------------------------------------------------------------------------

    public MergedAnnotations getMergedAnnotations() {
        return MergedAnnotations.search(MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
                .withRepeatableContainers(RepeatableContainers.none()).from(clazz);
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
        return Objects.requireNonNull(annotation);
    }

    public <A extends Annotation> AnnotationAttributes getAnnotationAttributes(Class<A> annotationType) {
        return getAnnotationAttributes(annotationType, false, false);
    }

    public <A extends Annotation> AnnotationAttributes getAnnotationAttributes(Class<A> annotationType,
                                                                               boolean classValuesAsString) {
        return getAnnotationAttributes(annotationType, classValuesAsString, false);
    }

    public <A extends Annotation> AnnotationAttributes getAnnotationAttributes(Class<A> annotationType,
                                                                               boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        try {
            var annotation = AnnotationUtils.findAnnotation(this.clazz, annotationType);
            if (annotation == null) {
                return new AnnotationAttributes(annotationType);
            }
            return AnnotationUtils.getAnnotationAttributes(annotation, classValuesAsString, nestedAnnotationsAsMap);
        } catch (Exception e) {
            return new AnnotationAttributes(annotationType);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@Nullable ClassDefinition o) {
        var o1 = this.getBeanClassName();
        var o2 = Optional.ofNullable(o).map(ClassDefinition::getBeanClassName).orElse(null);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ClassDefinition[" + getBeanClassName() + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClassDefinition classDefinition = (ClassDefinition) o;
        return clazz.equals(classDefinition.clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(clazz);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getParentName() {
        return delegating.getParentName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentName(String parentName) {
        delegating.setParentName(parentName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getBeanClassName() {
        return delegating.getBeanClassName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanClassName(String beanClassName) {
        delegating.setBeanClassName(beanClassName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScope() {
        return delegating.getScope();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public void setScope(String scope) {
        delegating.setScope(scope);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLazyInit() {
        return delegating.isLazyInit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLazyInit(boolean lazyInit) {
        delegating.setLazyInit(lazyInit);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String[] getDependsOn() {
        return delegating.getDependsOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDependsOn(String... dependsOn) {
        delegating.setDependsOn(dependsOn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutowireCandidate() {
        return delegating.isAutowireCandidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAutowireCandidate(boolean autowireCandidate) {
        delegating.setAutowireCandidate(autowireCandidate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimary() {
        return delegating.isPrimary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrimary(boolean primary) {
        delegating.setPrimary(primary);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getFactoryBeanName() {
        return delegating.getFactoryBeanName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFactoryBeanName(String factoryBeanName) {
        delegating.setFactoryBeanName(factoryBeanName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getFactoryMethodName() {
        return delegating.getFactoryMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFactoryMethodName(String factoryMethodName) {
        delegating.setFactoryMethodName(factoryMethodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        return delegating.getConstructorArgumentValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutablePropertyValues getPropertyValues() {
        return delegating.getPropertyValues();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getInitMethodName() {
        return delegating.getInitMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitMethodName(String initMethodName) {
        delegating.setInitMethodName(initMethodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDestroyMethodName() {
        return delegating.getDestroyMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDestroyMethodName(String destroyMethodName) {
        delegating.setDestroyMethodName(destroyMethodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRole() {
        return delegating.getRole();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(int role) {
        delegating.setRole(role);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getDescription() {
        return delegating.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(String description) {
        delegating.setDescription(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResolvableType getResolvableType() {
        return delegating.getResolvableType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSingleton() {
        return delegating.isSingleton();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrototype() {
        return delegating.isPrototype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAbstract() {
        return delegating.isAbstract();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getResourceDescription() {
        return delegating.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public BeanDefinition getOriginatingBeanDefinition() {
        return delegating.getOriginatingBeanDefinition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttribute(String name, Object value) {
        delegating.setAttribute(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAttribute(String name) {
        return delegating.getAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object removeAttribute(String name) {
        return delegating.removeAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAttribute(String name) {
        return delegating.hasAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] attributeNames() {
        return delegating.attributeNames();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasConstructorArgumentValues() {
        return delegating.hasConstructorArgumentValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPropertyValues() {
        return delegating.hasPropertyValues();
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public boolean isFallback() {
//        return delegating.isFallback();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setFallback(boolean fallback) {
//        delegating.setFallback(fallback);
//    }

}
