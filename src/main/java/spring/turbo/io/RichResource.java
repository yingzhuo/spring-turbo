/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import spring.turbo.util.CollectionUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNullElse;
import static java.util.Objects.requireNonNullElseGet;
import static spring.turbo.io.IOExceptionUtils.toUnchecked;
import static spring.turbo.util.CharsetPool.UTF_8;
import static spring.turbo.util.CollectionUtils.nullSafeAddAll;
import static spring.turbo.util.StringUtils.blankSafeAddAll;

/**
 * 有更丰富功能的 {@link Resource}
 *
 * @author 应卓
 * @see #builder()
 * @since 2.0.8
 */
public sealed interface RichResource extends Resource, Closeable permits RichResourceImpl {

    /**
     * 获得创建器实例
     *
     * @return 创建器实例
     */
    public static Builder builder() {
        return new Builder();
    }

    public default boolean isPhysicalResource() {
        try {
            getFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public default Path getPath() throws IOException {
        return getFile().toPath();
    }

    @Nullable
    public default Path getPathQuietly() {
        try {
            return getPath();
        } catch (IOException e) {
            return null;
        }
    }

    public default Path getRequiredPath() {
        try {
            return getPath();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Nullable
    public default InputStream getInputStreamQuietly() {
        try {
            return getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public default InputStream getRequiredInputStream() {
        try {
            return getInputStream();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Nullable
    public default File getFileQuietly() {
        try {
            return this.getFile();
        } catch (IOException e) {
            return null;
        }
    }

    public default File getRequiredFile() {
        try {
            return this.getFile();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    public default String getRequiredFilename() {
        var filename = getFilename();
        if (filename == null) {
            throw toUnchecked("this resource has no filename");
        }
        return filename;
    }

    public default Reader asReader() {
        return asReader(UTF_8);
    }

    public default Reader asReader(Charset charset) {
        try {
            return new FileReader(getRequiredFile(), charset);
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    public default String asText() {
        return asText(UTF_8);
    }

    public default String asText(Charset charset) {
        return IOUtils.copyToString(getRequiredInputStream(), charset);
    }

    public default LineIterator asLineIterator() {
        return asLineIterator(UTF_8);
    }

    public default LineIterator asLineIterator(Charset charset) {
        return new LineIterator(asReader(charset));
    }

    public default boolean isRegularFile() {
        try {
            return PathUtils.isRegularFile(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    public default boolean isDirectory() {
        try {
            return PathUtils.isDirectory(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    public default boolean isEmptyDirectory() {
        try {
            return PathUtils.isEmptyDirectory(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    public default boolean isSymbolicLink() {
        try {
            return PathUtils.isSymbolicLink(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    public default boolean isHidden() {
        try {
            return PathUtils.isHidden(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    public default Resource createRequiredRelative(String path) {
        try {
            return createRelative(path);
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@link RichResource}创建器
     *
     * @author 应卓
     * @since 2.0.8
     */
    final class Builder implements spring.turbo.bean.Builder<Optional<RichResource>> {

        private static final Predicate<Resource> DEFAULT_DISCRIMINATOR = r -> r != null && r.exists() && r.isReadable();
        private final List<String> locations = new ArrayList<>();
        private final List<Resource> resources = new ArrayList<>();
        private ResourceLoader resourceLoader = new DefaultResourceLoader();
        private Predicate<Resource> discriminator = DEFAULT_DISCRIMINATOR;
        private boolean testLocationsFirst = true;

        /**
         * 私有构造方法
         */
        private Builder() {
            super();
        }

        public Builder resourceLoader(@Nullable ResourceLoader resourceLoader) {
            this.resourceLoader = requireNonNullElseGet(resourceLoader, DefaultResourceLoader::new);
            return this;
        }

        public Builder discriminator(@Nullable Predicate<Resource> accept) {
            this.discriminator = requireNonNullElse(accept, DEFAULT_DISCRIMINATOR);
            return this;
        }

        public Builder testLocationsFirst() {
            this.testLocationsFirst = true;
            return this;
        }

        public Builder testResourcesFirst() {
            this.testLocationsFirst = false;
            return this;
        }

        public Builder addLocations(@Nullable String... locations) {
            if (locations != null) {
                for (var location : locations) {
                    if (location == null) {
                        throw new IllegalArgumentException("locations has null element(s)");
                    } else {
                        this.locations.add(location);
                    }
                }
            }
            return this;
        }

        public Builder addLocations(@Nullable Collection<String> locations) {
            if (locations != null) {
                for (var location : locations) {
                    if (location == null) {
                        throw new IllegalArgumentException("locations has null element(s)");
                    } else {
                        this.locations.add(location);
                    }
                }
            }
            return this;
        }

        public Builder blankSafeAddLocations(@Nullable String... locations) {
            blankSafeAddAll(this.locations, locations);
            return this;
        }

        public Builder blankSafeAddLocations(@Nullable Collection<String> locations) {
            blankSafeAddAll(this.locations, locations);
            return this;
        }

        public Builder addResources(@Nullable Resource... resources) {
            if (resources != null) {
                for (var resource : resources) {
                    if (resource == null) {
                        throw new IllegalArgumentException("resources has null element(s)");
                    } else {
                        this.resources.add(resource);
                    }
                }
            }
            return this;
        }

        public Builder addResources(@Nullable Collection<Resource> resources) {
            if (resources != null) {
                for (var resource : resources) {
                    if (resource == null) {
                        throw new IllegalArgumentException("resources has null element(s)");
                    } else {
                        this.resources.add(resource);
                    }
                }
            }
            return this;
        }

        public Builder nullSafeAddResources(@Nullable Resource... resources) {
            nullSafeAddAll(this.resources, resources);
            return this;
        }

        public Builder nullSafeAddResources(@Nullable Collection<Resource> resources) {
            nullSafeAddAll(this.resources, resources);
            return this;
        }

        @Override
        public Optional<RichResource> build() {

            if (CollectionUtils.isEmpty(resources) && CollectionUtils.isEmpty(locations)) {
                return Optional.empty();
            }

            var list = new ArrayList<Resource>();

            if (this.testLocationsFirst) {
                addAllLocations(list, this.locations);
                addAllResources(list, this.resources);
            } else {
                addAllResources(list, this.resources);
                addAllLocations(list, this.locations);
            }

            for (var resourceToTest : list) {
                if (discriminator.test(resourceToTest)) {
                    return Optional.of(new RichResourceImpl(resourceToTest));
                }
            }

            return Optional.empty();
        }

        private void addAllLocations(List<Resource> list, List<String> locations) {
            for (var location : locations) {
                try {
                    list.add(resourceLoader.getResource(location));
                } catch (Exception ignored) {
                    // nop
                }
            }
        }

        private void addAllResources(List<Resource> list, List<Resource> resources) {
            CollectionUtils.nullSafeAddAll(list, resources);
        }
    }

}
