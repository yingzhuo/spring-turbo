/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import spring.turbo.core.AnnotationUtils;

/**
 * @author 应卓
 * @since 1.2.3
 */
public class BetaWarningBeanPostProcessor implements BeanPostProcessor {

    private final static Logger log = LoggerFactory.getLogger(Beta.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            return doProcess(bean, beanName);
        } catch (Exception e) {
            return bean;
        }
    }

    private Object doProcess(Object bean, String beanName) {
        Class<?> beanType = bean.getClass();

        if (log.isWarnEnabled() && AnnotationUtils.findAnnotation(bean.getClass(), Beta.class) != null) {
            final String warningMsg = "\n\n" + "********************************************************************\n"
                    + "**********        You're using Beta component.         *************\n"
                    + "**********      Do not use in a production system!     *************\n"
                    + "********************************************************************\n\n";

            log.warn(warningMsg);
            log.warn("Component Type: \"{}\". Bean Name: \"{}\".", beanType, beanName);
        }

        return bean;
    }

}
