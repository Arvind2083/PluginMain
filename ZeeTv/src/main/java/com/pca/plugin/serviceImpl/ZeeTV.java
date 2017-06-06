/**
 * 
 */
package com.pca.plugin.serviceImpl;

import com.pca.serviceWorker.Channel;

/**
 * This is the plugin class for ZeeTV channel.
 * 
 * @author Arvind
 *
 */
public class ZeeTV implements Channel {

	/**
	 * String for name of the channel.
	 */
	private static String ChannelName = "ZEE";

	/**
	 * This method is responsible for calculating the commercial price for the
	 * SBS channel. The calculate price method of the SBS channel multiple the
	 * occurence by 2
	 * 
	 * @param time
	 * @param occurence
	 * @return
	 */
	public int getPrice(Integer time, Integer occurence) {
		return time * occurence;
	}

	/**
	 * This method accept the name of the channel and return the object of
	 * current class.
	 * 
	 * @param channelName
	 * @return
	 */
	public ZeeTV getName(String channelName) {
		if (null != channelName && ChannelName.equalsIgnoreCase(channelName)) {
			return new ZeeTV();
		}
		return null;
	}

}
