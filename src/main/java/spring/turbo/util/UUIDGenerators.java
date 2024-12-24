package spring.turbo.util;

import java.util.UUID;

import static spring.turbo.util.StringPool.EMPTY;
import static spring.turbo.util.StringPool.HYPHEN;
import static spring.turbo.util.UUIDGeneratorHelper.*;

/**
 * UUID生成工具
 *
 * @author 应卓
 * @since 3.4.1
 */
public final class UUIDGenerators {

    /**
     * 私有构造方法
     */
    private UUIDGenerators() {
    }

    public static String randomV4() {
        return randomV4(true);
    }

    public static String randomV4(boolean removeHyphen) {
        var uuid = UUID.randomUUID().toString();
        return removeHyphen ? uuid.replaceAll(HYPHEN, EMPTY) : uuid;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static String timeBased() {
        return timeBased(true);
    }

    public static String timeBased(boolean removeHyphen) {
        var uuid = format(getHiTime()) + HYPHEN +
                format(getLoTime()) + HYPHEN +
                format(getIP()) + HYPHEN +
                format(getJVM()) + HYPHEN +
                format(getCount());
        return removeHyphen ? uuid.replaceAll(HYPHEN, EMPTY) : uuid;
    }

    private static String format(int intValue) {
        String formatted = Integer.toHexString(intValue);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private static String format(short shortValue) {
        String formatted = Integer.toHexString(shortValue);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

}
