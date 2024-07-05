package spring.turbo.core;

import org.springframework.context.ApplicationEvent;
import spring.turbo.util.Asserts;

import static spring.turbo.core.SpringUtils.getApplicationEventPublisher;

/**
 * {@link ApplicationEvent}相关工具
 *
 * @author 应卓
 * @since 1.1.2
 */
public final class ApplicationEventUtils {

    /**
     * 私有构造方法
     */
    private ApplicationEventUtils() {
    }

    /**
     * 发布事件
     *
     * @param event 事件实例
     */
    public static void publish(ApplicationEvent event) {
        Asserts.notNull(event);
        getApplicationEventPublisher().publishEvent(event);
    }

    /**
     * 发布事件
     *
     * @param event 事件实例
     * @since 3.3.1
     */
    public static void publish(Object event) {
        Asserts.notNull(event);
        getApplicationEventPublisher().publishEvent(event);
    }

}
