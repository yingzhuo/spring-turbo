package spring.turbo.bean.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.annotation.*;

/**
 * 每日午夜启动 <br>
 * {@code @Scheduled(cron = "@midnight")} 的快捷方式
 *
 * @author 应卓
 * @see Scheduled
 * @see ScheduledDaily
 * @since 1.2.3
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Scheduled(cron = "@midnight")
public @interface ScheduledMidnight {
}
