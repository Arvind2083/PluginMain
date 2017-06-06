package com.pca.service;

import java.net.MalformedURLException;
import java.util.List;

import org.pca.domain.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pca.dao.ServiceDao;
import com.pca.serviceWorker.Channel;
import com.pca.serviceWorkerImpl.ChannelFinderService;

/**
 * This is service layer for the PriceChecker Application.
 * 
 * @author Arvind
 *
 */
@Service
public class ChannelService {

	/**
	 * This is DI injection for the dao layer.
	 */
	@Autowired
	private ServiceDao serviceDao;

	/**
	 * This Arraylist is declared for persistance.
	 */
	// public static List<String> channelList = new ArrayList<>();

	/**
	 * This method return check if the implementation class of the channel Name
	 * exists. If yee, then it return the instance of that class with populated
	 * fields.
	 * 
	 * @param channelName
	 * @return
	 * @throws MalformedURLException
	 */
	public Channel getChannelDetailsByName(String channelName) throws MalformedURLException {

		Channel channel = ChannelFinderService.getInstance().getChannelImplementation(channelName);

		return channel;
	}

	/**
	 * This Method return list of all the plugin details exists in the
	 * application.
	 * 
	 * @return
	 */
	public List<Plugin> getAllPluginList() {

		return serviceDao.findAll();

	}

	/**
	 * This method is the persistance method for the plugin name in a single
	 * session
	 * 
	 * @param PluginName
	 */
	public void savePluginDetails(String PluginName) {

		Plugin plugin = new Plugin();
		plugin.setName(PluginName);
		serviceDao.add(plugin);

	}

	/**
	 * This method calculated the price of the channel.
	 * 
	 * @param channel
	 * @param duration
	 * @param occurence
	 * @return
	 */
	public int calculateTotalPrice(Channel channel, int duration, int occurence) {

		return channel.getPrice(duration, occurence);

	}

}
