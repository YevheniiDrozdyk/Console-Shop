package com.epam.drozdyk.consoleshop.builder.impl.reflect;

import com.epam.drozdyk.consoleshop.annotation.InstrumentField;
import com.epam.drozdyk.consoleshop.builder.InstrumentBuilder;
import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.exception.GeneratingException;
import com.epam.drozdyk.consoleshop.exception.ReflectionException;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.model.Instrument;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Builds instrument with help of java reflection.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 6 Apr 2017
 */
public class ReflectInstrumentBuilder implements InstrumentBuilder {
    private InstrumentGenerator generator;
    private Class<? extends Instrument> instrumentClass;

    public ReflectInstrumentBuilder(InstrumentGenerator generator, Class<? extends Instrument> clazz) {
        this.generator = generator;
        this.instrumentClass = clazz;
    }

    @Override
    public Instrument buildInstrument() {
        Class<?> clazz = getInstrumentClass();
        List<Field> fields = getAnnotatedFields(clazz);
        Instrument instrument = newInstance(clazz);
        for (Field field : fields) {
            String fieldName = field.getAnnotation(InstrumentField.class).name();
            Object fieldValue = generateValue(field.getType(), fieldName);
            String setterName = getSetterName(fieldName);
            Method method = getSetter(clazz, setterName, field.getType());
            invokeRealMethod(method, instrument, fieldValue);
        }

        return instrument;
    }

    private Class<? extends Instrument> getInstrumentClass() {
        return instrumentClass;
    }

    private List<Field> getAnnotatedFields(Class<?> clazz) {
        List<Field> annotated = new ArrayList<>();
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != Object.class) {
            annotated.addAll(getAnnotatedFields(superclass));
        }
        Field[] fields = clazz.getDeclaredFields();
        annotated.addAll(Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(InstrumentField.class))
                .collect(Collectors.toList()));

        return annotated;
    }

    private Instrument newInstance(Class<?> clazz) {
        try {
            return (Instrument) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ReflectionException(Message.ERROR_REFLECTION, e);
        }
    }

    private Object generateValue(Class<?> fieldType, String fieldName) {
        if (fieldType == long.class) {
            return generator.generateInt(fieldName);
        }
        if (fieldType == int.class) {
            return generator.generateInt(fieldName);
        }
        if (fieldType == String.class) {
            return generator.generateString(fieldName);
        }
        if (fieldType == GuitarType.class) {
            return generator.generateGuitarType(fieldName);
        }
        if (fieldType == ViolinCategory.class) {
            return generator.generateViolinCategory(fieldName);
        }

        throw new GeneratingException(Message.ERROR_NO_SUCH_TYPE);
    }

    private String getSetterName(String fieldName) {
        StringBuilder sb = new StringBuilder("set");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));

        return sb.toString();
    }

    private Method getSetter(Class<?> clazz, String setterName, Class<?> fieldType) {
        try {
            return clazz.getMethod(setterName, fieldType);
        } catch (NoSuchMethodException e) {
            throw new ReflectionException(Message.ERROR_REFLECTION, e);
        }
    }

    private void invokeRealMethod(Method method, Instrument instrument, Object fieldValue) {
        try {
            method.invoke(instrument, fieldValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException(Message.ERROR_REFLECTION, e);
        }
    }
}
