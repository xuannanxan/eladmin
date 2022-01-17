package com.eladmin.annotation;


import java.lang.annotation.*;

//@java.lang.annotation.Target(value={java.lang.annotation.ElementType.PARAMETER,java.lang.annotation.ElementType.METHOD,java.lang.annotation.ElementType.FIELD})
//@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FieldDescribe {
    abstract String value();
}