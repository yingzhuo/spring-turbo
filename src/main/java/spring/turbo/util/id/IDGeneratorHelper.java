package spring.turbo.util.id;

import spring.turbo.util.UUIDGenerators;

import java.net.InetAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ID生成辅助工具
 *
 * @author 应卓
 * @see UUIDGenerators#timeBased32()
 * @see UUIDGenerators#timeBased36()
 * @see spring.turbo.util.UUIDGenerators#timeBased(boolean)
 * @since 3.4.1
 */
public final class IDGeneratorHelper {

    private static final int IP;
    private static final Lock COUNTER_LOCKER = new ReentrantLock(false);
    private static final int JVM_STARTUP = (int) (System.currentTimeMillis() >>> 8);
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

    /**
     * JVM段
     *
     * @return JVM段
     */
    public static int getJVM() {
        return JVM_STARTUP;
    }

    /**
     * JVM段
     *
     * @return JVM段
     */
    public static String getFormattedJVM() {
        return format(JVM_STARTUP);
    }

    /**
     * 序号
     *
     * @return 序号
     */
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

    /**
     * 序号
     *
     * @return 序号
     */
    public static String getFormattedCount() {
        return format(getCount());
    }

    /**
     * IP
     *
     * @return IP
     */
    public static int getIP() {
        return IP;
    }

    /**
     * IP
     *
     * @return IP
     */
    public static String getFormattedIP() {
        return format(IP);
    }

    /**
     * 当前时间高位
     *
     * @return 时间高位
     */
    public static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    /**
     * 当前时间高位
     *
     * @return 时间高位
     */
    public static String getFormattedHiTime() {
        return format(getHiTime());
    }

    /**
     * 当前时间低位
     *
     * @return 时间低位
     */
    public static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * 当前时间低位
     *
     * @return 时间低位
     */
    public static String getFormattedLoTime() {
        return format(getLoTime());
    }

    /**
     * 格式化整数
     *
     * @param intValue 整数值
     * @return 字符串
     */
    public static String format(int intValue) {
        var formatted = Integer.toHexString(intValue);
        var buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    /**
     * 格式化短整数
     *
     * @param shortValue 短整数值
     * @return 字符串
     */
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
