package spring.turbo.util;

import static java.util.UUID.randomUUID;
import static spring.turbo.util.StringPool.EMPTY;
import static spring.turbo.util.StringPool.HYPHEN;
import static spring.turbo.util.id.IDGeneratorHelper.*;

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

    public static String v4(boolean removeHyphen) {
        var uuid = randomUUID().toString();
        return removeHyphen ? uuid.replaceAll(HYPHEN, EMPTY) : uuid;
    }

    public static String classic32() {
        return v4(true);
    }

    public static String classic36() {
        return v4(false);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static String timeBased32() {
        return timeBased(true);
    }

    public static String timeBased36() {
        return timeBased(false);
    }

    public static String timeBased(boolean removeHyphen) {
        var builder = new StringBuilder(removeHyphen ? 32 : 36);

        builder.append(getFormattedHiTime());
        if (!removeHyphen) {
            builder.append(HYPHEN);
        }

        builder.append(getFormattedLoTime());
        if (!removeHyphen) {
            builder.append(HYPHEN);
        }

        builder.append(getFormattedIP());
        if (!removeHyphen) {
            builder.append(HYPHEN);
        }

        builder.append(getFormattedJVM());
        if (!removeHyphen) {
            builder.append(HYPHEN);
        }

        builder.append(getFormattedCount());
        return builder.toString();
    }

}
