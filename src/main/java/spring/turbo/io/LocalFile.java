/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import spring.turbo.io.function.LocalFilePredicate;
import spring.turbo.util.FilenameUtils;
import spring.turbo.util.StringPool;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @see Resource
 * @see ResourceOption
 * @see PathUtils
 * @see PathTreeUtils
 * @see LocalFilePredicate
 * @see LocalFileInterceptor
 * @see LocalFileInterceptorChain
 * @since 1.1.1
 */
public interface LocalFile extends Serializable {

    public static LocalFile of(String first, String... more) {
        return new PathLocalFile(first, more);
    }

    public static LocalFile of(File file) {
        return new PathLocalFile(file);
    }

    public static LocalFile of(Path path) {
        return new PathLocalFile(path);
    }

    public Path asPath();

    public default File asFile() {
        return asPath().toFile();
    }

    public default Resource asResource() {
        return new FileSystemResource(asPath());
    }

    public default ResourceOption asResourceOption() {
        return ResourceOptions.of(asResource());
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default boolean exists() {
        return PathUtils.isExists(asPath());
    }

    public default boolean isReadable() {
        return PathUtils.isReadable(asPath());
    }

    public default boolean isWritable() {
        return PathUtils.isWritable(asPath());
    }

    public default boolean isExecutable() {
        return PathUtils.isExecutable(asPath());
    }

    public default boolean isDirectory() {
        return PathUtils.isDirectory(asPath());
    }

    public default boolean isRegularFile() {
        return PathUtils.isRegularFile(asPath());
    }

    public default boolean isSymbolicLink() {
        return PathUtils.isSymbolicLink(asPath());
    }

    public default boolean isEmptyDirectory() {
        return PathUtils.isEmptyDirectory(asPath());
    }

    public default boolean isHidden() {
        return PathUtils.isHidden(asPath());
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default Date getCreationTime() {
        return PathUtils.getCreationTime(asPath());
    }

    public default Date getLastAccessTime() {
        return PathUtils.getLastAccessTime(asPath());
    }

    public default Date getLastModifiedTime() {
        return PathUtils.getLastModifiedTime(asPath());
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default void waiteUntilReadable() {
        FileWaitingUtils.waitUntilIsReadable(asPath());
    }

    public default void waiteUntilReadable(int maxWaitingCount, long millisPerWaiting) {
        FileWaitingUtils.waitUntilIsReadable(asPath(), maxWaitingCount, millisPerWaiting);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default List<String> readLines() {
        return PathUtils.readLines(asPath());
    }

    public default List<String> readLines(Charset charset) {
        return PathUtils.readLines(asPath(), charset);
    }

    public default byte[] readBytes() {
        return PathUtils.readBytes(asPath());
    }

    public default void writeLines(Path path, List<String> lines, boolean createIfNotExists, boolean append) {
        PathUtils.writeLines(asPath(), lines, createIfNotExists, append);
    }

    public default void writeBytes(byte[] bytes, boolean createIfNotExists, boolean append) {
        PathUtils.writeBytes(asPath(), bytes, createIfNotExists, append);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default String getPathAsString() {
        return asPath().toString();
    }

    public default String getFilenameAsString() {
        return asPath().getFileName().toString();
    }

    public default String getBaseNameAsString() {
        return FilenameUtils.getBaseName(getFilenameAsString());
    }

    public default String getExtension() {
        return getExtension(true);
    }

    public default String getExtension(boolean startWithDot) {
        String ext = FilenameUtils.getExtension(getFilenameAsString());
        if (StringPool.EMPTY.equals(ext)) {
            return ext;
        } else {
            return startWithDot ? StringPool.DOT + ext : ext;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default InputStream openAsInputStream() {
        try {
            return new BufferedInputStream(new FileInputStream(asFile()));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public default OutputStream openAsOutputStream(boolean append) {
        try {
            return new BufferedOutputStream(new FileOutputStream(asFile(), append));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default Stream<LocalFile> list(int maxDepth) {
        if (!isDirectory()) {
            return Stream.empty();
        }

        return PathTreeUtils.list(asPath(), maxDepth)
                .map(LocalFile::of);
    }

    public default Stream<LocalFile> list() {
        return list(Integer.MAX_VALUE);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public default void delete() {
        PathUtils.delete(asPath());
    }

    public default void deleteQuietly() {
        PathUtils.deleteQuietly(asPath());
    }

    public default void cleanDirectory() {
        PathUtils.cleanDirectory(asPath());
    }

    public default void cleanDirectoryQuietly() {
        PathUtils.cleanDirectoryQuietly(asPath());
    }

    public default long size() {
        return PathUtils.size(asPath());
    }

}
