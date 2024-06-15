/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * ApplicationContext实例保存
 *
 * @author 应卓
 *
 * @see SpringUtils
 * @see SpringApplicationHoldersEnvironmentPostProcessor
 *
 * @since 1.0.2
 */
final class SpringApplicationHolders implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

    @Nullable
    static SpringApplication SA = null;

    @Nullable
    static ApplicationContext AC = null;

    // @Nullable
    // static SpringContext SC = null;

    /**
     * 私有构造方法
     */
    private SpringApplicationHolders() {
        super();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AC = event.getApplicationContext();
        // SC = SpringContext.of(AC);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
