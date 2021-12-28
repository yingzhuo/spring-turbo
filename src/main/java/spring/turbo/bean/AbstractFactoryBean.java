/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.SmartFactoryBean;

import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.2
 */
public abstract class AbstractFactoryBean implements SmartFactoryBean<Object> {

    protected ClassDefinitionResolvable classDefinitionResolvable;

    public AbstractFactoryBean() {
        super();
    }

    @Override
    public final Class<?> getObjectType() {
        return Optional.ofNullable(classDefinitionResolvable)
                .map(ClassDefinitionResolvable::getBeanClass)
                .orElse(null);
    }

    @Override
    public final boolean isPrototype() {
        return this.classDefinitionResolvable == null ?
                SmartFactoryBean.super.isPrototype() :
                classDefinitionResolvable.isPrototype();
    }

    @Override
    public final boolean isSingleton() {
        return this.classDefinitionResolvable == null ?
                SmartFactoryBean.super.isSingleton() :
                classDefinitionResolvable.isSingleton();
    }

    @Override
    public final boolean isEagerInit() {
        return this.classDefinitionResolvable == null ?
                SmartFactoryBean.super.isEagerInit() :
                classDefinitionResolvable.isEagerInit();
    }

    public final void setClassDefinitionResolvable(ClassDefinitionResolvable classDefinitionResolvable) {
        this.classDefinitionResolvable = classDefinitionResolvable;
    }

    public final void setClassDefinition(ClassDefinition classDefinition) {
        this.setClassDefinitionResolvable((ClassDefinitionResolvable) classDefinition);
    }

}
