package spring.turbo.util;

import java.lang.management.ManagementFactory;

/**
 * PID工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class PID {

    /**
     * 私有构造方法
     */
    private PID() {
    }

    /**
     * 获取当前进程PID
     *
     * @return PID
     */
    public static int get() {
        return SyncAvoid.pid;
    }

    // 延迟加载
    private static class SyncAvoid {
        private final static int pid;

        static {
            final String processName = ManagementFactory.getRuntimeMXBean().getName();
            if (processName == null || processName.isBlank()) {
                pid = -1;
            } else {
                final int atIndex = processName.indexOf('@');
                if (atIndex > 0) {
                    pid = Integer.parseInt(processName.substring(0, atIndex));
                } else {
                    pid = processName.hashCode();
                }
            }
        }
    }

}
