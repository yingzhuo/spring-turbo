package spring.turbo.databinding;

import org.springframework.validation.MessageCodesResolver;

/**
 * 简易{@link MessageCodesResolver} 实现。
 *
 * @author 应卓
 * @see #getInstance()
 * @since 3.3.1
 */
public final class DirectMessageCodesResolver implements MessageCodesResolver {

    /**
     * 私有构造方法
     */
    private DirectMessageCodesResolver() {
    }

    /**
     * 获取本类型单例
     *
     * @return 单例实例
     */
    public static DirectMessageCodesResolver getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] resolveMessageCodes(String errorCode, String objectName) {
        return new String[]{errorCode};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] resolveMessageCodes(String errorCode, String objectName, String field, Class<?> fieldType) {
        return new String[]{errorCode};
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final DirectMessageCodesResolver INSTANCE = new DirectMessageCodesResolver();
    }

}
