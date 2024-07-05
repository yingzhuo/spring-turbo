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
 * @see SpringUtils
 * @see SpringApplicationHoldersEnvironmentPostProcessor
 * @since 1.0.2
 */
class SpringApplicationHolders implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

    @Nullable
    static SpringApplication SA = null;

    @Nullable
    static ApplicationContext AC = null;

    /**
     * 私有构造方法
     */
    private SpringApplicationHolders() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AC = event.getApplicationContext();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
