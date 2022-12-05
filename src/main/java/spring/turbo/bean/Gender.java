/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.lang.Immutable;

/**
 * 性别
 *
 * @author 应卓
 * @since 1.1.0
 */
@Immutable
public enum Gender {

    /**
     * 女
     */
    FEMALE(0),

    /**
     * 男
     */
    MALE(1);

    private final int integerValue;

    /**
     * 构造方法
     *
     * @param intValue 整形值
     */
    Gender(int intValue) {
        this.integerValue = intValue;
    }

    public int getIntegerValue() {
        return integerValue;
    }

}
