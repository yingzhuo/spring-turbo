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
import spring.turbo.util.Asserts;

import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Path;

/**
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

}
