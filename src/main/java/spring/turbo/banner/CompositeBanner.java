/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.Nullable;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static spring.turbo.util.collection.CollectionUtils.nullSafeAddAll;
import static spring.turbo.util.collection.ListFactories.nullSafeNewArrayList;

/**
 * @author 应卓
 *
 * @see org.springframework.boot.ResourceBanner
 * @see EnvironmentPrintingBanner
 *
 * @since 3.2.4
 */
public class CompositeBanner implements Banner {

    private final List<Banner> delegatingBanners;

    /**
     * 获取创建器
     *
     * @return 创建器实例
     */
    public static CompositeBanner.Builder builder() {
        return new Builder();
    }

    /**
     * 构造方法
     *
     * @param delegatingBanners
     *            代理的其他Banner实现
     */
    public CompositeBanner(@Nullable Banner... delegatingBanners) {
        this.delegatingBanners = nullSafeNewArrayList(delegatingBanners);
    }

    /**
     * 构造方法
     *
     * @param delegatingBanners
     *            代理的其他Banner实现
     */
    public CompositeBanner(@Nullable List<Banner> delegatingBanners) {
        final var list = new ArrayList<Banner>();
        nullSafeAddAll(list, delegatingBanners);
        this.delegatingBanners = list;
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (var banner : this.delegatingBanners) {
            if (banner != null) {
                banner.printBanner(environment, sourceClass, out);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 创建器
     */
    public static class Builder {

        private final List<Banner> banners = new LinkedList<>();

        /**
         * 私有构造方法
         */
        private Builder() {
            super();
        }

        public Builder addClassPathResource(String resourceLocation) {
            this.banners.add(new ResourceBanner(new ClassPathResource(resourceLocation)));
            return this;
        }

        public Builder addFileSystemResource(String resourceLocation) {
            this.banners.add(new ResourceBanner(new FileSystemResource(resourceLocation)));
            return this;
        }

        public Builder add(Banner banner) {
            this.banners.add(banner);
            return this;
        }

        public CompositeBanner build() {
            return new CompositeBanner(banners);
        }
    }

}
