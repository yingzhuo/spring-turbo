package spring.turbo.util.concurrent;

/**
 * 不检查线程被中断异常
 *
 * @author 应卓
 * @since 3.3.5
 */
public final class UncheckedInterruptedException extends RuntimeException {

    /**
     * 构造方法
     *
     * @param cause 要检查线程被中断方法
     */
    public UncheckedInterruptedException(Throwable cause) {
        super(cause);
    }

}
