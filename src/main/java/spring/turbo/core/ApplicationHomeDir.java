/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import spring.turbo.io.PathTreeUtils;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 应用程序部署目录
 *
 * @author 应卓
 * @since 2.0.7
 */
public final class ApplicationHomeDir {

    private final File home;

    private ApplicationHomeDir(ApplicationHome home) {
        Asserts.notNull(home);
        this.home = home.getDir().getAbsoluteFile();
    }

    public static ApplicationHomeDir of() {
        return new ApplicationHomeDir(new ApplicationHome());
    }

    public static ApplicationHomeDir of(@Nullable Class<?> sourceClass) {
        return new ApplicationHomeDir(new ApplicationHome(sourceClass));
    }

    public File asFile() {
        return this.home;
    }

    public Path asPath() {
        return this.home.toPath();
    }

    public String asString() {
        return asString(false);
    }

    public String asString(boolean forceEndsWithPathSeparator) {
        var s = home.getAbsolutePath();
        if (forceEndsWithPathSeparator && !s.endsWith(File.separator)) {
            s = s + File.separator;
        }
        return s;
    }

    public String resolveResourceLocation(@Nullable String... paths) {
        final var builder = new StringBuilder("file:");
        builder.append(asString(false));
        builder.append(File.separator);

        if (paths != null) {
            for (String path : paths) {
                builder.append(path);
                builder.append(File.separatorChar);
            }
        }

        return builder.substring(0, builder.length() - 1);
    }

    public List<String> listFiles() {
        return listFiles(Integer.MAX_VALUE, null);
    }

    public List<String> listFiles(int maxDepth) {
        return listFiles(maxDepth, null);
    }

    /**
     * @see AntStyleFilter#newInstance(String...)
     */
    public List<String> listFiles(int maxDepth, @Nullable Predicate<File> filter) {
        final var filterToUse = Objects.requireNonNullElse(filter, f -> true);

        return PathTreeUtils.list(home.toPath(), maxDepth)
                .filter(path -> filterToUse.test(path.toFile()))
                .map(p -> p.toAbsolutePath().toString())
                .toList();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * @author 应卓
     * @see #newInstance(String...)
     * @since 2.0.8
     */
    public static final class AntStyleFilter implements Predicate<File> {

        private final PathMatcher matcher = new AntPathMatcher();
        private final String[] antPatterns;

        public static AntStyleFilter newInstance(String... patterns) {
            List<String> list = new ArrayList<>();
            StringUtils.blankSafeAddAll(list, patterns);
            return new AntStyleFilter(list.toArray(new String[0]));
        }

        /**
         * 私有构造方法
         *
         * @param antPatterns 多个匹配策略
         */
        private AntStyleFilter(String[] antPatterns) {
            this.antPatterns = antPatterns;
        }

        @Override
        public boolean test(@Nullable File file) {
            if (antPatterns.length == 0 || file == null) {
                return false;
            }

            for (var pattern : this.antPatterns) {
                if (matcher.match(pattern, file.getAbsolutePath())) {
                    return true;
                }
            }
            return false;
        }
    }

}
