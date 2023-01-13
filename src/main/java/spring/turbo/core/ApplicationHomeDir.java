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
import org.springframework.core.io.FileSystemResource;
import spring.turbo.io.PathTreeUtils;
import spring.turbo.util.Asserts;

import javax.annotation.Nullable;
import java.io.File;
import java.io.Serializable;
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
public final class ApplicationHomeDir implements Comparable<ApplicationHomeDir>, Serializable {

    private final File homeDir;

    private ApplicationHomeDir(ApplicationHome home) {
        Asserts.notNull(home);
        this.homeDir = home.getDir().getAbsoluteFile();
    }

    /**
     * 默认方式获取应用程序部署目录
     *
     * @return 实例
     */
    public static ApplicationHomeDir of() {
        return new ApplicationHomeDir(new ApplicationHome());
    }

    /**
     * 获取应用程序部署目录
     *
     * @param sourceClass 启动类
     * @return 实例
     */
    public static ApplicationHomeDir of(@Nullable Class<?> sourceClass) {
        return new ApplicationHomeDir(new ApplicationHome(sourceClass));
    }

    /**
     * 转换成{@link File} 对象
     *
     * @return {@link File}对象
     */
    public File asFile() {
        return this.homeDir;
    }

    /**
     * 转换成{@link Path} 对象
     *
     * @return {@link Path}对象
     */
    public Path asPath() {
        return this.homeDir.toPath();
    }

    /**
     * 转换成{@link String} 对象
     *
     * @return {@link String}对象
     */
    public String asString() {
        return asString(false);
    }

    /**
     * 转换成{@link String} 对象
     *
     * @param forceEndsWithPathSeparator 最后以PathSeparator结束
     * @return {@link String}对象
     * @see File#separator
     */
    public String asString(boolean forceEndsWithPathSeparator) {
        var s = homeDir.getAbsolutePath();
        if (forceEndsWithPathSeparator && !s.endsWith(File.separator)) {
            s = s + File.separator;
        }
        return s;
    }

    /**
     * 解析出{@link FileSystemResource}相关的location
     *
     * @param paths paths
     * @return 结果
     */
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

    /**
     * 递归查找之下所有文件
     *
     * @param maxDepth 查找最大深度
     * @return 查找的所有子文件和目录
     */
    public List<File> listFiles(int maxDepth) {
        return listFiles(maxDepth, null);
    }

    /**
     * 递归查找之下所有文件
     *
     * @param maxDepth 查找最大深度
     * @param filter   过滤器，可为null
     * @return 查找的所有子文件和目录
     */
    public List<File> listFiles(int maxDepth, @Nullable Predicate<File> filter) {
        var filterToUse = Objects.requireNonNullElse(filter, f -> true);
        return PathTreeUtils.list(homeDir.toPath(), maxDepth)
                .map(Path::toFile)
                .filter(filterToUse)
                .toList();
    }

    @Override
    public String toString() {
        return asString(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationHomeDir that = (ApplicationHomeDir) o;
        return homeDir.equals(that.homeDir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(homeDir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@Nullable ApplicationHomeDir o) {
        final var thisFile = this.homeDir;
        final var thatFile = o != null ? o.homeDir : null;
        return Comparator.nullsLast(Comparator.<File>naturalOrder()).compare(thisFile, thatFile);
    }

}
