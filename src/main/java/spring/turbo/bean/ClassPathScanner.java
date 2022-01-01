/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.NonNull;

import java.util.List;

/**
 * ClassPath扫描器
 *
 * @author 应卓
 * @since 1.0.0
 */
@FunctionalInterface
public interface ClassPathScanner {

    /**
     * 新建创建器
     *
     * @return 创建器
     */
    @NonNull
    public static ClassPathScannerBuilder builder() {
        return new ClassPathScannerBuilder();
    }

    /**
     * 扫描类路径
     *
     * @param basePackages 扫描起点
     * @return 扫描结果
     */
    @NonNull
    public List<ClassDefinition> scan(@NonNull Iterable<String> basePackages);

}
