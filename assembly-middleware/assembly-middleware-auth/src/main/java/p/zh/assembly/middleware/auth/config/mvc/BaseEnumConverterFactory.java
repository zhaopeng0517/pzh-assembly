/**
 * dataofx.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package p.zh.assembly.middleware.auth.config.mvc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;
import p.zh.assembly.middleware.common.em.standard.BaseEnum;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * <p>
 * mvc 枚举转换方式
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
public class BaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> result = converterMap.get(targetType);
        if (result == null) {
            result = new IntegerStrToEnum<>(targetType);
            converterMap.put(targetType, result);
        }
        return result;
    }

    static class IntegerStrToEnum<T extends BaseEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        public IntegerStrToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }


        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return BaseEnum.getEnum(enumType, Integer.valueOf(source.trim()));
        }
    }
}