package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import com.shatteredpixel.shatteredpixeldungeon.spdnet.SPDNetConfig;
import com.watabou.noosa.Game;
import com.watabou.utils.DeviceCompat;

import java.net.URISyntaxException;
import java.util.Collections;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.Getter;

/**
 * 此类用于处理网络连接
 */
public class Net {
	static private Socket socket;
	// 服务器地址
	@Getter
	private static String serverUrl = "";

	/**
	 * 获取一个socketIO对象
	 *
	 * @return socketIO对象
	 */
	public static Socket getSocket() {
		if (serverUrl.isEmpty()) {
			refreshServerUrl();
		}
		if (socket == null) {
			try {
				IO.Options opts = new IO.Options();
				opts.reconnection = true;
				opts.auth = Collections.singletonMap("token", SPDNetConfig.getKey());
				opts.query = "SPDVersion=" + Game.version + "&NetVersion=" + Game.netVersion;
				socket = IO.socket(serverUrl, opts);
				setupEvents();
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
		return socket;
	}

	public static void connect() {
		Receiver.startAll();
		getSocket().connect();
	}

	public static void disConnect() {
		Receiver.cancelAll();
		getSocket().disconnect();
	}

	/**
	 * 获取一个已经连接的socketIO对象，如果当前没有连接，那么就尝试连接
	 *
	 * @return 连接的socketIO对象
	 */
	public static Socket getConnectedSocket() {
		if (!isConnected()) {
			connect();
		}
		return getSocket();
	}

	/**
	 * 切换socket连接状态
	 */
	public static void toggleSocket() {
		if (!isConnected() && !getSocket().io().isReconnecting()) {
			connect();
		} else {
			disConnect();
		}
	}

	/**
	 * 完全销毁所有socket相关的对象，重置连接
	 */
	public static void destroySocket() {
		if (isConnected()) {
			disConnect();
		}
		socket.off();
		socket = null;
	}

	/**
	 * 设置连接相关的事件
	 */
	public static void setupEvents() {
		Emitter.Listener onConnected = args -> {
		};
		Emitter.Listener onDisconnected = args -> {
		};
		Emitter.Listener onConnectionError = args -> {
		};
		socket.on(Socket.EVENT_CONNECT, onConnected);
		socket.on(Socket.EVENT_DISCONNECT, onDisconnected);
		socket.on(Socket.EVENT_CONNECT_ERROR, onConnectionError);
	}

	/**
	 * 刷新服务器地址
	 */
	public static void refreshServerUrl() {
		if (DeviceCompat.isDebug()){
			serverUrl = "http://127.0.0.1:21687";
		}else {
			SPDNetConfig.refreshConfig();
			serverUrl = SPDNetConfig.config.get("serverUrl").asText();
		}
	}

	public static boolean isConnected() {
		return getSocket().connected();
	}
}