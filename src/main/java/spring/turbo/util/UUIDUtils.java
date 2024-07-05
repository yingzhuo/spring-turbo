package spring.turbo.util;

import java.util.UUID;

import static spring.turbo.util.StringPool.EMPTY;
import static spring.turbo.util.StringPool.HYPHEN;

/**
 * {@link java.util.UUID} 相关工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class UUIDUtils {

    /**
     * 私有构造方法
     */
    private UUIDUtils() {
    }

    /**
     * 生成随机UUID字符串
     *
     * @return 随机UUID字符串
     */
    public static String uuid36() {
        return randomUUID(false);
    }

    /**
     * 生成随机UUID字符串，并删除 {@code "-"} 字符
     *
     * @return 随机UUID字符串
     */
    public static String uuid32() {
        return randomUUID(true);
    }

    /**
     * 生成随机UUID字符串
     *
     * @param removeHyphen 结果中是否要移除字符 {@code "-"}
     * @return 随机UUID字符串
     */
    public static String randomUUID(boolean removeHyphen) {
        final var uuid = UUID.randomUUID().toString();
        return removeHyphen ? uuid.replaceAll(HYPHEN, EMPTY) : uuid;
    }

}
