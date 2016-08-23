package com.charge71.botframework.telegram.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method to be called when the bot update is a command that is equal or
 * starts with the annotation's value. If no annotation is found with the same
 * value, the method annotated with the empty value is called if it exists.
 * 
 * @author Diego Bardari
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TelegramBotCommand {

	/**
	 * The bot command related to this method. If empty the method is called
	 * when no related command is found.
	 * 
	 * @return the bot command related to this method
	 */
	String value() default "";

	/**
	 * If true the method is called when the command prefix matches the
	 * annotation value, otherwise the method is called for exact matches only.
	 * For example the method is called if the command is
	 * <code>/command1234</code> and the annotation value is
	 * <code>/command</code> with prefix <code>true</code>.
	 * 
	 * @return whether the method should be called if command prefix is matched
	 *         or not
	 */
	boolean prefix() default false;

}
