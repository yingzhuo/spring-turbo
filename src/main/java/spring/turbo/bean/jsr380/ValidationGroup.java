/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

/**
 * 验证组
 *
 * @author 应卓
 * @since 2.0.0
 */
public final class ValidationGroup {

    /**
     * 私有构造方法
     */
    private ValidationGroup() {
        super();
    }

    /**
     * 增
     */
    public interface Creating {
    }

    /**
     * 删
     */
    public interface Deleting {
    }

    /**
     * 查
     */
    public interface Reading {
    }

    /**
     * 改
     */
    public interface Updating {
    }

}
