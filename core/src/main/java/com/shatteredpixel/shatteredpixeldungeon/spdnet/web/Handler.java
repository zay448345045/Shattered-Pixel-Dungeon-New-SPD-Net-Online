package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SAchievement;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SAnkhUsed;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SChatMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SDeath;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SEnterDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SError;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SHero;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SExit;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SFloatingText;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SGiveItem;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SInit;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SJoin;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SLeaveDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerList;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerMove;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SServerMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SWin;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.windows.NetWindow;

/**
 * 此类用于处理服务器发送的消息
 */
public class Handler {
	public static void handleAchievement(SAchievement achievement) {
	}

	public static void handleAnkhUsed(SAnkhUsed ankhUsed) {
	}

	public static void handleHero(SHero hero) {
	}

	public static void handleChatMessage(SChatMessage chatMessage) {
	}

	public static void handleDeath(SDeath death) {
	}

	public static void handleEnterDungeon(SEnterDungeon enterDungeon) {
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
	}

	public static void handleJoin(SJoin join) {
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
