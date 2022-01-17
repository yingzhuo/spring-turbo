/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.nio.file.Paths;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 文件名处理工具
 *
 * @author 应卓
 * @since 1.0.5
 */
public final class FilenameUtils {

    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    private static final char EXTENSION_SEPARATOR = '.';
    private static final int NOT_FOUND = -1;

    /**
     * 私有构造方法
     */
    private FilenameUtils() {
        super();
    }

    /**
     * 正常化文件名
     *
     * @param filename 文件名
     * @return 文件名
     */
    @NonNull
    public static String normalize(@NonNull String filename) {
        return contact(filename);
    }

    /**
     * 拼接多个path生成文件名
     *
     * @param path path
     * @param more 子目录
     * @return 文件名
     */
    @NonNull
    public static String contact(@NonNull String path, String... more) {
        Asserts.notNull(path);

        if (more != null)
            return Paths.get(path, more).normalize().toString();
        else {
            return Paths.get(path).normalize().toString();
        }
    }

    /**
     * 获取文件BaseName
     * <pre>
     * a/b/c.txt --&gt; c
     * a.txt     --&gt; a
     * a/b/c     --&gt; c
     * a/b/c/    --&gt; ""
     * </pre>
     *
     * @param fileName 文件名
     * @return 文件basename
     */
    @NonNull
    public static String getBaseName(@NonNull final String fileName) {
        Asserts.notNull(fileName);

        requireNonNullChars(fileName);

        final int index = indexOfExtension(fileName);
        if (index == NOT_FOUND) {
            return fileName;
        }
        return fileName.substring(0, index);
    }

    /**
     * 获取文件的扩展名
     * <pre>
     * foo.txt      --&gt; "txt"
     * a/b/c.jpg    --&gt; "jpg"
     * a/b.txt/c    --&gt; ""
     * a/b/c        --&gt; ""
     * </pre>
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    @NonNull
    public static String getExtension(@NonNull final String fileName) {
        Asserts.notNull(fileName);

        final int index = indexOfExtension(fileName);
        if (index == NOT_FOUND) {
            return EMPTY;
        }
        return fileName.substring(index + 1);
    }

    /**
     * 查找扩展名的索引起点
     *
     * @param fileName 文件名
     * @return 结果或者-1
     */
    public static int indexOfExtension(@Nullable final String fileName) {
        if (fileName == null) {
            return NOT_FOUND;
        }

        final int extensionPos = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        final int lastSeparator = indexOfLastSeparator(fileName);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    private static int indexOfLastSeparator(final String fileName) {
        if (fileName == null) {
            return NOT_FOUND;
        }
        final int lastUnixPos = fileName.lastIndexOf(UNIX_SEPARATOR);
        final int lastWindowsPos = fileName.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    private static void requireNonNullChars(final String path) {
        if (path.indexOf(0) >= 0) {
            throw new IllegalArgumentException("Null byte present in file/path name. There are no "
                    + "known legitimate use cases for such data, but several injection attacks may use it");
        }
    }

}
