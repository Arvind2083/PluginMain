package com.pca.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException ;
import java.util.List;

import org.apache.log4j.Logger;
import org.pca.domain.ChannelInfo;
import org.pca.domain.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pca.service.ChannelService;
import com.pca.serviceWorker.Channel;
import com.pca.utility.UtilConstants;
import com.pca.utility.Utility;

@RestController
public class PriceCheckAppController {

	private final static Logger LOGGER = Logger.getLogger(PriceCheckAppController.class);

	/**
	 * This is DI injection for the service layer.
	 */
	@Autowired
	private ChannelService channelService;

	/**
	 * This is main method for displaying the prices for the commercial
	 * channels.
	 * 
	 * @param channelName
	 * @param duration
	 * @param occurence
	 * @return
	 * @throws MalformedURLException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/displayPrices")
	public @ResponseBody ChannelInfo getChannelDetails(@RequestParam("channelName") String channelName,
			@RequestParam("duration") int duration, @RequestParam("occurence") int occurence)
			throws MalformedURLException {

		LOGGER.info("Search Price with details for" + "channelName" + channelName + "Duration :" + duration
				+ "And Occurence :" + occurence);

		Channel channel = channelService.getChannelDetailsByName(channelName);

		ChannelInfo channelInfo = new ChannelInfo();
		if (channel != null) {
			channelInfo.setMessage("Total price required : " + channel.getPrice(duration, occurence));
			return channelInfo;
		} else {
			LOGGER.info(channelName + "Channel doesn't exists. Please add the plugin");
			channelInfo.setMessage(channelName + "Plugin doesn't exists. Please add the plugin");
			return channelInfo;
		}
	}

	/**
	 * This method will return list of all the plugins available in the
	 * application. Please note this demo application is not connected with the
	 * database so method will show only the instances added in that particular
	 * session.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/displayPluginDetails")
	public @ResponseBody List<Plugin> showAllPluginsDetails() {

		List<Plugin> list = channelService.getAllPluginList();

		return list;
	}

	/**
	 * This is the admin method for uploading the plugin in the system.
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public @ResponseBody String uploadPlugin(@RequestParam("file") MultipartFile file,
			@RequestParam("pluginName") String pluginName) throws IOException {

		InputStream uploadedInputStream = file.getInputStream();
		String uploadedFileLocation = UtilConstants.pluginLocation + file.getOriginalFilename();

		Utility.writeToFile(uploadedInputStream, uploadedFileLocation);

		/** Persist the plugin details */
		channelService.savePluginDetails(pluginName);

		return "Plugin for file pluginName is uploaded successfully ";
	}

}
