/**
 * 
 */
package com.pca.plugin.serviceImpl;

import com.pca.serviceWorker.Channel;

/**
 * This is the plugin class for Fox channel.
 * 
 * @author Arvind
 *
 */
public class Fox implements Channel {

	/**
	 * String for name of the channel.
	 */
	private static String ChannelName = "FOX";

	/**
	 * This method is responsible for calculating the commercial price for the
	 * Fox channel. The calculate price method of the Fox channel divide the
	 * occurence by 2
	 * 
	 * @param time
	 * @param occurence
	 * @return
	 */
	public int getPrice(Integer time, Integer occurence) {
		return time * (occurence / 2);
	}

	/**
	 * This method accept the name of the channel and return the object of
	 * current class.
	 * 
	 * @param channelName
	 * @return
	 */
	public Fox getName(String channelName) {
		if (null != channelName && ChannelName.equalsIgnoreCase(channelName)) {
			return new Fox();
		}
		return null;
	}

}
