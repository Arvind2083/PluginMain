package com.pca.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pca.domain.Plugin;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pca.service.ChannelService;
import com.pca.serviceWorker.Channel;

public class PriceCheckAppControllerTest {

	private MockMvc mvc;

	@Mock
	private ChannelService channelService;

	@Mock
	private Channel channel;

	@InjectMocks
	private PriceCheckAppController priceCheckAppController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(priceCheckAppController).build();
	}

	@Test
	public final void testShowAllPluginsDetails() throws Exception {

		List<Plugin> list = new ArrayList<Plugin>();
		list.add(new Plugin());
		list.add(new Plugin());

		when(channelService.getAllPluginList()).thenReturn((List) list);

		mvc.perform(get("/displayPluginDetails")).andExpect(status().isOk()).andReturn().getResponse().equals(list);
	}
}
