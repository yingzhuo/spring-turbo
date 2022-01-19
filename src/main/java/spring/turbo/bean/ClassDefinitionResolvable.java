/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * @author 应卓
 * @see org.springframework.beans.factory.SmartFactoryBean
 * @since 1.0.2
 */
public interface ClassDefinitionResolvable extends Serializable {

    public boolean isPrimary();

    public boolean isSingleton();

    public boolean isPrototype();

    public default boolean isAbstractDefinition() {
        return false;
    }

    public boolean isLazyInit();

    public default boolean isEagerInit() {
        return !isLazyInit();
    }

    public Class<?> getBeanClass();

    @Nullable
    public String getBeanClassName();

    /**
     * @return bean角色
     * @see org.springframework.beans.factory.config.BeanDefinition#ROLE_APPLICATION
     * @see org.springframework.beans.factory.config.BeanDefinition#ROLE_SUPPORT
     * @see org.springframework.beans.factory.config.BeanDefinition#ROLE_INFRASTRUCTURE
     */
    public int getRole();

}
