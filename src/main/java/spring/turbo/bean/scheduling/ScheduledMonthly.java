package spring.turbo.bean.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.annotation.*;

/**
 * 每月启动一次 <br>
 * {@code @Scheduled(cron = "@monthly")} 的快捷方式
 *
 * @author 应卓
 * @see Scheduled
 * @since 1.2.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Scheduled(cron = "@monthly")
public @interface ScheduledMonthly {
}
