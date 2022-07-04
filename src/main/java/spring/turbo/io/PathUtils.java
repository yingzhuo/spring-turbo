/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.util.FileSystemUtils;
import spring.turbo.io.function.PathPredicateFactories;
import spring.turbo.util.Asserts;
import spring.turbo.util.ListFactories;
import spring.turbo.util.StringFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * {@link Path}相关工具
 *
 * @author 应卓
 * @see Paths
 * @see Files
 * @see PathTreeUtils
 * @since 1.0.7
 */
public final class PathUtils {

    /**
     * 私有构造方法
     */
    private PathUtils() {
        super();
    }

    /**
     * 转换成{@link File}类型
     *
     * @param path path
     * @return 结果
     */
    public static File toFile(Path path) {
        Asserts.notNull(path);
        return path.toFile();
    }

    /**
     * 创建Path实例
     *
     * @param first 首个path
     * @param more  其他path
     * @return Path实例
     */
    public static Path createPath(String first, String... more) {
        Asserts.notNull(first);
        return Paths.get(first, more).normalize();
    }

    /**
     * 创建Path实例 (绝对路径)
     *
     * @param first 首个path
     * @param more  其他path
     * @return Path实例
     */
    public static Path createAbsolutePath(String first, String... more) {
        return createPath(first, more).toAbsolutePath();
    }

    /**
     * 创建文件实例
     *
     * @param first 首个path
     * @param more  其他path
     * @return 文件实例
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Path createFile(String first, String... more) {
        final Path path = createPath(first, more);
        createFile(path);
        return path;
    }

    /**
     * 创建文件实例
     *
     * @param path path
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void createFile(Path path) {
        Asserts.notNull(path);

        try {
            boolean success = toFile(path).createNewFile();
            if (!success) {
                final String msg = StringFormatter.format("unable to create file: {}", path);
                throw IOExceptionUtils.toUnchecked(msg);
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 创建目录实例
     *
     * @param first 首个path
     * @param more  其他path
     * @return 目录实例
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Path createDirectory(String first, String... more) {
        Asserts.notNull(first);

        final Path path = createPath(first, more);
        createDirectory(path);
        return path;
    }

    /**
     * 创建目录实例
     *
     * @param path path
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void createDirectory(Path path) {
        Asserts.notNull(path);

        if (isExists(path)) {
            if (isDirectory(path)) {
                return;
            } else {
                final String msg = StringFormatter.format("unable to create dir: {}", path);
                throw IOExceptionUtils.toUnchecked(msg);
            }
        }

        boolean success = toFile(path).mkdirs();
        if (!success) {
            final String msg = StringFormatter.format("unable to create dir: {}", path);
            throw IOExceptionUtils.toUnchecked(msg);
        }
    }

    /**
     * 移动文件或目录
     *
     * @param source          源
     * @param target          目标
     * @param replaceExisting 覆盖已存在的目标
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void move(Path source, Path target, boolean replaceExisting) {
        Asserts.notNull(source);
        Asserts.notNull(target);

        final List<CopyOption> copyOptions =
                ListFactories.newLinkedList();

        if (replaceExisting) {
            copyOptions.add(StandardCopyOption.REPLACE_EXISTING);
        }

        try {
            Files.move(source, target, copyOptions.toArray(new CopyOption[0]));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 拷贝文件或目录
     *
     * @param source          源
     * @param target          目标
     * @param replaceExisting 覆盖已存在的目标
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void copy(Path source, Path target, boolean replaceExisting) {
        Asserts.notNull(source);
        Asserts.notNull(target);

        final List<CopyOption> copyOptions =
                ListFactories.newLinkedList();

        if (replaceExisting) {
            copyOptions.add(StandardCopyOption.REPLACE_EXISTING);
        }

        try {
            Files.copy(source, target, copyOptions.toArray(new CopyOption[0]));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 创建文件或更新最后更新时间
     *
     * @param path path
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void touch(Path path) {
        Asserts.notNull(path);
        try {
            if (Files.exists(path)) {
                Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param path path
     * @return 存在时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isExists(Path path) {
        Asserts.notNull(path);
        return Files.exists(path);
    }

    /**
     * 判断path是否为目录
     *
     * @param path path
     * @return 是目录时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isDirectory(Path path) {
        Asserts.notNull(path);
        return Files.isDirectory(path);
    }

    /**
     * 判断path是否为空目录
     *
     * @param path path
     * @return 是空目录时返回 {@code true} 否则返回 {@code false}
     * @throws java.io.UncheckedIOException IO异常
     */
    public static boolean isEmptyDirectory(Path path) {
        if (!isDirectory(path)) {
            return false;
        }

        try {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 判断path是否为一般文件
     *
     * @param path path
     * @return 是一般文件时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isRegularFile(Path path) {
        Asserts.notNull(path);
        return Files.isRegularFile(path);
    }

    /**
     * 判断path是否为Link
     *
     * @param path path
     * @return 是Link时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isSymbolicLink(Path path) {
        Asserts.notNull(path);
        return Files.isSymbolicLink(path);
    }

    /**
     * 判断path是否为隐藏目录或文件
     *
     * @param path path
     * @return 是隐藏目录或文件时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isHidden(Path path) {
        Asserts.notNull(path);
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断path是否为可读可写
     *
     * @param path path
     * @return 是可读可写时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isReadableAndWritable(Path path) {
        return isReadable(path) && isWritable(path);
    }

    /**
     * 判断path是否为可读
     *
     * @param path path
     * @return 是可读时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isReadable(Path path) {
        Asserts.notNull(path);
        return Files.isReadable(path);
    }

    /**
     * 判断path是否为可写
     *
     * @param path path
     * @return 是可读时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isWritable(Path path) {
        Asserts.notNull(path);
        return Files.isWritable(path);
    }

    /**
     * 判断path是否可执行
     *
     * @param path path
     * @return 是可执行时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isExecutable(Path path) {
        Asserts.notNull(path);
        return Files.isExecutable(path);
    }

    /**
     * 获取文件的大小
     *
     * @param path path
     * @return 文件尺寸
     * @throws java.io.UncheckedIOException IO异常
     */
    public static long size(Path path) {
        Asserts.notNull(path);

        if (!isExists(path)) {
            throw IOExceptionUtils.toUnchecked("file not exists");
        }

        try {
            return Files.size(path);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 删除目录或文件
     *
     * @param path path
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void delete(Path path) {
        Asserts.notNull(path);

        try {
            if (!isExists(path)) {
                return;
            }

            if (isRegularFile(path)) {
                Files.deleteIfExists(path);
            } else {
                FileSystemUtils.deleteRecursively(path);
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 删除目录或文件
     *
     * @param path path
     */
    public static void deleteQuietly(Path path) {
        Asserts.notNull(path);

        try {
            delete(path);
        } catch (Throwable e) {
            // nop
        }
    }

    /**
     * 清空目录
     *
     * @param path path
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void cleanDirectory(Path path) {
        if (!isDirectory(path)) {
            return;
        }

        PathTreeUtils.list(path, 1, PathPredicateFactories.alwaysTrue()).forEach(found -> {
            if (!found.equals(path)) {
                delete(found);
            }
        });
    }

    /**
     * 清空目录
     *
     * @param path path
     */
    public static void cleanDirectoryQuietly(Path path) {
        if (!isDirectory(path)) {
            return;
        }

        PathTreeUtils.list(path, 1, PathPredicateFactories.alwaysTrue()).forEach(found -> {
            if (!found.equals(path)) {
                deleteQuietly(found);
            }
        });
    }

    /**
     * 获取创建时间
     *
     * @param path path
     * @return 创建时间
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Date getCreationTime(Path path) {
        Asserts.notNull(path);
        try {
            final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 获取最后更新时间
     *
     * @param path path
     * @return 最后更新时间
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Date getLastModifiedTime(Path path) {
        Asserts.notNull(path);
        try {
            final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return new Date(attributes.lastModifiedTime().to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 获取最后访问
     *
     * @param path path
     * @return 最后访问时间
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Date getLastAccessTime(Path path) {
        Asserts.notNull(path);
        try {
            final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return new Date(attributes.lastAccessTime().to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 读取所有的行
     *
     * @param path path
     * @return 多行数据
     * @throws java.io.UncheckedIOException IO异常
     */
    public static List<String> readLines(Path path) {
        return readLines(path, UTF_8);
    }

    /**
     * 读取所有的行
     *
     * @param path    path
     * @param charset 字符编码
     * @return 多行数据
     * @throws java.io.UncheckedIOException IO异常
     */
    public static List<String> readLines(Path path, Charset charset) {
        Asserts.notNull(path);
        Asserts.notNull(charset);

        try {
            return Files.readAllLines(path, charset);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 读取二进制数据
     *
     * @param path path
     * @return 文件内容
     * @throws java.io.UncheckedIOException IO异常
     */
    public static byte[] readBytes(Path path) {
        Asserts.notNull(path);

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 写入二进制数据
     *
     * @param path              path
     * @param bytes             内容
     * @param createIfNotExists 没有文件时是否应该创建之
     * @param append            是否使用追加写入
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void writeBytes(Path path, byte[] bytes, boolean createIfNotExists, boolean append) {
        Asserts.notNull(path);
        Asserts.notNull(bytes);

        final List<OpenOption> openOptions =
                ListFactories.newArrayList(StandardOpenOption.WRITE);

        if (createIfNotExists) {
            openOptions.add(StandardOpenOption.CREATE);
        }

        if (append) {
            openOptions.add(StandardOpenOption.APPEND);
        } else {
            openOptions.add(StandardOpenOption.TRUNCATE_EXISTING);
        }

        try {
            Files.write(path, bytes, openOptions.toArray(new OpenOption[0]));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 写入文本数据
     *
     * @param path              path
     * @param lines             文本数据
     * @param createIfNotExists 没有文件时是否应该创建之
     * @param append            是否使用追加写入
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void writeLines(Path path, List<String> lines, boolean createIfNotExists, boolean append) {
        writeLines(path, lines, UTF_8, createIfNotExists, append);
    }

    /**
     * 写入文本数据
     *
     * @param path              path
     * @param lines             文本数据
     * @param charset           字符编码
     * @param createIfNotExists 没有文件时是否应该创建之
     * @param append            是否使用追加写入
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void writeLines(Path path, List<String> lines, Charset charset, boolean createIfNotExists, boolean append) {
        Asserts.notNull(path);
        Asserts.notNull(lines);
        Asserts.notNull(charset);

        final List<OpenOption> openOptions =
                ListFactories.newArrayList(StandardOpenOption.WRITE);

        if (createIfNotExists) {
            openOptions.add(StandardOpenOption.CREATE);
        }

        if (append) {
            openOptions.add(StandardOpenOption.APPEND);
        } else {
            openOptions.add(StandardOpenOption.TRUNCATE_EXISTING);
        }

        try {
            Files.write(path, lines, charset, openOptions.toArray(new OpenOption[0]));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 转换为绝对路径
     *
     * @param path path
     * @return 结果
     */
    public static Path toAbsolutePath(Path path) {
        Asserts.notNull(path);
        return path.toAbsolutePath();
    }

    /**
     * 判断是否是同一个文件
     *
     * @param p1 p1
     * @param p2 p2
     * @return 结果
     * @throws java.io.UncheckedIOException IO异常
     */
    public static boolean isSameFile(Path p1, Path p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);

        try {
            return Files.isSameFile(p1, p2);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

}
