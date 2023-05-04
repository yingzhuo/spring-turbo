/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.context.ApplicationEvent;
import spring.turbo.util.Asserts;

/**
 * {@link ApplicationEvent}相关工具
 *
 * @author 应卓
 *
 * @since 1.1.2
 */
public final class ApplicationEventUtils {

    /**
     * 私有构造方法
     */
    private ApplicationEventUtils() {
        super();
    }

    /**
     * 发布事件
     *
     * @param event
     *            事件实例
     */
    public static void publish(ApplicationEvent event) {
        Asserts.notNull(event);
        SpringUtils.getApplicationEventPublisher().publishEvent(event);
    }

}
