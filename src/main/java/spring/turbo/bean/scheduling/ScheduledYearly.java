package spring.turbo.bean.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.annotation.*;

/**
 * 每年启动 <br>
 * {@code @Scheduled(cron = "@yearly")} 的快捷方式
 *
 * @author 应卓
 * @see Scheduled
 * @since 1.2.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Scheduled(cron = "@yearly")
public @interface ScheduledYearly {
}
