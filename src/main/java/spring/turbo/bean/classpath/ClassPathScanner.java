/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import spring.turbo.util.Asserts;

import java.util.Arrays;
import java.util.List;

/**
 * ClassPath扫描器
 *
 * @author 应卓
 * @see #builder()
 * @see ClassPathScannerBuilder
 * @since 1.0.0
 */
public sealed interface ClassPathScanner permits DefaultClassPathScanner, NullClassPathScanner {

    /**
     * 新建创建器
     *
     * @return 创建器
     */
    public static ClassPathScannerBuilder builder() {
        return new ClassPathScannerBuilder();
    }

    /**
     * 扫描类路径
     *
     * @param basePackages 扫描起点
     * @return 扫描结果
     */
    public List<ClassDef> scan(Iterable<String> basePackages);

    /**
     * 扫描类路径
     *
     * @param basePackages 扫描起点
     * @return 扫描结果
     */
    public default List<ClassDef> scan(String... basePackages) {
        Asserts.notNull(basePackages);
        Asserts.noNullElements(basePackages);
        return scan(Arrays.asList(basePackages));
    }

}
