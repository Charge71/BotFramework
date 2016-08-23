package com.charge71.botframework.telegram.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.charge71.botframework.telegram.TelegramBotDispatcher;

/**
 * Marks a class to be a Telegram bot to which updates are dispatched by a
 * {@link TelegramBotDispatcher} adding them to the dispatcher's classes list.
 * 
 * @author Diego Bardari
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TelegramBot {

	/**
	 * The bot's token, also used as the bot's identifier.
	 * 
	 * @return the bot's token
	 */
	String token();

	/**
	 * The base url of the bot's webhook. The full webhook url is
	 * <code>&lt;baseurl>/telegram/&lt;token></code>. If this value is set the
	 * <code>setWebhook</code> API method is called when the bot is initialized.
	 * 
	 * @return the bot's base url
	 */
	String baseUrl() default "";

}
