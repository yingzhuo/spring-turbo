package spring.turbo.util;

import java.util.zip.CRC32;

/**
 * CRC32相关工具
 *
 * @author 应卓
 * @since 3.4.0
 */
public final class CRC32Utils {

    /**
     * 私有构造方法
     */
    private CRC32Utils() {
    }

    /**
     * 计算数据的CRC32累加和
     *
     * @param data 数据
     * @return 累加和
     */
    public static Long crc32Value(byte[] data) {
        var crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    /**
     * 计算数据的CRC32累加和
     *
     * @param data 数据
     * @return 累加和 (字符串表示)
     */
    public static String crc32Hex(byte[] data) {
        var value = crc32Value(data);
        return Long.toHexString(value);
    }

}
