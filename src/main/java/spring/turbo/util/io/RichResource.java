package spring.turbo.util.io;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import spring.turbo.util.collection.CollectionUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNullElse;
import static java.util.Objects.requireNonNullElseGet;
import static spring.turbo.util.StringUtils.blankSafeAddAll;
import static spring.turbo.util.collection.CollectionUtils.nullSafeAddAll;

/**
 * 有更丰富功能的 {@link Resource}
 *
 * @author 应卓
 * @see #builder()
 * @since 2.0.8
 */
public sealed interface RichResource extends Resource, Closeable
        permits RichResourceImpl {

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

    public default Reader getAsReader() throws IOException {
        return getAsReader(UTF_8);
    }

    public default Reader getAsReader(@Nullable Charset charset) throws IOException {
        return new InputStreamReader(getInputStream(), Objects.requireNonNullElse(charset, UTF_8));
    }

    public default String getAsText() throws IOException {
        return getAsText(null);
    }

    public default String getAsText(@Nullable Charset charset) throws IOException {
        return getContentAsString(Objects.requireNonNullElse(charset, UTF_8));
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

    public Resource getDelegating();

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@link RichResource}创建器
     *
     * @author 应卓
     * @since 2.0.8
     */
    final class Builder {

        public static final Predicate<Resource> DEFAULT_DISCRIMINATOR = r -> r != null && r.exists() && r.isReadable();
        private final List<String> locations = new ArrayList<>();
        private final List<Resource> resources = new ArrayList<>();
        private ResourceLoader resourceLoader = new DefaultResourceLoader();
        private Predicate<Resource> discriminator = DEFAULT_DISCRIMINATOR;
        private boolean testLocationsFirst = true;

        /**
         * 私有构造方法
         */
        private Builder() {
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
            for (var resource : resources) {
                if (resource == null) {
                    continue;
                }

                if (resource instanceof RichResource rich) {
                    list.add(rich.getDelegating());
                } else {
                    list.add(resource);
                }
            }
        }
    }

}
