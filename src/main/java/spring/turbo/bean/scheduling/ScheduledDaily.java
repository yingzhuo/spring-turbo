package spring.turbo.bean.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.annotation.*;

/**
 * 每日午夜启动 <br>
 * {@code @Scheduled(cron = "@daily")} 的快捷方式
 *
 * @author 应卓
 * @see Scheduled
 * @see ScheduledMidnight
 * @since 1.2.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Scheduled(cron = "@daily")
public @interface ScheduledDaily {
}
