/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 本类包含一系列静态方法创建TypeFilter的实例
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class TypeFilterFactories {

    /**
     * 私有构造方法
     */
    private TypeFilterFactories() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 通过元注释来过滤类型
     *
     * @param annotationType 元注释类型
     * @return TypeFilter的实例
     */
    public static TypeFilter annotation(Class<? extends Annotation> annotationType) {
        return annotation(annotationType, true, true);
    }

    /**
     * 通过元注释来过滤类型
     *
     * @param annotationType          元注释类型
     * @param considerMetaAnnotations 考虑Meta元注释的情形
     * @param considerInterfaces      考虑元注释在接口上而不仅仅在实现类上的情形
     * @return TypeFilter的实例
     */
    public static TypeFilter annotation(Class<? extends Annotation> annotationType, boolean considerMetaAnnotations, boolean considerInterfaces) {
        Assert.notNull(annotationType, "annotationType is null");
        return new AnnotationTypeFilter(annotationType, considerMetaAnnotations, considerInterfaces);
    }

    /**
     * 通过正则表达式匹配fully-qualified class name过滤类型
     *
     * @param regex 正则表达式
     * @return TypeFilter的实例
     */
    public static TypeFilter regex(String regex) {
        Assert.hasText(regex, "regex is null or blank");
        return new RegexPatternTypeFilter(Pattern.compile(regex));
    }

    /**
     * 过滤是接口的类型
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isInterface() {
        return (reader, readerFactory) -> reader.getClassMetadata().isInterface();
    }

    /**
     * 过滤抽象的类型。包含接口和抽象类。
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isAbstract() {
        return (reader, readerFactory) -> reader.getClassMetadata().isAbstract();
    }

    /**
     * 过滤具象的类型
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isConcrete() {
        return (reader, readerFactory) -> reader.getClassMetadata().isConcrete();
    }

    /**
     * 过滤是元数据的类型
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isAnnotation() {
        return (reader, readerFactory) -> reader.getClassMetadata().isAnnotation();
    }

    /**
     * 过滤是Final的类型
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isFinal() {
        return (reader, readerFactory) -> reader.getClassMetadata().isFinal();
    }

    /**
     * 过滤是顶级类型或静态内部类
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isIndependent() {
        return (reader, readerFactory) -> reader.getClassMetadata().isIndependent();
    }

    /**
     * 过滤有父类型的类型
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter hasSuperClass() {
        return (reader, readerFactory) -> reader.getClassMetadata().hasSuperClass();
    }

    /**
     * 过滤内部类的类型
     *
     * @return TypeFilter的实例
     */
    public static TypeFilter isInnerClass() {
        return (reader, readerFactory) -> reader.getClassMetadata().hasEnclosingClass();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 逻辑取反
     *
     * @param typeFilter 代理的TypeFilter实例
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter not(final TypeFilter typeFilter) {
        Assert.notNull(typeFilter, "typeFilter is null");
        return (reader, readerFactory) -> !typeFilter.match(reader, readerFactory);
    }

    /**
     * 被装饰的所有TypeFilter任意一个返回true，整体返回true，否则返回false
     *
     * @param filters 代理的TypeFilter实例
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter any(TypeFilter... filters) {
        return new Any(Arrays.asList(filters));
    }

    /**
     * 被装饰的所有TypeFilter任意一个返回false，整体返回false，否则返回true
     *
     * @param filters 代理的TypeFilter实例
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter all(TypeFilter... filters) {
        return new All(Arrays.asList(filters));
    }

    /**
     * 总是返回true的TypeFilter
     *
     * @return TypeFilter实例
     */
    public static TypeFilter alwaysTrue() {
        return (reader, readerFactory) -> true;
    }

    /**
     * 总是返回false的TypeFilter
     *
     * @return TypeFilter实例
     */
    public static TypeFilter alwaysFalse() {
        return (reader, readerFactory) -> false;
    }

    /**
     * 装饰TypeFilter并吞掉IOException
     *
     * @param filter        代理的TypeFilter实例
     * @param resultIfError 发生IOException时的返回值
     * @return TypeFilter实例
     */
    public static TypeFilter quiet(TypeFilter filter, boolean resultIfError) {
        Assert.notNull(filter, "filter is null");
        return new Quiet(filter, resultIfError);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static class All implements TypeFilter {

        private final List<TypeFilter> filters;

        public All(List<TypeFilter> filters) {
            this.filters = filters;
        }

        @Override
        public boolean match(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
            if (CollectionUtils.isEmpty(filters)) {
                return false;
            }
            for (TypeFilter filter : filters) {
                if (!filter.match(reader, readerFactory)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class Any implements TypeFilter {
        private final List<TypeFilter> filters;

        public Any(List<TypeFilter> filters) {
            this.filters = filters;
        }

        @Override
        public boolean match(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
            if (CollectionUtils.isEmpty(filters)) {
                return false;
            }
            for (TypeFilter filter : filters) {
                if (filter.match(reader, readerFactory)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class Quiet implements TypeFilter {
        private final TypeFilter typeFilter;
        private final boolean resultIfError;

        public Quiet(TypeFilter typeFilter, boolean resultIfError) {
            this.typeFilter = typeFilter;
            this.resultIfError = resultIfError;
        }

        @Override
        public boolean match(MetadataReader reader, MetadataReaderFactory readerFactory) {
            try {
                return typeFilter.match(reader, readerFactory);
            } catch (IOException e) {
                return resultIfError;
            }
        }
    }

}
