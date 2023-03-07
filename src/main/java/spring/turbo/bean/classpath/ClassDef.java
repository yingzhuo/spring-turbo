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
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import spring.turbo.util.Asserts;

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
public final class ClassDef implements BeanDefinition, Comparable<ClassDef>, Serializable {

    private final Class<?> clazz;
    private final BeanDefinition bd;

    /**
     * 构造方法
     *
     * @param beanDefinition beanDefinition实例
     */
    public ClassDef(BeanDefinition beanDefinition) {
        this(beanDefinition, null);
    }

    /**
     * 构造方法
     *
     * @param beanDefinition beanDefinition实例
     * @param classLoader    类加载器
     */
    public ClassDef(BeanDefinition beanDefinition, @Nullable ClassLoader classLoader) {
        Asserts.notNull(beanDefinition);

        classLoader = Objects.requireNonNullElseGet(classLoader, ClassUtils::getDefaultClassLoader);

        this.bd = beanDefinition;
        var className = beanDefinition.getBeanClassName();
        Asserts.notNull(className);

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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ClassDef[" + getBeanClassName() + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDef classDef = (ClassDef) o;
        return clazz.equals(classDef.clazz);
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
        return bd.getParentName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentName(String parentName) {
        bd.setParentName(parentName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getBeanClassName() {
        return bd.getBeanClassName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanClassName(String beanClassName) {
        bd.setBeanClassName(beanClassName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScope() {
        return bd.getScope();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public void setScope(String scope) {
        bd.setScope(scope);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLazyInit() {
        return bd.isLazyInit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLazyInit(boolean lazyInit) {
        bd.setLazyInit(lazyInit);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String[] getDependsOn() {
        return bd.getDependsOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDependsOn(String... dependsOn) {
        bd.setDependsOn(dependsOn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutowireCandidate() {
        return bd.isAutowireCandidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAutowireCandidate(boolean autowireCandidate) {
        bd.setAutowireCandidate(autowireCandidate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimary() {
        return bd.isPrimary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrimary(boolean primary) {
        bd.setPrimary(primary);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getFactoryBeanName() {
        return bd.getFactoryBeanName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFactoryBeanName(String factoryBeanName) {
        bd.setFactoryBeanName(factoryBeanName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getFactoryMethodName() {
        return bd.getFactoryMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFactoryMethodName(String factoryMethodName) {
        bd.setFactoryMethodName(factoryMethodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        return bd.getConstructorArgumentValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutablePropertyValues getPropertyValues() {
        return bd.getPropertyValues();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getInitMethodName() {
        return bd.getInitMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitMethodName(String initMethodName) {
        bd.setInitMethodName(initMethodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDestroyMethodName() {
        return bd.getDestroyMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDestroyMethodName(String destroyMethodName) {
        bd.setDestroyMethodName(destroyMethodName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRole() {
        return bd.getRole();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(int role) {
        bd.setRole(role);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getDescription() {
        return bd.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(String description) {
        bd.setDescription(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResolvableType getResolvableType() {
        return bd.getResolvableType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSingleton() {
        return bd.isSingleton();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrototype() {
        return bd.isPrototype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAbstract() {
        return bd.isAbstract();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getResourceDescription() {
        return bd.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public BeanDefinition getOriginatingBeanDefinition() {
        return bd.getOriginatingBeanDefinition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttribute(String name, Object value) {
        bd.setAttribute(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return bd.getAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object removeAttribute(String name) {
        return bd.removeAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAttribute(String name) {
        return bd.hasAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] attributeNames() {
        return bd.attributeNames();
    }

}
