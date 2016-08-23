package com.charge71.botframework.telegram;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updates.SetWebhook;

/**
 * Class implementing methods of the Telegram API.
 * 
 * @author Diego Bardari
 *
 */
public class TelegramBotApiClient {

	private static final String BASE_URL = "https://api.telegram.org/bot";

	private final String token;

	private RestTemplate restTemplate = new RestTemplate();

	TelegramBotApiClient(String token) {
		this.token = token;
	}

	/**
	 * Implementation of the setWebhook Telegram API. The certificate field is
	 * not implemented.
	 * 
	 * @param setWebhook
	 *            setWebhook API argument
	 * @return API call response
	 * @throws RestClientException
	 */
	public ResponseEntity<String> setWebhook(SetWebhook setWebhook) throws RestClientException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + token).path("/" + SetWebhook.PATH);
		if (setWebhook.getUrl() != null) {
			builder.queryParam(SetWebhook.URL_FIELD, setWebhook.getUrl());
		}
		return restTemplate.getForEntity(builder.build().encode().toUri(), String.class);
	}

	/**
	 * Implementation of the sendMessage Telegram API.
	 * 
	 * @param sendMessage
	 *            sendMessage API argument
	 * @return API call response
	 * @throws RestClientException
	 */
	public ResponseEntity<String> sendMessage(SendMessage sendMessage) throws RestClientException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + token).path("/" + SendMessage.PATH);
		if (sendMessage.getChatId() == null) {
			throw new IllegalArgumentException("SendMessage cannot have null chatId");
		}
		if (sendMessage.getText() == null) {
			throw new IllegalArgumentException("SendMessage cannot have null text");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(sendMessage.toJson().toString(), headers);
		return restTemplate.postForEntity(builder.build().encode().toUri(), entity, String.class);
	}
}
