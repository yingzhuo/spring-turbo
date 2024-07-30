package spring.turbo.core.resource;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.zip.ZipFile;

/**
 * @author 应卓
 * @see ZipProtocolResolver
 * @since 3.4.0
 */
final class JarProtocolResolver implements ProtocolResolver {

    private static final String JAR_PREFIX = "jar:";

    @Nullable
    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (StringUtils.startsWithIgnoreCase(location, JAR_PREFIX)) {

            var parts = StringUtils.delimitedListToStringArray(location.substring(JAR_PREFIX.length()), "!");
            if (parts.length == 2) {
                var zipLocation = parts[0];
                var entryName = parts[1];

                try {
                    return doResolve(zipLocation, entryName, resourceLoader);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }

        return null;
    }

    private Resource doResolve(String zipLocation, String entryName, ResourceLoader resourceLoader) throws IOException {
        var zipResource = resourceLoader.getResource(zipLocation);
        try (var zipFile = new ZipFile(zipResource.getFile())) {
            var zipEntry = zipFile.getEntry(entryName);
            return new ByteArrayResource(StreamUtils.copyToByteArray(zipFile.getInputStream(zipEntry)));
        }
    }

}
