package com.shatteredpixel.shatteredpixeldungeon.spdnet;

import com.alibaba.fastjson2.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketManager {
	private static Socket socket;
	// 服务器地址配置Json
	private static JSONObject serverJson;
	// 服务器地址
	private static String serverUrl = "";
	// 认证状态
	public static boolean isAuthed = false;

	public static Socket getSocket() {
		if (serverUrl.isEmpty()) {
			refreshServerUrl();
		}
		if (socket == null) {
			try {
				IO.Options opts = new IO.Options();
				opts.reconnection = true;
				socket = IO.socket(serverUrl, opts);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
		return socket;
	}

	public static Socket getConnectedSocket() {
		Socket socket = getSocket();
		if (!socket.connected()) {
			socket.connect();
		}
		return socket;
	}

	public static void refreshServerUrl() {
		SPDNetConfig.refreshConfig();
		serverUrl = SPDNetConfig.config.getString("serverUrl");
	}
}