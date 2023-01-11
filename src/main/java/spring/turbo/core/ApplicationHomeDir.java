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
import spring.turbo.io.PathTreeUtils;
import spring.turbo.util.Asserts;

import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 应用程序部署目录
 *
 * @author 应卓
 * @see #of()
 * @see #of(Class)
 * @since 2.0.7
 */
public final class ApplicationHomeDir implements Comparable<ApplicationHomeDir> {

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

    public List<String> listFiles(int maxDepth, @Nullable Predicate<File> filter) {
        final var filterToUse = Objects.requireNonNullElse(filter, f -> true);

        return PathTreeUtils.list(home.toPath(), maxDepth)
                .filter(path -> filterToUse.test(path.toFile()))
                .map(p -> p.toAbsolutePath().toString())
                .toList();
    }

    @Override
    public String toString() {
        return asString(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationHomeDir that = (ApplicationHomeDir) o;
        return home.equals(that.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(home);
    }

    @Override
    public int compareTo(@Nullable ApplicationHomeDir o) {
        final var thisFile = this.home;
        final var thatFile = o != null ? o.home : null;
        return Comparator.nullsLast(Comparator.<File>naturalOrder())
                .compare(thisFile, thatFile);
    }

}
