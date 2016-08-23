package com.charge71.botframework.telegram;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.charge71.botframework.telegram.annotations.TelegramBot;
import com.charge71.botframework.telegram.annotations.TelegramBotCommand;
import com.charge71.botframework.telegram.annotations.TelegramBotDefault;
import com.charge71.botframework.telegram.annotations.TelegramBotLocation;
import com.charge71.botframework.telegram.annotations.TelegramBotText;
import com.charge71.botframework.telegram.annotations.TelegramBotUrl;

/**
 * Internal bot description built using annotations.
 * 
 * @author Diego Bardari
 *
 */
public class TelegramBotDescriptor {

	private String token;
	private String baseUrl;
	private Map<String, Method> commands = new HashMap<>();
	private Map<String, Method> prefixCommands = new HashMap<>();
	private Map<String, Method> texts = new HashMap<>();
	private Map<String, Method> prefixTexts = new HashMap<>();
	private Method location;
	private Method url;
	private Method botDefault;
	private Object bot;
	private boolean telegramBotApiClientAware;

	static TelegramBotDescriptor get(String className) throws Exception {
		Class<?> botClass = Class.forName(className);
		TelegramBot bot = botClass.getAnnotation(TelegramBot.class);
		if (bot == null) {
			return null;
		}
		TelegramBotDescriptor descriptor = new TelegramBotDescriptor();
		descriptor.bot = botClass.newInstance();
		descriptor.token = bot.token();
		descriptor.baseUrl = bot.baseUrl();
		descriptor.telegramBotApiClientAware = TelegramBotApiClientAware.class.isAssignableFrom(botClass);
		Method[] methods = botClass.getDeclaredMethods();
		for (Method method : methods) {
			TelegramBotCommand telegramBotCommand = method.getAnnotation(TelegramBotCommand.class);
			if (telegramBotCommand != null) {
				if (telegramBotCommand.prefix()) {
					descriptor.prefixCommands.put(telegramBotCommand.value(), method);
				} else {
					descriptor.commands.put(telegramBotCommand.value(), method);
				}
				continue;
			}
			TelegramBotDefault telegramBotDefault = method.getAnnotation(TelegramBotDefault.class);
			if (telegramBotDefault != null) {
				descriptor.botDefault = method;
				continue;
			}
			TelegramBotLocation telegramBotLocation = method.getAnnotation(TelegramBotLocation.class);
			if (telegramBotLocation != null) {
				descriptor.location = method;
				continue;
			}
			TelegramBotText telegramBotText = method.getAnnotation(TelegramBotText.class);
			if (telegramBotText != null) {
				if (telegramBotText.prefix()) {
					descriptor.prefixTexts.put(telegramBotText.value(), method);
				} else {
					descriptor.texts.put(telegramBotText.value(), method);
				}
				continue;
			}
			TelegramBotUrl telegramBotUrl = method.getAnnotation(TelegramBotUrl.class);
			if (telegramBotUrl != null) {
				descriptor.url = method;
				continue;
			}
		}
		return descriptor;
	}

	/**
	 * Return the bot's token.
	 * 
	 * @return the bot's token
	 */
	public String getBotToken() {
		return token;
	}

	/**
	 * Return the method related to a location update or <code>null</code> if
	 * none is configured.
	 * 
	 * @return the method related to a location update or <code>null</code> if
	 *         none is configured
	 */
	public Method getLocationMethod() {
		return location;
	}

	/**
	 * Return the method related to an url update or <code>null</code> if none
	 * is configured.
	 * 
	 * @return the method related to an url update or <code>null</code> if none
	 *         is configured
	 */
	public Method getUrlMethod() {
		return url;
	}

	/**
	 * Return the method related to an update not related to any other method or
	 * <code>null</code> if none is configured.
	 * 
	 * @return the method related to an update not related to any other method
	 *         or <code>null</code> if none is configured.
	 */
	public Method getDefaultMethod() {
		return botDefault;
	}

	/**
	 * Return the method related to the given command or <code>null</code> if
	 * none is configured.
	 * 
	 * @param command
	 *            the update's command
	 * @return the method related to the given command or <code>null</code> if
	 *         none is configured
	 */
	public Method getCommandMethod(String command) {
		return commands.get(command);
	}

	/**
	 * Return a map of all the configured prefix commands with the related
	 * methods.
	 * 
	 * @return a map of all the configured prefix commands with the related
	 *         methods
	 */
	public Map<String, Method> getPrefixCommandMethods() {
		return prefixCommands;
	}

	/**
	 * Return the method related to the given text or <code>null</code> if none
	 * is configured.
	 * 
	 * @param text
	 *            the update's text
	 * @return the method related to the given text or <code>null</code> if none
	 *         is configured
	 */
	public Method getTextMethod(String text) {
		return texts.get(text);
	}

	/**
	 * Return a map of all the configured prefix texts with the related methods.
	 * 
	 * @return a map of all the configured prefix texts with the related methods
	 */
	public Map<String, Method> getPrefixTextMethods() {
		return prefixTexts;
	}

	/**
	 * Return an instance of the bot.
	 * 
	 * @return an instance of the bot
	 */
	public Object getBot() {
		return bot;
	}

	/**
	 * Return <code>true</code> if the bot implements
	 * {@link TelegramBotApiClientAware}, <code>false</code> otherwise.
	 * 
	 * @return whether the bot implements <code>TelegramBotApiClientAware</code>
	 *         or not
	 */
	public boolean isTelegramBotApiClientAware() {
		return telegramBotApiClientAware;
	}

	/**
	 * Return the bot's base url or <code>null</code> if not configured.
	 * 
	 * @return the bot's base url or <code>null</code> if not configured
	 */
	public String getBotBaseUrl() {
		return baseUrl;
	}
}
