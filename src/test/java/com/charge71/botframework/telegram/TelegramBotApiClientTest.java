package com.charge71.botframework.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public class TelegramBotApiClientTest {

	public static void main(String[] args) {

		TelegramBotApiClient client = new TelegramBotApiClient("236804872:AAHa_Z0fdO_9CedIsBqfwEabwPJK5Lq1bow");
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId("148883640");
		sendMessage.setText("Test");
		System.out.println(client.sendMessage(sendMessage));
	}

}
