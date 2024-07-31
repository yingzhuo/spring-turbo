package spring.turbo.core.resource;

/**
 * @author 应卓
 * @since 3.4.0
 */
final class JarProtocolResolver extends AbstractZipProtocolResolver {

    public JarProtocolResolver() {
        super("jar:");
    }

}
