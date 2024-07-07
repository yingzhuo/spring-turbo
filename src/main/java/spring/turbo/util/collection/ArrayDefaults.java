package spring.turbo.util.collection;

import org.springframework.lang.Nullable;

import static spring.turbo.util.collection.ArrayUtils.isNullOrEmpty;

/**
 * 数组默认值相关工具
 *
 * @author 应卓
 * @see ArrayUtils
 * @since 2.1.0
 */
public final class ArrayDefaults {

    /**
     * 私有构造方法
     */
    private ArrayDefaults() {
    }

    public static <T> T[] nullToDefault(@Nullable T[] array, T[] defaultArray) {
        return array == null ? defaultArray : array;
    }

    public static <T> T[] emptyToDefault(@Nullable T[] array, T[] defaultArray) {
        return isNullOrEmpty(array) ? defaultArray : array;
    }

}
