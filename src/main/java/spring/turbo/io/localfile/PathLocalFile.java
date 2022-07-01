/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.localfile;

import spring.turbo.io.PathUtils;
import spring.turbo.lang.Immutable;

import java.nio.file.Path;

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

    @Override
    public Path asPath() {
        return path;
    }

    public Path getPath() {
        return path;
    }

}
