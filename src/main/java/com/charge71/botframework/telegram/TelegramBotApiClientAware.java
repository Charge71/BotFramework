package com.charge71.botframework.telegram;

import com.charge71.botframework.telegram.annotations.TelegramBot;

/**
 * Interface to be implemented by any class annotated with {@link TelegramBot}
 * that wishes to use a {@link TelegramBotApiClient}.
 * 
 * @author Diego Bardari
 *
 */
public interface TelegramBotApiClientAware {

	/**
	 * Set the TelegramBotApiClient to be used by the bot.
	 * 
	 * @param telegramBotApiClient
	 *            the TelegramBotApiClient to be used by the bot
	 */
	public void setTelegramBotApiClient(TelegramBotApiClient telegramBotApiClient);

}
