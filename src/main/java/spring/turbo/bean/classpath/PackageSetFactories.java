/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import spring.turbo.util.Asserts;
import spring.turbo.util.ClassUtils;

import java.lang.annotation.Annotation;

/**
 * @author 应卓
 * @see ImportBeanDefinitionRegistrar
 * @since 3.3.1
 */
public final class PackageSetFactories {

    /**
     * 私有构造方法
     */
    private PackageSetFactories() {
        super();
    }

    public static <A extends Annotation> PackageSet create(AnnotationMetadata importingClassMetadata, Class<A> importingAnnotationType) {
        Asserts.notNull(importingClassMetadata);
        Asserts.notNull(importingAnnotationType);

        var packageSet = PackageSet.newInstance();

        var attributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(importingAnnotationType.getName()));

        if (attributes != null) {
            packageSet.acceptPackages(attributes.getStringArray("value"));
            packageSet.acceptBaseClasses(attributes.getClassArray("basePackageClasses"));
        }

        if (packageSet.isEmpty()) {
            packageSet.acceptPackages(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }

        return packageSet;
    }

}
