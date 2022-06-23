/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import spring.turbo.util.Asserts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

/**
 * @author 应卓
 * @see Files
 * @see java.nio.file.Paths
 * @see PathUtils
 * @see PathTreeUtils
 * @since 1.1.0
 */
public final class FileUtils {

    /**
     * 私有构造方法
     */
    private FileUtils() {
        super();
    }

    /**
     * 转换为Path实例
     *
     * @param file file
     * @return path
     */
    public static Path toPath(File file) {
        Asserts.notNull(file);
        return file.toPath();
    }

    /**
     * 创建Path实例
     *
     * @param first 首个path
     * @param more  其他path
     * @return Path实例
     */
    public static File createFile(String first, String... more) {
        return PathUtils.toFile(
                PathUtils.createPath(first, more)
        );
    }

    /**
     * 创建文件实例
     *
     * @param file file
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void createFile(File file) {
        Asserts.notNull(file);
        PathUtils.createFile(file.toPath());
    }

    /**
     * 创建目录实例
     *
     * @param file file
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void createDirectory(File file) {
        Asserts.notNull(file);
        PathUtils.createDirectory(file.toPath());
    }

    /**
     * 移动文件或目录
     *
     * @param source          源
     * @param target          目标
     * @param replaceExisting 覆盖已存在的目标
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void move(File source, File target, boolean replaceExisting) {
        Asserts.notNull(source);
        Asserts.notNull(target);
        PathUtils.move(source.toPath(), target.toPath(), replaceExisting);
    }

    /**
     * 拷贝文件或目录
     *
     * @param source          源
     * @param target          目标
     * @param replaceExisting 覆盖已存在的目标
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void copy(File source, File target, boolean replaceExisting) {
        Asserts.notNull(source);
        Asserts.notNull(target);
        PathUtils.copy(source.toPath(), target.toPath(), replaceExisting);
    }

    /**
     * 创建文件或更新最后更新时间
     *
     * @param file file
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void touch(File file) {
        Asserts.notNull(file);
        PathUtils.touch(file.toPath());
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param file file
     * @return 存在时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isExists(File file) {
        Asserts.notNull(file);
        return Files.exists(file.toPath());
    }

    /**
     * 判断path是否为目录
     *
     * @param file file
     * @return 是目录时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isDirectory(File file) {
        Asserts.notNull(file);
        return Files.isDirectory(file.toPath());
    }

    /**
     * 判断path是否为空目录
     *
     * @param file file
     * @return 是空目录时返回 {@code true} 否则返回 {@code false}
     * @throws java.io.UncheckedIOException IO异常
     */
    public static boolean isEmptyDirectory(File file) {
        return PathUtils.isEmptyDirectory(file.toPath());
    }

    /**
     * 判断path是否为一般文件
     *
     * @param file file
     * @return 是一般文件时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isRegularFile(File file) {
        Asserts.notNull(file);
        return Files.isRegularFile(file.toPath());
    }

    /**
     * 判断path是否为Link
     *
     * @param file file
     * @return 是Link时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isSymbolicLink(File file) {
        Asserts.notNull(file);
        return Files.isSymbolicLink(file.toPath());
    }

    /**
     * 判断path是否为隐藏目录或文件
     *
     * @param file file
     * @return 是隐藏目录或文件时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isHidden(File file) {
        Asserts.notNull(file);
        try {
            return Files.isHidden(file.toPath());
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断path是否为可读可写
     *
     * @param file file
     * @return 是可读可写时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isReadableAndWritable(File file) {
        Asserts.notNull(file);
        return PathUtils.isReadableAndWritable(file.toPath());
    }

    /**
     * 判断path是否为可读
     *
     * @param file file
     * @return 是可读时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isReadable(File file) {
        Asserts.notNull(file);
        return PathUtils.isReadable(file.toPath());
    }

    /**
     * 判断path是否为可写
     *
     * @param file file
     * @return 是可读时返回 {@code true} 否则返回 {@code false}
     */
    public static boolean isWritable(File file) {
        Asserts.notNull(file);
        return PathUtils.isWritable(file.toPath());
    }

    /**
     * 获取文件的大小
     *
     * @param file file
     * @return 文件尺寸
     * @throws java.io.UncheckedIOException IO异常
     */
    public static long size(File file) {
        Asserts.notNull(file);
        return PathUtils.size(file.toPath());
    }

    /**
     * 删除目录或文件
     *
     * @param file file
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void delete(File file) {
        Asserts.notNull(file);
        PathUtils.delete(file.toPath());
    }

    /**
     * 删除目录或文件
     *
     * @param file file
     */
    public static void deleteQuietly(File file) {
        Asserts.notNull(file);
        PathUtils.deleteQuietly(file.toPath());
    }

    /**
     * 清空目录
     *
     * @param file file
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void cleanDirectory(File file) {
        Asserts.notNull(file);
        PathUtils.cleanDirectory(file.toPath());
    }

    /**
     * 清空目录
     *
     * @param file file
     */
    public static void cleanDirectoryQuietly(File file) {
        Asserts.notNull(file);
        PathUtils.cleanDirectoryQuietly(file.toPath());
    }

    /**
     * 获取创建时间
     *
     * @param file file
     * @return 创建时间
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Date getCreationTime(File file) {
        Asserts.notNull(file);
        return PathUtils.getCreationTime(file.toPath());
    }

    /**
     * 获取最后更新时间
     *
     * @param file file
     * @return 最后更新时间
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Date getLastModifiedTime(File file) {
        Asserts.notNull(file);
        return PathUtils.getLastModifiedTime(file.toPath());
    }

    /**
     * 获取最后访问
     *
     * @param file file
     * @return 最后访问时间
     * @throws java.io.UncheckedIOException IO异常
     */
    public static Date getLastAccessTime(File file) {
        Asserts.notNull(file);
        return PathUtils.getLastAccessTime(file.toPath());
    }

    /**
     * 读取所有的行
     *
     * @param file file
     * @return 多行数据
     * @throws java.io.UncheckedIOException IO异常
     */
    public static List<String> readLines(File file) {
        Asserts.notNull(file);
        return PathUtils.readLines(file.toPath());
    }

    /**
     * 读取所有的行
     *
     * @param file    path
     * @param charset 字符编码
     * @return 多行数据
     * @throws java.io.UncheckedIOException IO异常
     */
    public static List<String> readLines(File file, Charset charset) {
        Asserts.notNull(file);
        Asserts.notNull(charset);
        return PathUtils.readLines(file.toPath(), charset);
    }

    /**
     * 读取二进制数据
     *
     * @param file file
     * @return 文件内容
     * @throws java.io.UncheckedIOException IO异常
     */
    public static byte[] readBytes(File file) {
        Asserts.notNull(file);
        return PathUtils.readBytes(file.toPath());
    }

    /**
     * 写入二进制数据
     *
     * @param file              file
     * @param bytes             内容
     * @param createIfNotExists 没有文件时是否应该创建之
     * @param append            是否使用追加写入
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void writeBytes(File file, byte[] bytes, boolean createIfNotExists, boolean append) {
        Asserts.notNull(file);
        Asserts.notNull(bytes);
        PathUtils.writeBytes(file.toPath(), bytes, createIfNotExists, append);
    }

    /**
     * 写入文本数据
     *
     * @param file              file
     * @param lines             文本数据
     * @param createIfNotExists 没有文件时是否应该创建之
     * @param append            是否使用追加写入
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void writeLines(File file, List<String> lines, boolean createIfNotExists, boolean append) {
        PathUtils.writeLines(file.toPath(), lines, createIfNotExists, append);
    }

    /**
     * 写入文本数据
     *
     * @param file              file
     * @param lines             文本数据
     * @param charset           字符编码
     * @param createIfNotExists 没有文件时是否应该创建之
     * @param append            是否使用追加写入
     * @throws java.io.UncheckedIOException IO异常
     */
    public static void writeLines(File file, List<String> lines, Charset charset, boolean createIfNotExists, boolean append) {
        Asserts.notNull(file);
        Asserts.notNull(lines);
        Asserts.notNull(charset);
        PathUtils.writeLines(file.toPath(), lines, charset, createIfNotExists, append);
    }

    /**
     * 判断是否是同一个文件
     *
     * @param f1 file1
     * @param f2 file2
     * @return 结果
     * @throws java.io.UncheckedIOException IO异常
     */
    public static boolean isSameFile(File f1, File f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return PathUtils.isSameFile(f1.toPath(), f2.toPath());
    }

}
