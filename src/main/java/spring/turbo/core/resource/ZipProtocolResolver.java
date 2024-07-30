package spring.turbo.core.resource;

/**
 * @author 应卓
 * @since 3.4.0
 */
final class ZipProtocolResolver extends AbstractZipProtocolResolver {

    private static final String ZIP_PREFIX = "zip:";

    public ZipProtocolResolver() {
        super(ZIP_PREFIX);
    }

}
