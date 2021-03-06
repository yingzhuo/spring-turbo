/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author 应卓
 * @since 1.1.1
 */
@Immutable
public class PathLocalFile implements LocalFile {

    private final Path path;

    public PathLocalFile(String first, String... more) {
        this.path = PathUtils.createPath(first, more)
                .normalize()
                .toAbsolutePath();
    }

    public PathLocalFile(Path path) {
        Asserts.notNull(path);
        this.path = path.normalize().toAbsolutePath();
    }

    public PathLocalFile(File file) {
        this(file.toPath());
    }

    @Override
    public Path asPath() {
        return path;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return getPathAsString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathLocalFile that = (PathLocalFile) o;
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

}
