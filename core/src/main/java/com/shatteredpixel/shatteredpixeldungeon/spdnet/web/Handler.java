package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import com.alibaba.fastjson2.JSONObject;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Player;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;

import java.util.List;
import java.util.Map;

/**
 * 此类用于处理服务器发送的消息
 */
public class Handler {
	public static void handleAchievement(String name, String message, boolean unique) {
	}

	public static void handleBackpack(String name, Belongings belongings) {
	}

	public static void handleChatMessage(String name, String message) {
	}

	public static void handleDeath(String name, String cause) {
	}

	public static void handleEnterDungeon(String name, Status status) {
	}

	public static void handleExit(String name) {
	}

	public static void handleGiveItem(String name, JSONObject item) {
	}

	public static void handleFloatingText(String name, int type, String text) {
	}

	public static void handleInit(String motd, Map<String, String> seeds) {
	}

	public static void handleJoin(String name, String power) {
	}

	public static void handleLeaveDungeon(String name) {
	}

	public static void handlePlayerList(List<Player> players) {
	}

	public static void handlePlayerMove(String name, int depth, int pos) {
	}

	public static void handleServerMessage(String message) {
	}

	public static void handleWin(String name, int challenges) {
	}
}
