package spring.turbo.util;

import java.net.InetAddress;

/**
 * 内部工具
 *
 * @author 应卓
 * @since 3.4.1
 */
abstract class UUIDGeneratorHelper {

    private static final int IP;

    static {
        int ipAddress;
        try {
            ipAddress = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipAddress = 0;
        }
        IP = ipAddress;
    }

    private static short COUNTER = (short) 0;
    private final static int JVM = (int) (System.currentTimeMillis() >>> 8);

    protected UUIDGeneratorHelper() {
    }

    protected static int getJVM() {
        return JVM;
    }

    protected static short getCount() {
        synchronized (UUIDGeneratorHelper.class) {
            if (COUNTER < 0) {
                COUNTER = 0;
            }
            return COUNTER++;
        }
    }

    protected static int getIP() {
        return IP;
    }

    protected static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    protected static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

}

