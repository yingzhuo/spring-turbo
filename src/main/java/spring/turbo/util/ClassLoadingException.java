/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import spring.turbo.util.reflection.InstantiationException;

/**
 * 类型加载异常
 *
 * @author 应卓
 * @see InstantiationException
 * @since 1.0.2
 */
public class ClassLoadingException extends IllegalStateException {

    /**
     * 构造方法
     */
    public ClassLoadingException() {
        super();
    }

    /**
     * 构造方法
     *
     * @param message 消息
     */
    public ClassLoadingException(String message) {
        super(message);
    }

}
