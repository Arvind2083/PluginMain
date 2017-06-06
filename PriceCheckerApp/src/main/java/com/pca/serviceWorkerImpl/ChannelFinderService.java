package com.pca.serviceWorkerImpl;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import org.apache.log4j.Logger;

import com.pca.serviceWorker.Channel;
import com.pca.utility.UtilConstants;

/**
 * This is the service loader class to search and load implementation provider
 * plugin class.
 * 
 * @author Arvind
 *
 */
public class ChannelFinderService {

	private final static Logger LOGGER = Logger.getLogger(ChannelFinderService.class);
	/**
	 * Instance for the service loader.
	 */
	private ServiceLoader<Channel> loader;

	/**
	 * This is contructor for the ChannelFinderService.Loads all the jar files
	 * available definations @pluginLocation.
	 * 
	 * @throws MalformedURLException
	 */
	public ChannelFinderService() throws MalformedURLException {

		File location = new File(UtilConstants.pluginLocation);

		File[] fileList = location.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getPath().toLowerCase().endsWith(UtilConstants.extention);
			};
		});
		URL[] urls = new URL[fileList.length];
		for (int i = 0; i < fileList.length; i++)
			urls[i] = fileList[i].toURI().toURL();
		URLClassLoader ucl = new URLClassLoader(urls);

		this.loader = ServiceLoader.load(Channel.class, ucl);

	}

	/**
	 * Return the instance of ChannelFinderService.
	 * 
	 * @throws MalformedURLException
	 */
	public static synchronized ChannelFinderService getInstance() throws MalformedURLException {
		return new ChannelFinderService();
	}

	/**
	 * This method accept the name of the file and check that plugin for that
	 * particular implementaion exists in the loader.
	 * 
	 * @param channelName
	 * @param duration
	 * @param occurence
	 * @return
	 */
	public Channel getChannelImplementation(String channelName) {
		Channel subscriptionChannel = null;

		try {
			Iterator<Channel> channelList = loader.iterator();

			while (channelList.hasNext()) {
				Channel channel = channelList.next();

				/** check if this class is having the channel Name */
				if (channel != null) {
					subscriptionChannel = channel.getName(channelName);
				}

			}
		} catch (ServiceConfigurationError serviceError) {
			LOGGER.info("Error :" + serviceError.getMessage());

		}
		return subscriptionChannel;
	}
}
