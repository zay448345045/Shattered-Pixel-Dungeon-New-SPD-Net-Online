package com.shatteredpixel.shatteredpixeldungeon.spdnet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

public class SPDNetConfig {
	public static JsonNode config;
	// 配置获取地址
	private static final String CONFIG_GITEE_URL = "https://gitee.com/catandA/SPDNet-Data/raw/main/config.json";
	private static final String CONFIG_GITHUB_URL = "https://raw.githubusercontent.com/Not-Name-Dev-Team/SPDNet-Data/main/config.json";
	@Getter
	@Setter
	private static String key = "default";

	/**
	 * 从服务器更新配置文件
	 */
	public static void refreshConfig() {
		String json = null;
		try {
			json = HttpUtils.get(CONFIG_GITEE_URL);
		} catch (Exception e) {
			// Gitee获取失败,尝试GitHub
			try {
				json = HttpUtils.get(CONFIG_GITHUB_URL);
			} catch (Exception e1) {
				// GitHub获取失败
				// TODO 获取失败处理
			}
		}
		if (json == null) return;
		ObjectMapper mapper = new ObjectMapper();
		try {
			config = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
