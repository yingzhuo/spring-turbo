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
 * @see PathTreeWalker
 * @since 1.0.7
 */
public final class PathUtils {

    /**
     * 私有构造方法
     */
    private PathUtils() {
        super();
    }

    public static Path createPath(String first, String... more) {
        Asserts.notNull(first);
        return Paths.get(first, more).normalize();
    }

    public static Path createFile(String first, String... more) {
        final Path path = createPath(first, more);
        createFile(path);
        return path;
    }

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

    public static Path createDirectory(String first, String... more) {
        Asserts.notNull(first);

        final Path path = PathUtils.createPath(first, more);
        createDirectory(path);
        return path;
    }

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

    public static boolean isExists(Path path) {
        Asserts.notNull(path);
        return Files.exists(path);
    }

    public static boolean isDirectory(Path path) {
        Asserts.notNull(path);
        return Files.isDirectory(path);
    }

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

    public static boolean isRegularFile(Path path) {
        Asserts.notNull(path);
        return Files.isRegularFile(path);
    }

    public static boolean isSymbolicLink(Path path) {
        Asserts.notNull(path);
        return Files.isSymbolicLink(path);
    }

    public static boolean isHidden(Path path) {
        Asserts.notNull(path);
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            return false;
        }
    }

    public static void delete(Path path) {
        Asserts.notNull(path);

        try {
            if (isRegularFile(path)) {
                Files.deleteIfExists(path);
            } else {
                FileSystemUtils.deleteRecursively(path);
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static void deleteQuietly(Path path) {
        Asserts.notNull(path);

        try {
            delete(path);
        } catch (Throwable e) {
            // nop
        }
    }

    public static void cleanDirectory(Path path) {
        if (!isDirectory(path)) {
            return;
        }

        PathTreeWalker.list(path, 1, PathPredicateFactories.alwaysTrue()).forEach(found -> {
            if (!found.equals(path)) {
                deleteQuietly(found);
            }
        });
    }

    public static Date getCreationTime(Path path) {
        Asserts.notNull(path);
        try {
            final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static Date getLastModifiedTime(Path path) {
        Asserts.notNull(path);
        try {
            final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return new Date(attributes.lastModifiedTime().to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static Date getLastAccessTime(Path path) {
        Asserts.notNull(path);
        try {
            final BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return new Date(attributes.lastAccessTime().to(TimeUnit.MILLISECONDS));
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static List<String> readLines(Path path) {
        return readLines(path, UTF_8);
    }

    public static List<String> readLines(Path path, Charset charset) {
        Asserts.notNull(path);
        Asserts.notNull(charset);

        try {
            return Files.readAllLines(path, charset);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static byte[] readBytes(Path path) {
        Asserts.notNull(path);

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

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

    public static void writeLines(Path path, List<String> lines, boolean createIfNotExists, boolean append) {
        writeLines(path, lines, UTF_8, createIfNotExists, append);
    }

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

    public static Path toAbsolutePath(Path path) {
        Asserts.notNull(path);
        return path.toAbsolutePath();
    }

    public static File toFile(Path path) {
        Asserts.notNull(path);
        return path.toFile();
    }

}
