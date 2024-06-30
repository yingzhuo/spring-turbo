/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * {@link Locale} 相关工具
 *
 * @author 应卓
 * @since 3.3.2
 */
public final class LocaleUtils {

    /**
     * 私有构造方法
     */
    private LocaleUtils() {
        super();
    }

    /**
     * 获取{@link Locale} 实例。会强制去掉变体部分
     *
     * @return 实例
     */
    public static Locale getLocale() {
        try {
            var locale = LocaleContextHolder.getLocale();
            var lang = locale.getLanguage();
            var country = locale.getCountry();
            return new Locale(lang, country);
        } catch (Throwable e) {
            return Locale.getDefault();
        }
    }

}
