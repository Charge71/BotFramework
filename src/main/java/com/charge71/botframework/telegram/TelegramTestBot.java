package com.charge71.botframework.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import com.charge71.botframework.telegram.annotations.TelegramBot;
import com.charge71.botframework.telegram.annotations.TelegramBotCommand;
import com.charge71.botframework.telegram.annotations.TelegramBotDefault;
import com.charge71.botframework.telegram.annotations.TelegramBotLocation;
import com.charge71.botframework.telegram.annotations.TelegramBotText;
import com.charge71.botframework.telegram.annotations.TelegramBotUrl;

@TelegramBot(token = "test")
public class TelegramTestBot {

	@TelegramBotDefault
	public SendMessage botDefault(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("default");
		return message;
	}

	@TelegramBotText("prefix")
	public SendMessage prefix(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("prefix");
		return message;
	}

	@TelegramBotText(value = "fixed", prefix = false)
	public SendMessage fixed(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("fixed");
		return message;
	}

	@TelegramBotCommand("/command")
	public SendMessage command(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("command");
		return message;
	}

	@TelegramBotCommand(value = "/prefix", prefix = true)
	public SendMessage commandPrefix(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("commandPrefix");
		return message;
	}

	@TelegramBotLocation
	public SendMessage location(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("location");
		return message;
	}

	@TelegramBotUrl
	public SendMessage url(Update update) {
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId().toString());
		message.setText("url");
		return message;
	}
}
