/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.lang.Nullable;

import java.util.List;

/**
 * ClassPath扫描器
 *
 * @author 应卓
 * @see #builder()
 * @see org.springframework.beans.factory.config.BeanDefinition
 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 * @see org.springframework.core.type.filter.TypeFilter
 * @since 1.0.0
 */
public sealed interface ClassPathScanner permits DefaultClassPathScanner, NullClassPathScanner {

    /**
     * 新建创建器
     *
     * @return 创建器实例
     */
    public static ClassPathScannerBuilder builder() {
        return new ClassPathScannerBuilder();
    }

    /**
     * 扫描类路径
     *
     * @param packageSet 扫描起点 (多个)
     * @return 扫描结果
     * @see PackageSet
     */
    public List<ClassDef> scan(@Nullable PackageSet packageSet);

}
