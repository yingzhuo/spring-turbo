/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;

import java.io.PrintStream;
import java.util.List;

import static spring.turbo.util.collection.ListFactories.nullSafeNewArrayList;

/**
 * @author 应卓
 *
 * @see org.springframework.boot.ResourceBanner
 * @see EnvironmentPrintingBanner
 *
 * @since 3.2.4
 */
public class BannerChain implements Banner {

    private final List<Banner> delegatingBanners;

    public BannerChain(@Nullable Banner... delegatingBanners) {
        this.delegatingBanners = nullSafeNewArrayList(delegatingBanners);
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (var banner : this.delegatingBanners) {
            if (banner != null) {
                banner.printBanner(environment, sourceClass, out);
            }
        }
    }

}
