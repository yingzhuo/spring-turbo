package spring.turbo.exception;

import java.util.function.Supplier;

@FunctionalInterface
public interface RuntimeExceptionSupplier extends Supplier<RuntimeException> {
}
