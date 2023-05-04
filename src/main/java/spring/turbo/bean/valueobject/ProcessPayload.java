/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import spring.turbo.bean.Attributes;

/**
 * @author 应卓
 *
 * @since 1.0.0
 */
public final class ProcessPayload extends Attributes {

    private long successCount = 0L;
    private long invalidDataCount = 0L;
    private long errorCount = 0L;

    public ProcessPayload() {
        super();
    }

    public static ProcessPayload newInstance() {
        return new ProcessPayload();
    }

    public long getSuccessCount() {
        return successCount;
    }

    public long getInvalidDataCount() {
        return invalidDataCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void incrSuccessCount() {
        this.incrSuccessCount(1L);
    }

    public void incrSuccessCount(long n) {
        this.successCount += n;
    }

    public void incrInvalidDataCount() {
        this.incrInvalidDataCount(1L);
    }

    public void incrInvalidDataCount(long n) {
        this.invalidDataCount += n;
    }

    public void incrErrorCount() {
        this.incrErrorCount(1L);
    }

    public void incrErrorCount(long n) {
        this.errorCount += n;
    }

    public boolean hasInvalidData() {
        return this.invalidDataCount > 0;
    }

    public boolean hasError() {
        return this.errorCount > 0;
    }

    public boolean hasInvalidDataOrError() {
        return hasError() || hasInvalidData();
    }

}
