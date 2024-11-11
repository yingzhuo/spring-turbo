package spring.turbo.util.concurrent;

/**
 * 不检查线程被中断异常
 *
 * @author 应卓
 * @since 3.4.0
 */
public final class UncheckedInterruptedException extends RuntimeException {

    public UncheckedInterruptedException() {
        super();
    }

    public UncheckedInterruptedException(Throwable cause) {
        super(cause);
    }

    public UncheckedInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }

}
