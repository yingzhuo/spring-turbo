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
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class DataBinding {

    private final List<Validator> validators = new LinkedList<>();
    private Object valueObject;
    private MutablePropertyValues data;
    private ConversionService conversionService;

    private DataBinding() {
        super();
    }

    public static DataBinding newInstance() {
        return new DataBinding();
    }

    public DataBinding valueObject(Object valueObject) {
        Assert.notNull(valueObject, "valueObject is null");
        this.valueObject = valueObject;
        return this;
    }

    public DataBinding data(MutablePropertyValues data) {
        Assert.notNull(data, "data is null");
        this.data = data;
        return this;
    }

    public DataBinding data(NamedArray<?> data) {
        Assert.notNull(data, "data is null");
        this.data = data.toMutablePropertyValues();
        return this;
    }

    public DataBinding conversionService(ConversionService conversionService) {
        Assert.notNull(conversionService, "conversionService is null");
        this.conversionService = conversionService;
        return this;
    }

    public DataBinding validator(Validator validator) {
        Assert.notNull(validator, "validator is null");
        this.validators.clear();
        this.validators.add(validator);
        return this;
    }

    public DataBinding validators(Validator... validators) {
        Assert.notNull(validators, "validator is null or has null element(s)");
        Assert.noNullElements(validators, "validator is null or has null element(s)");
        this.validators.addAll(Arrays.asList(validators));
        return this;
    }

    public BindingResult bind() {
        init();

        final DataBinder dataBinder = new DataBinder(valueObject);
        Optional.ofNullable(conversionService).ifPresent(dataBinder::setConversionService);
        dataBinder.addValidators(validators.toArray(new Validator[0]));

        dataBinder.bind(data);

        if (!validators.isEmpty()) {
            dataBinder.validate();
        }
        return dataBinder.getBindingResult();
    }

    private void init() {
        Assert.state(valueObject != null, "valueObject not set");
        Assert.state(data != null, "data not set");
    }

}
