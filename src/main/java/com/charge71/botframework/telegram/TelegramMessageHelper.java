package com.charge71.botframework.telegram;

import org.telegram.telegrambots.api.objects.EntityType;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.MessageEntity;

/**
 * Class containing static helper methods for Telegram.
 * 
 * @author Diego Bardari
 *
 */
public class TelegramMessageHelper {

	/**
	 * Return <code>true</code> if the given message contains an url entity,
	 * <code>false</code> otherwise.
	 * 
	 * @param message
	 *            the message to check
	 * @return <code>true</code> if the given message contains an url entity,
	 *         <code>false</code> otherwise
	 */
	public static boolean hasUrl(Message message) {
		if (message.hasText() && message.getEntities() != null) {
			for (MessageEntity entity : message.getEntities()) {
				if (entity != null && entity.getOffset() == 0 && EntityType.URL.equals(entity.getType())) {
					return true;
				}
			}
		}
		return false;
	}

}
