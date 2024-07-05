package spring.turbo.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Bean Scope(s)
 *
 * @author 应卓
 * @see org.springframework.beans.factory.config.ConfigurableBeanFactory
 * @since 1.0.0
 */
public final class BeanScopes {

    public static final String SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    public static final String PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /**
     * 私有构造方法
     */
    private BeanScopes() {
    }

}
