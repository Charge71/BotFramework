package com.charge71.botframework.telegram;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class TelegramBotDispatcherTest {

	private static TelegramBotDispatcher dispatcher;

	@BeforeClass
	public static void init() {
		dispatcher = new TelegramBotDispatcher();
		dispatcher.setBotClasses(Arrays.asList(new String[] { "com.charge71.botframework.telegram.TelegramTestBot" }));
		dispatcher.init();
	}

	@Test
	public void testDefault() {
		String jsonString = "{\"update_id\":91280668,\"message\":{\"message_id\":4730,\"from\":{\"id\":267170958,\"first_name\":\"Sable\",\"username\":\"achisable\"},\"chat\":{\"id\":267170958,\"first_name\":\"Sable\",\"username\":\"achisable\",\"type\":\"private\"},\"date\":1471069208,\"text\":\"default\"}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("267170958", sendMessage.getChatId());
		Assert.assertEquals("default", sendMessage.getText());
	}

	@Test
	public void testPrefixText() {
		String jsonString = "{\"update_id\":91280668,\"message\":{\"message_id\":4730,\"from\":{\"id\":267170958,\"first_name\":\"Sable\",\"username\":\"achisable\"},\"chat\":{\"id\":267170958,\"first_name\":\"Sable\",\"username\":\"achisable\",\"type\":\"private\"},\"date\":1471069208,\"text\":\"prefix text\"}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("267170958", sendMessage.getChatId());
		Assert.assertEquals("prefix", sendMessage.getText());
	}

	@Test
	public void testFixedText() {
		String jsonString = "{\"update_id\":91280668,\"message\":{\"message_id\":4730,\"from\":{\"id\":267170958,\"first_name\":\"Sable\",\"username\":\"achisable\"},\"chat\":{\"id\":267170958,\"first_name\":\"Sable\",\"username\":\"achisable\",\"type\":\"private\"},\"date\":1471069208,\"text\":\"fixed\"}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("267170958", sendMessage.getChatId());
		Assert.assertEquals("fixed", sendMessage.getText());
	}

	@Test
	public void testCommand() {
		String jsonString = "{\"update_id\":872680355,\"message\":{\"message_id\":16990,\"from\":{\"id\":68549135,\"first_name\":\"ُmσѕَтαƒα\",\"username\":\"iMostafa97\"},\"chat\":{\"id\":68549135,\"first_name\":\"ُmσѕَтαƒα\",\"username\":\"iMostafa97\",\"type\":\"private\"},\"date\":1471194623,\"text\":\"/command\",\"entities\":[{\"type\":\"bot_command\",\"offset\":0,\"length\":8}]}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("68549135", sendMessage.getChatId());
		Assert.assertEquals("command", sendMessage.getText());
	}

	@Test
	public void testCommandPrefix() {
		String jsonString = "{\"update_id\":872680355,\"message\":{\"message_id\":16990,\"from\":{\"id\":68549135,\"first_name\":\"ُmσѕَтαƒα\",\"username\":\"iMostafa97\"},\"chat\":{\"id\":68549135,\"first_name\":\"ُmσѕَтαƒα\",\"username\":\"iMostafa97\",\"type\":\"private\"},\"date\":1471194623,\"text\":\"/prefix1234\",\"entities\":[{\"type\":\"bot_command\",\"offset\":0,\"length\":11}]}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("68549135", sendMessage.getChatId());
		Assert.assertEquals("commandPrefix", sendMessage.getText());
	}

	@Test
	public void testLocation() {
		String jsonString = "{\"update_id\":91280676,\"message\":{\"message_id\":4746,\"from\":{\"id\":141560936,\"first_name\":\"Francesco\",\"username\":\"Francesco0599\"},\"chat\":{\"id\":141560936,\"first_name\":\"Francesco\",\"username\":\"Francesco0599\",\"type\":\"private\"},\"date\":1471160268,\"reply_to_message\":{\"message_id\":4745,\"from\":{\"id\":204887014,\"first_name\":\"Meet Around Bot\",\"username\":\"MeetAroundBot\"},\"chat\":{\"id\":141560936,\"first_name\":\"Francesco\",\"username\":\"Francesco0599\",\"type\":\"private\"},\"date\":1471160257,\"text\":\"Welcome Francesco! To start send your location using the button below or the attachment function (paperclip icon). Please note that doing that you agree to share your first name and profile picture. Click /settings to change language or /help to learn more on how to gain points!\",\"entities\":[{\"type\":\"bot_command\",\"offset\":205,\"length\":9},{\"type\":\"bot_command\",\"offset\":237,\"length\":5}]},\"location\":{\"latitude\":40.059275,\"longitude\":18.441772}}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("141560936", sendMessage.getChatId());
		Assert.assertEquals("location", sendMessage.getText());
	}

	@Test
	public void testUrl() {
		String jsonString = "{\"update_id\":872680362,\"message\":{\"message_id\":17109,\"from\":{\"id\":29699835,\"first_name\":\"Salman\",\"username\":\"salmanrs\"},\"chat\":{\"id\":29699835,\"first_name\":\"Salman\",\"username\":\"salmanrs\",\"type\":\"private\"},\"date\":1471253340,\"text\":\"Varzesh3.com/rss\",\"entities\":[{\"type\":\"url\",\"offset\":0,\"length\":16}]}}";
		Update update = new Update(new JSONObject(jsonString));
		BotApiMethod<?> method = dispatcher.dispatch("test", update);

		Assert.assertTrue(method instanceof SendMessage);
		SendMessage sendMessage = (SendMessage) method;
		Assert.assertEquals("29699835", sendMessage.getChatId());
		Assert.assertEquals("url", sendMessage.getText());
	}

}
