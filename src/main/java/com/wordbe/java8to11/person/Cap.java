package com.wordbe.java8to11.person;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(CapContainer.class)
public @interface Cap {
//    String value();
}
