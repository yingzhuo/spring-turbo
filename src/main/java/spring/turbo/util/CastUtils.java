package spring.turbo.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 类型转换工具
 *
 * @author 应卓
 *
 * @since 3.2.5
 */
@SuppressWarnings("unchecked")
public final class CastUtils {

    @Nullable
    public static <T> T cast(@Nullable Object object) {
        return (T) object;
    }

    @NonNull
    public static <T> T castNonNull(Object object) {
        Asserts.notNull(object);
        return (T) object;
    }

    /**
     * 私有构造方法
     */
    private CastUtils() {
        super();
    }

}
