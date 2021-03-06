package com.charge71.botframework.telegram.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method to be called when the bot update is not a command, url or
 * location and no {@link TelegramBotText} can be selected.
 * 
 * @author Diego Bardari
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TelegramBotDefault {

}
