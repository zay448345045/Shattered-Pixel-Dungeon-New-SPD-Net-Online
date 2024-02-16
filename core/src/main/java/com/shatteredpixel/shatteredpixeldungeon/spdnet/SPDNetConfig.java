package com.shatteredpixel.shatteredpixeldungeon.spdnet;

import com.alibaba.fastjson2.JSONObject;

public class SPDNetConfig {
	static JSONObject config;
	// 配置获取地址
	private static final String CONFIG_GITEE_URL = "";
	private static final String CONFIG_GITHUB_URL = "";

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
		config = JSONObject.parseObject(json);
	}
}
