package spring.turbo.util;

import org.springframework.lang.Nullable;

/**
 * 类型转换工具
 *
 * @author 应卓
 * @since 3.2.5
 */
@SuppressWarnings("unchecked")
public final class CastUtils {

    /**
     * 私有构造方法
     */
    private CastUtils() {
        super();
    }

    @Nullable
    public static <T> T castNullable(@Nullable Object object) {
        return (T) object;
    }

    public static <T> T castNonNull(Object object) {
        Asserts.notNull(object);
        return (T) object;
    }

}
