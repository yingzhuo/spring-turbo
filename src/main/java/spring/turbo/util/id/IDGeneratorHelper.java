package spring.turbo.util.id;

import java.net.InetAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 内部工具
 *
 * @author 应卓
 * @since 3.4.1
 */
public final class IDGeneratorHelper {

    private static final int IP;
    private static final Lock COUNTER_LOCKER = new ReentrantLock(false);
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private static short COUNTER = (short) 0;

    static {
        int ipAddress;
        try {
            ipAddress = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Throwable ex) {
            ipAddress = 0;
        }
        IP = ipAddress;
    }

    /**
     * 私有构造方法
     */
    private IDGeneratorHelper() {
    }

    public static int getJVM() {
        return JVM;
    }

    public static String getFormattedJVM() {
        return format(JVM);
    }

    public static short getCount() {
        COUNTER_LOCKER.lock();
        try {
            if (COUNTER < 0) {
                COUNTER = 0;
            }
            return COUNTER++;
        } finally {
            COUNTER_LOCKER.unlock();
        }
    }

    public static String getFormattedCount() {
        return format(getCount());
    }

    public static int getIP() {
        return IP;
    }

    public static String getFormattedIP() {
        return format(IP);
    }

    public static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    public static String getFormattedHiTime() {
        return format(getHiTime());
    }

    public static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    public static String getFormattedLoTime() {
        return format(getLoTime());
    }

    public static String format(int intValue) {
        var formatted = Integer.toHexString(intValue);
        var buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    public static String format(short shortValue) {
        var formatted = Integer.toHexString(shortValue);
        var buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

}

