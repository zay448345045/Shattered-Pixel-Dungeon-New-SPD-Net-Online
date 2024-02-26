package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Player;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.*;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.windows.NetWindow;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 此类用于处理服务器发送的消息
 */
public class Handler {
	public static void handleAchievement(SAchievement achievement) {
	}

	public static void handleAnkhUsed(SAnkhUsed ankhUsed) {
		// TODO 聊天显示其他玩家差点好似
	}

	public static void handleHero(SHero hero) {
		// TODO 显示其他玩家的详细信息
	}

	public static void handleChatMessage(SChatMessage chatMessage) {
	}

	public static void handleDeath(SDeath death) {
	}

	public static void handleEnterDungeon(SEnterDungeon enterDungeon) {
		Player player = Net.playerList.get(enterDungeon.getName());
		player.setStatus(enterDungeon.getStatus());
		Net.playerList.put(enterDungeon.getName(), player);
		// TODO 进入地牢消息
		// TODO 更新玩家列表
	}

	public static void handleError(SError error) {
		NetWindow.error("服务器说:" + error.getError());
	}

	public static void handleExit(SExit exit) {
	}

	public static void handleGiveItem(SGiveItem giveItem) {
	}

	public static void handleFloatingText(SFloatingText floatingText) {
	}

	public static void handleInit(SInit init) {
		Net.seeds = new ConcurrentHashMap<>(init.getSeeds());
		Net.name = init.getName();
		NetWindow.showMotd(init.getMotd());


		// TODO 等GUI实现之后来这里更改种子逻辑 目前默认使用服务器给与的第一个种子
		NetInProgress.seed = Net.seeds.get(Net.seeds.keySet().toArray()[0]);
	}

	public static void handleJoin(SJoin join) {
		Net.playerList.put(join.getName(), new Player(join.getName(), join.getPower(), new Status(-1, -1, -1, -1, -1, -1)));
		// TODO 上线提醒
	}

	public static void handleLeaveDungeon(SLeaveDungeon leaveDungeon) {
	}

	public static void handlePlayerList(SPlayerList playerList) {
	}

	public static void handlePlayerMove(SPlayerMove playerMove) {
	}

	public static void handleServerMessage(SServerMessage serverMessage) {
	}

	public static void handleWin(SWin win) {
	}
}
