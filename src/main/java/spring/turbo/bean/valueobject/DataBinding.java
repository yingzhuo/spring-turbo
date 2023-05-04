/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import spring.turbo.util.Asserts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author 应卓
 *
 * @see NamedArray
 * @see NamedArrayBuilder
 * @see MutablePropertyValues
 * @see Alias
 *
 * @since 1.0.0
 */
public final class DataBinding {

    @NonNull
    private final List<Validator> validators = new LinkedList<>();

    @Nullable
    private Object valueObject;

    @Nullable
    private MutablePropertyValues data;

    @Nullable
    private ConversionService conversionService;

    private DataBinding() {
        super();
    }

    public static DataBinding newInstance() {
        return new DataBinding();
    }

    public DataBinding valueObject(Object valueObject) {
        Asserts.notNull(valueObject);
        this.valueObject = valueObject;
        return this;
    }

    public DataBinding data(MutablePropertyValues data) {
        Asserts.notNull(data);
        this.data = data;
        return this;
    }

    public DataBinding data(NamedArray<?> data) {
        Asserts.notNull(data);
        this.data = data.toMutablePropertyValues();
        return this;
    }

    public DataBinding conversionService(ConversionService conversionService) {
        Asserts.notNull(conversionService);
        this.conversionService = conversionService;
        return this;
    }

    public DataBinding validator(Validator validator) {
        Asserts.notNull(validator);
        this.validators.clear();
        this.validators.add(validator);
        return this;
    }

    public DataBinding validators(Validator... validators) {
        Asserts.notNull(validators);
        Asserts.notEmpty(validators);
        Asserts.noNullElements(validators);
        this.validators.addAll(Arrays.asList(validators));
        return this;
    }

    public BindingResult bind() {
        Asserts.isTrue(valueObject != null);
        Asserts.isTrue(data != null);

        final DataBinder dataBinder = new DataBinder(valueObject);
        Optional.ofNullable(conversionService).ifPresent(dataBinder::setConversionService);
        dataBinder.addValidators(validators.toArray(new Validator[0]));

        dataBinder.bind(data);

        if (!validators.isEmpty()) {
            dataBinder.validate();
        }
        return dataBinder.getBindingResult();
    }

}
