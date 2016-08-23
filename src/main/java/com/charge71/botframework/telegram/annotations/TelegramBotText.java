package com.charge71.botframework.telegram.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method to be called when the bot update is not a command, url or
 * location and contains a text that is equal or starts with the annotation's
 * value.
 * 
 * @author Diego Bardari
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TelegramBotText {

	/**
	 * The text related to this method.
	 * 
	 * @return the text related to this method
	 */
	String value();

	/**
	 * If true the method is called when the text starts with the annotation
	 * value, otherwise the method is called for exact matches only.
	 * 
	 * @return whether the method should be called if the text starts with the
	 *         value or not
	 */
	boolean prefix() default true;

}
