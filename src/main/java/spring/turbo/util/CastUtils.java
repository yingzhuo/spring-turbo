package spring.turbo.util;

/**
 * 类型转换工具
 *
 * @author 应卓
 *
 * @since 3.2.5
 */
public final class CastUtils {

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

    /**
     * 私有构造方法
     */
    private CastUtils() {
        super();
    }

}
