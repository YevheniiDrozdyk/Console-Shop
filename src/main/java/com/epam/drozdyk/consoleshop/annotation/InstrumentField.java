package com.epam.drozdyk.consoleshop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates field of instrument with name of parameter.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 6 Apr 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InstrumentField {

    String name();
}
