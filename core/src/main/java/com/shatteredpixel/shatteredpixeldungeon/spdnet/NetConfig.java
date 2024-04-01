package com.shatteredpixel.shatteredpixeldungeon.spdnet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

public class NetConfig {
	public static JsonNode config;
	// 配置获取地址
	private static final String CONFIG_GITEE_URL = "https://gitee.com/catandA/SPDNet-Data/raw/main/config.json";
	private static final String CONFIG_GITHUB_URL = "https://raw.githubusercontent.com/Not-Name-Dev-Team/SPDNet-Data/main/config.json";

	public static void refreshConfig() {
		refreshConfig(new Net.HttpResponseListener() {
			@Override
			public void handleHttpResponse(Net.HttpResponse httpResponse) {
			}

			@Override
			public void failed(Throwable t) {
			}

			@Override
			public void cancelled() {
			}
		});
	}

	/**
	 * 从服务器更新配置文件
	 */
	public static void refreshConfig(final Net.HttpResponseListener externalListener) {
		final String[] json = new String[1];
		Net.HttpResponseListener listener1 = new Net.HttpResponseListener() {
			@Override
			public void handleHttpResponse(Net.HttpResponse httpResponse) {
				json[0] = httpResponse.getResultAsString();
				ObjectMapper mapper = new ObjectMapper();
				try {
					config = mapper.readTree(json[0]);
					com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.setServerUrl(NetConfig.config.get("serverUrl").asText());
					com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.destroySocket();
					externalListener.handleHttpResponse(httpResponse);
				} catch (JsonProcessingException ignored) {
				}
			}

			@Override
			public void failed(Throwable t) {
			}

			@Override
			public void cancelled() {
			}
		};
		// 优先使用github
		getHttpStringFromUrl(CONFIG_GITHUB_URL, listener1);
		if (json[0] == null) {
			getHttpStringFromUrl(CONFIG_GITEE_URL, listener1);
		}
		if (json[0] == null) {
			externalListener.cancelled();
		}
	}

	public static void getHttpStringFromUrl(String url, Net.HttpResponseListener listener) {
		Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
		request.setUrl(url);
		Gdx.net.sendHttpRequest(request, listener);
	}
}
