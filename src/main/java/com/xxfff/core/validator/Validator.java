package com.xxfff.core.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validator {
	public String filed();
	public int maxLength() default Integer.MAX_VALUE;
	public int minLength() default 0;
	public int maxValue() default Integer.MAX_VALUE;
	public int minValue() default Integer.MIN_VALUE;
	public String regexp();
	public String type();
}