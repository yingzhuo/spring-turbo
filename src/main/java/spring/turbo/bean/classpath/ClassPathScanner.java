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
public interface ClassPathScanner {

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
    public List<ClassDefinition> scan(@Nullable PackageSet packageSet);

}
