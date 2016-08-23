package com.charge71.botframework.telegram;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.api.objects.EntityType;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.MessageEntity;
import org.telegram.telegrambots.api.objects.Update;

import com.charge71.botframework.interfaces.BotDispatcher;
import com.charge71.botframework.telegram.annotations.TelegramBot;

/**
 * Telegram specific implementation of {@link BotDispatcher} which dispatches
 * updates to configured bots annotated with {@link TelegramBot}.
 * 
 * @author Diego Bardari
 *
 */
public class TelegramBotDispatcher implements BotDispatcher<Update, BotApiMethod<?>> {

	Logger logger = Logger.getLogger(TelegramBotDispatcher.class);

	private Map<String, TelegramBotDescriptor> bots = new HashMap<>();

	private List<String> botClasses;

	public BotApiMethod<?> dispatch(String botId, Update update) {
		if (!update.hasMessage()) {
			return null;
		}
		TelegramBotDescriptor tbd = bots.get(botId);
		if (tbd == null) {
			return null;
		}
		Method method = null;
		Message message = update.getMessage();
		if (message.isCommand()) {
			for (MessageEntity entity : message.getEntities()) {
				if (entity != null && entity.getOffset() == 0 && EntityType.BOTCOMMAND.equals(entity.getType())) {
					String command = entity.getText();
					method = tbd.getCommandMethod(command);
					if (method == null) {
						for (String prefix : tbd.getPrefixCommandMethods().keySet()) {
							if (command.startsWith(prefix)) {
								method = tbd.getPrefixCommandMethods().get(prefix);
								break;
							}
						}
					}
					if (method == null) {
						method = tbd.getCommandMethod("");
					}
					break;
				}
			}
		} else if (message.hasLocation()) {
			method = tbd.getLocationMethod();
		} else if (TelegramMessageHelper.hasUrl(message)) {
			method = tbd.getUrlMethod();
		} else if (message.hasText()) {
			String text = message.getText();
			method = tbd.getTextMethod(text);
			if (method == null) {
				for (String prefix : tbd.getPrefixTextMethods().keySet()) {
					if (text.startsWith(prefix)) {
						method = tbd.getPrefixTextMethods().get(prefix);
						break;
					}
				}
			}
		}
		if (method == null) {
			method = tbd.getDefaultMethod();
		}
		if (method != null) {
			try {
				return (BotApiMethod<?>) method.invoke(tbd.getBot(), update);
			} catch (Exception e) {
				logger.error(botId + " method " + method.getName() + " invoke error", e);
			}
		}
		return null;
	}

	public void init() {
		for (String botClass : botClasses) {
			try {
				TelegramBotDescriptor tbd = TelegramBotDescriptor.get(botClass);
				if (tbd.isTelegramBotApiClientAware()) {
					TelegramBotApiClient client = new TelegramBotApiClient(tbd.getBotToken());
					((TelegramBotApiClientAware) tbd.getBot()).setTelegramBotApiClient(client);
					if (tbd.getBotBaseUrl() != null) {
						SetWebhook setWebhook = new SetWebhook();
						setWebhook.setUrl(tbd.getBotBaseUrl() + "/telegram/" + tbd.getBotToken());
						client.setWebhook(setWebhook);
					}
				}
				bots.put(tbd.getBotToken(), tbd);
			} catch (Exception e) {
				logger.error("Error init bot " + botClass, e);
			}
		}
	}

	//

	/**
	 * Return the list of the full qualified names of the configured bot classes
	 * managed by this dispatcher.
	 * 
	 * @return the list of the full qualified names of the configured bot
	 *         classes
	 */
	public List<String> getBotClasses() {
		return botClasses;
	}

	/**
	 * Set the list of the full qualified names of the configured bot classes to
	 * be managed by this dispatcher.
	 * 
	 * @param botClasses
	 *            the list of the full qualified names of the configured bot
	 *            classes to be managed by this dispatcher
	 */
	public void setBotClasses(List<String> botClasses) {
		this.botClasses = botClasses;
	}

}