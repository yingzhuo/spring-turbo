/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.function;

import java.util.function.Supplier;

/**
 * @author 应卓
 * @since 1.1.4
 */
@FunctionalInterface
public interface Supplier5<T, P1, P2, P3, P4, P5> {

    /**
     * 生成实例的方法
     *
     * @param p1 参数1
     * @param p2 参数2
     * @param p3 参数3
     * @param p4 参数4
     * @param p5 参数5
     * @return 目标对象
     */
    public T get(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);

    /**
     * 将带有参数的Supplier转换为无参{@link Supplier}
     *
     * @param p1 参数1
     * @param p2 参数2
     * @param p3 参数3
     * @param p4 参数4
     * @param p5 参数5
     * @return {@link Supplier}
     */
    public default Supplier<T> toSupplier(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        return () -> get(p1, p2, p3, p4, p5);
    }

}
