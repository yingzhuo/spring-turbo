package spring.turbo.util;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URL;
import java.util.function.Predicate;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author 应卓
 * @see java.util.jar.Manifest
 * @since 3.4.2
 */
public final class ManifestUtils {

    /**
     * 私有构造方法
     */
    private ManifestUtils() {
    }

    @Nullable
    public static Manifest getManifest(Predicate<URL> predicate) {
        Assert.notNull(predicate, "predicate is required");

        try {
            var resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                URL url = resEnum.nextElement();
                if (predicate.test(url)) {
                    return new Manifest(url.openStream());
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

}
