package spring.turbo.core;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.Assert;

import static spring.turbo.core.SpringUtils.getApplicationEventPublisher;

/**
 * {@link ApplicationEventPublisher}相关工具
 *
 * @author 应卓
 * @see SpringUtils
 * @since 3.3.2
 */
public final class ApplicationEventPublisherUtils {

    /**
     * 私有构造方法
     */
    private ApplicationEventPublisherUtils() {
    }

    /**
     * 发布事件
     *
     * @param event 事件实例
     */
    public static void publish(ApplicationEvent event) {
        Assert.notNull(event, "event is required");
        getApplicationEventPublisher().publishEvent(event);
    }

    /**
     * 发布事件
     *
     * @param event 事件实例
     * @since 3.3.1
     */
    public static void publish(Object event) {
        Assert.notNull(event, "event is required");
        getApplicationEventPublisher().publishEvent(event);
    }

}
