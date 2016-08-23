package com.charge71.botframework.interfaces;

/**
 * Interface for classes that dispatch messages to bots.
 * 
 * @author Diego Bardari
 *
 * @param <T> type of the message to dispatch
 * @param <V> type of the object returned
 */
public interface BotDispatcher<T, V> {
	
	/**
	 * Initialization method.
	 */
	public void init();
	
	/**
	 * Method called to dispatch messages to the bot with the given id.
	 * 
	 * @param botId the id of the bot to dispatch the message to
	 * @param message the message to dispatch
	 * @return a response object or <code>null</code>
	 */
	public V dispatch(String botId, T message);

}
