package spring.turbo.core;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * {@link Locale} 相关工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class LocaleUtils {

    /**
     * 私有构造方法
     */
    private LocaleUtils() {
    }

    /**
     * 获取{@link Locale} 实例。会强制去掉变体部分
     *
     * @return 实例
     */
    public static Locale getLocale() {
        return getLocale(true);
    }

    /**
     * 获取{@link Locale} 实例
     *
     * @param removeVariant 是否强制去掉变体部分
     * @return 实例
     */
    public static Locale getLocale(boolean removeVariant) {
        Locale locale;
        try {
            locale = LocaleContextHolder.getLocale();
        } catch (Throwable e) {
            locale = Locale.getDefault();
        }

        if (removeVariant) {
            var lang = locale.getLanguage();
            var country = locale.getCountry();

            if (lang != null && country != null) {
                return new Locale(lang, country);
            } else if (lang != null) {
                return new Locale(lang);
            } else {
                return Locale.getDefault();
            }
        } else {
            return locale;
        }
    }

}
