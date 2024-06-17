/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import spring.turbo.util.Asserts;
import spring.turbo.util.SleepUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * @author 应卓
 * @see SleepUtils
 * @since 1.1.1
 */
public final class FileWaitingUtils {

    public static final int DEFAULT_MAX_WAITING_COUNT = 60;
    public static final long DEFAULT_MILLIS_PER_WAITING = 1000L;

    /**
     * 私有构造方法
     */
    private FileWaitingUtils() {
        super();
    }

    public static void waitUntilIsReadable(Path path) {
        waitUntilIsReadable(path, DEFAULT_MAX_WAITING_COUNT, DEFAULT_MILLIS_PER_WAITING);
    }

    public static void waitUntilIsReadable(Path path, int maxWaitingCount, long millisPerWaiting) {
        Asserts.notNull(path);
        waitUntilIsReadable(path.toFile(), maxWaitingCount, millisPerWaiting);
    }

    public static void waitUntilIsReadable(File file) {
        waitUntilIsReadable(file, DEFAULT_MAX_WAITING_COUNT, DEFAULT_MILLIS_PER_WAITING);
    }

    public static void waitUntilIsReadable(File file, int maxWaitingCount, long millisPerWaiting) {
        Asserts.notNull(file);
        Asserts.isTrue(maxWaitingCount >= 1);
        Asserts.isTrue(millisPerWaiting >= 1);

        boolean isReadable = false;
        int loopsNumber = 0;
        while (!isReadable && loopsNumber <= maxWaitingCount) {
            try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
                isReadable = true;
            } catch (Exception e) {
                loopsNumber++;
                SleepUtils.sleep(maxWaitingCount, TimeUnit.MILLISECONDS);
                isReadable = false;
            }
        }
    }

}
