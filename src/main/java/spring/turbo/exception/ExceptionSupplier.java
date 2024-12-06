package spring.turbo.exception;

import java.util.function.Supplier;

/**
 * 异常提供器 (已检查异常)
 *
 * @author 应卓
 * @see ExceptionSupplier
 * @since 3.4.0
 */
@FunctionalInterface
public interface ExceptionSupplier extends Supplier<Exception> {
}
