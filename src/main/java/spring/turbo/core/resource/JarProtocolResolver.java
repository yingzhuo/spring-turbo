package spring.turbo.core.resource;

/**
 * @author 应卓
 * @since 3.4.0
 */
final class JarProtocolResolver extends AbstractZipProtocolResolver {

    private static final String JAR_PREFIX = "jar:";

    public JarProtocolResolver() {
        super(JAR_PREFIX);
    }

}
