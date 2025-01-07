package spring.turbo.util.io;

import org.springframework.lang.Nullable;
import spring.turbo.util.collection.StreamFactories;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @see Manifest
 * @since 3.4.2
 */
public final class ManifestUtils {

    /**
     * 私有构造方法
     */
    private ManifestUtils() {
    }

    public static Manifest getManifest(@Nullable Predicate<URL> urlPredicate) {
        return getManifestStream(urlPredicate)
                .findFirst()
                .orElse(null);
    }

    public static Stream<Manifest> getManifestStream(@Nullable Predicate<URL> urlPredicate) {
        try {
            final var resources = ManifestUtils.class.getClassLoader()
                    .getResources(JarFile.MANIFEST_NAME);

            return StreamFactories.newSteam(resources, false)
                    .filter(Objects.requireNonNullElse(urlPredicate, url -> true))
                    .map(new URL2ManifestFunc())
                    .filter(Objects::nonNull);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static class URL2ManifestFunc implements Function<URL, Manifest> {
        @Nullable
        @Override
        public Manifest apply(URL url) {
            try {
                return new Manifest(url.openStream());
            } catch (IOException e) {
                return null;
            }
        }
    }

}
