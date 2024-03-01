package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.NetInProgress;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.actors.NetHero;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Player;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CRequestPlayerList;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SAchievement;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SAnkhUsed;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SChatMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SDeath;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SEnterDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SError;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SExit;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SFloatingText;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SGiveItem;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SHero;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SInit;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SJoin;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SLeaveDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerChangeFloor;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerList;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerMove;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SServerMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SWin;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.windows.NetWindow;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 此类用于处理服务器发送的消息
 */
public class Handler {
	public static void handleAchievement(SAchievement achievement) {
	}

	public static void handleAnkhUsed(SAnkhUsed ankhUsed) {
		// TODO 调用NetHero.useAnkh()
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
		if (!enterDungeon.getName().equals(Net.name)) {
			Player player = Net.playerList.get(enterDungeon.getName());
			if (player == null) {
				syncPlayerList();
				return;
			}
			player.setStatus(enterDungeon.getStatus());
			Net.playerList.put(enterDungeon.getName(), player);
			NetHero.addPlayer(player);
			// TODO 进入地牢消息
		}
	}

	public static void handleError(SError error) {
		NetWindow.error("服务器错误:" + error.getError());
	}

	public static void handleExit(SExit exit) {
		Net.playerList.remove(exit.getName());
		// TODO 下线提醒
	}

	public static void handleGiveItem(SGiveItem giveItem) {
	}

	public static void handleFloatingText(SFloatingText floatingText) {
		if (!floatingText.getName().equals(Net.name)) {
			NetHero player = NetHero.getPlayer(floatingText.getName());
			if (player != null) {
				player.sprite.showStatusWithIcon(floatingText.getColor(), floatingText.getText(), floatingText.getIcon());
			}
		}
	}

	public static void handleInit(SInit init) {
		Net.seeds = new ConcurrentHashMap<>(init.getSeeds());
		Net.name = init.getName();
		NetWindow.showMotd(init.getMotd());


		// TODO 等GUI实现之后来这里更改种子逻辑 目前默认使用服务器给与的第一个种子
		NetInProgress.seed = Net.seeds.get(Net.seeds.keySet().toArray()[0]);
	}

	public static void handleJoin(SJoin join) {
		if (!join.getName().equals(Net.name)) {
			Net.playerList.put(join.getName(), new Player(join.getQq(), join.getName(), join.getPower(), null));
			// TODO 上线提醒
		}
	}

	public static void handleLeaveDungeon(SLeaveDungeon leaveDungeon) {
		if (!leaveDungeon.getName().equals(Net.name)) {
			Player player = Net.playerList.get(leaveDungeon.getName());
			if (player != null) {
				player.setStatus(null);
				Net.playerList.put(leaveDungeon.getName(), player);
				NetHero.syncWithCurrentLevel();
			}
		}
	}

	public static void handlePlayerChangeFloor(SPlayerChangeFloor playerChangeFloor) {
	}

	public static void handlePlayerList(SPlayerList playerList) {
		Net.playerList.clear();
		for (Player player : playerList.getPlayers()) {
			Net.playerList.put(player.getName(), player);
		}
	}

	public static void handlePlayerMove(SPlayerMove playerMove) {
		if (!playerMove.getName().equals(Net.name)) {
			Player player = Net.playerList.get(playerMove.getName());
			Status status = player.getStatus();
			status.setPos(playerMove.getPos());
			player.setStatus(status);
			Net.playerList.put(playerMove.getName(), player);
			// 如果这位玩家在当前地牢楼层
			NetHero player1 = NetHero.getPlayer(playerMove.getName());
			if (player1 != null) {
				player1.move(playerMove.getPos(), false);
			}

		}
	}

	public static void handleServerMessage(SServerMessage serverMessage) {
		NetWindow.message(serverMessage.getMessage());
	}

	public static void handleWin(SWin win) {
	}

	/**
	 * 同步玩家列表
	 * 如果出现任何列表不同步的情况, 请调用此方法
	 */
	public static void syncPlayerList() {
		Sender.sendRequestPlayerList(new CRequestPlayerList());
		NetHero.syncWithCurrentLevel();
	}
}
