package com.charge71.botframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

import com.charge71.botframework.interfaces.BotDispatcher;

/**
 * Central controller class that receives updates from bot platforms and
 * forwards them to the appropriate <code>BotDispatcher</code>.
 * 
 * @author Diego Bardari
 *
 */
@RestController
public class BotController {

	@Autowired
	private BotDispatcher<Update, BotApiMethod<?>> telegramBotDispatcher;

	/**
	 * Method that receives updates from the Telegram bot platform and forwards
	 * them to Telegram specific <code>BotDispatcher</code>. The method endpoint
	 * is <code>/telegram/{token}</code> and it accepts POST requests containing
	 * a Telegram update in JSON format.
	 * 
	 * @param token
	 *            The target bot token
	 * @param update
	 *            The update to process
	 * @return a JSON format request to be performed to the Telegram Bot API or
	 *         a void response
	 */
	@RequestMapping(value = "/telegram/{token}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> telegramWebhook(@PathVariable("token") String token, @RequestBody Update update) {
		BotApiMethod<?> response = telegramBotDispatcher.dispatch(token, update);
		if (response != null) {
			return new ResponseEntity<BotApiMethod<?>>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	/**
	 * Get the Telegram specific <code>BotDispatcher</code>.
	 * 
	 * @return the Telegram specific <code>BotDispatcher</code>
	 */
	public BotDispatcher<Update, BotApiMethod<?>> getTelegramBotDispatcher() {
		return telegramBotDispatcher;
	}

	/**
	 * Set the Telegram specific <code>BotDispatcher</code>.
	 * 
	 * @param telegramBotDispatcher
	 *            the Telegram specific <code>BotDispatcher</code> for this
	 *            controller
	 */
	public void setTelegramBotDispatcher(BotDispatcher<Update, BotApiMethod<?>> telegramBotDispatcher) {
		this.telegramBotDispatcher = telegramBotDispatcher;
	}

}
