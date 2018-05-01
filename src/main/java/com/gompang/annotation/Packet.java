package com.gompang.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Packet {

    public String name() default "no name";

    public String description() default "no description";


}