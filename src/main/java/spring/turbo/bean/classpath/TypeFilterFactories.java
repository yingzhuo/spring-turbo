/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 本类包含一系列静态方法创建TypeFilter的实例
 *
 * @author 应卓
 * @see TypeFilter
 * @see ClassPathScanner
 * @see ClassPathScannerBuilder
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
    public static TypeFilter hasAnnotation(Class<? extends Annotation> annotationType) {
        return hasAnnotation(annotationType, true, true);
    }

    /**
     * 通过元注释来过滤类型
     *
     * @param annotationType          元注释类型
     * @param considerMetaAnnotations 考虑Meta元注释的情形
     * @param considerInterfaces      考虑元注释在接口上而不仅仅在实现类上的情形
     * @return TypeFilter的实例
     */
    public static TypeFilter hasAnnotation(Class<? extends Annotation> annotationType, boolean considerMetaAnnotations, boolean considerInterfaces) {
        Asserts.notNull(annotationType);
        return new AnnotationTypeFilter(annotationType, considerMetaAnnotations, considerInterfaces);
    }

    /**
     * 通过可赋值性过滤类型
     *
     * @param targetType 赋值目标类型
     * @return TypeFilter的实例
     */
    public static TypeFilter assignable(Class<?> targetType) {
        return new AssignableTypeFilter(targetType);
    }

    /**
     * 通过类名过滤类型
     *
     * @param className 指定的类型
     * @return TypeFilter的实例
     */
    public static TypeFilter fullyQualifiedNameEquals(String className) {
        return fullyQualifiedNameEquals(className, false);
    }

    /**
     * 通过类名过滤类型
     *
     * @param className  指定的类型
     * @param ignoreCase 是否忽略大小写
     * @return TypeFilter的实例
     */
    public static TypeFilter fullyQualifiedNameEquals(String className, boolean ignoreCase) {
        Asserts.hasText(className);
        if (ignoreCase) {
            return (reader, readerFactory) -> className.equalsIgnoreCase(reader.getClassMetadata().getClassName());
        } else {
            return (reader, readerFactory) -> className.equals(reader.getClassMetadata().getClassName());
        }
    }

    /**
     * 通过正则表达式匹配FQN过滤类型
     *
     * @param regex 正则表达式
     * @return TypeFilter的实例
     */
    public static TypeFilter fullyQualifiedNameMatch(String regex) {
        Asserts.hasText(regex);
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

    /**
     * 过滤实现了指定接口的类型
     *
     * @param interfaceType 指定接口
     * @return TypeFilter的实例
     */
    public static TypeFilter implementsInterface(final Class<?> interfaceType) {
        Asserts.notNull(interfaceType);
        return (reader, readerFactory) -> {
            final Set<String> set = Stream.of(reader.getClassMetadata().getInterfaceNames()).collect(Collectors.toSet());
            return set.contains(interfaceType.getName());
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 逻辑取反
     *
     * @param f 代理的TypeFilter实例
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter not(final TypeFilter f) {
        Asserts.notNull(f);
        return (reader, readerFactory) -> !f.match(reader, readerFactory);
    }

    /**
     * 逻辑或
     *
     * @param f1 实例1
     * @param f2 实例2
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter or(TypeFilter f1, TypeFilter f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return any(f1, f2);
    }

    /**
     * 逻辑与
     *
     * @param f1 实例1
     * @param f2 实例2
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter and(TypeFilter f1, TypeFilter f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return all(f1, f2);
    }

    /**
     * 被装饰的所有TypeFilter任意一个返回true，整体返回true，否则返回false
     *
     * @param filters 代理的TypeFilter实例
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter any(TypeFilter... filters) {
        Asserts.notNull(filters);
        Asserts.noNullElements(filters);
        return new Any(Arrays.asList(filters));
    }

    /**
     * 被装饰的所有TypeFilter任意一个返回false，整体返回false，否则返回true
     *
     * @param filters 代理的TypeFilter实例
     * @return 装饰后的TypeFilter实例
     */
    public static TypeFilter all(TypeFilter... filters) {
        Asserts.notNull(filters);
        Asserts.noNullElements(filters);
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

    // -----------------------------------------------------------------------------------------------------------------

    private static final class All implements TypeFilter {

        private final List<TypeFilter> list = new LinkedList<>();

        public All(List<TypeFilter> list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean match(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
            if (CollectionUtils.isEmpty(list)) {
                return false;
            }
            for (TypeFilter filter : list) {
                if (!filter.match(reader, readerFactory)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static final class Any implements TypeFilter {
        private final List<TypeFilter> list = new LinkedList<>();

        public Any(List<TypeFilter> list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean match(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
            if (CollectionUtils.isEmpty(list)) {
                return false;
            }
            for (TypeFilter filter : list) {
                if (filter.match(reader, readerFactory)) {
                    return true;
                }
            }
            return false;
        }
    }

}
