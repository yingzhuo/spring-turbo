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
import org.springframework.lang.Nullable;
import spring.turbo.bean.classpath.ClassDef;

import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.2
 */
public abstract class AbstractFactoryBean implements SmartFactoryBean<Object> {

    @Nullable
    protected ClassDef classDef;

    /**
     * 构造方法
     */
    public AbstractFactoryBean() {
        super();
    }

    @Override
    public final Class<?> getObjectType() {
        return Optional.ofNullable(classDef)
                .map(ClassDef::getBeanClass)
                .orElse(null);
    }

    @Override
    public final boolean isPrototype() {
        return Optional.ofNullable(classDef)
                .map(ClassDef::isPrototype)
                .orElse(SmartFactoryBean.super.isPrototype());
    }

    @Override
    public final boolean isSingleton() {
        return Optional.ofNullable(classDef)
                .map(ClassDef::isSingleton)
                .orElse(SmartFactoryBean.super.isSingleton());
    }

    @Override
    public final boolean isEagerInit() {
        return Optional.ofNullable(classDef)
                .map(cd -> !cd.isLazyInit())
                .orElse(SmartFactoryBean.super.isEagerInit());
    }

    public void setClassDef(@Nullable ClassDef classDef) {
        this.classDef = classDef;
    }

}
