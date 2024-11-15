package spring.turbo.core;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.DataBinder;

/**
 * {@link DataBinder} 相关工具
 *
 * @author 应卓
 * @see org.springframework.validation.DataBinder
 * @since 3.3.5
 */
public final class DataBinderUtils {

    /**
     * 私有构造方法
     */
    private DataBinderUtils() {
    }

    /**
     * 新建一个DataBinder
     *
     * @param target binding对象
     * @return {@link DataBinder} 实例
     */
    public static DataBinder createDataBinder(Object target) {
        return createDataBinder(target, null);
    }

    /**
     * 新建一个DataBinder
     *
     * @param target     binding对象
     * @param objectName 名称
     * @return {@link DataBinder} 实例
     */
    public static DataBinder createDataBinder(Object target, @Nullable String objectName) {
        Assert.notNull(target, "target is required");

        var conversionService =
                SpringUtils.getBean(ConversionService.class)
                        .orElseGet(DefaultFormattingConversionService::new);

        var binder = new DataBinder(target, objectName);
        binder.setConversionService(conversionService);
        return binder;
    }

}
