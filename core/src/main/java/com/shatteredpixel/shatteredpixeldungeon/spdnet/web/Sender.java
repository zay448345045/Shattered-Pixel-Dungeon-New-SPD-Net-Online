package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.getSocket;
import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Receiver.mapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Actions;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CAchievement;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CBackpack;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CChatMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CDeath;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CEnterDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CError;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CFloatingText;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CGiveItem;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CLeaveDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CPlayerMove;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions.CWin;

/**
 * 此类用于发送消息给服务器
 */
public class Sender {
	public static void sendAchievement(CAchievement achievement) {
		getSocket().emit(Actions.ACHIEVEMENT.getName(), mapper.convertValue(achievement, ObjectNode.class));
	}

	public static void sendBackpack(CBackpack backpack) {
		getSocket().emit(Actions.BACKPACK.getName(), mapper.convertValue(backpack, ObjectNode.class));
	}

	public static void sendChatMessage(CChatMessage message) {
		getSocket().emit(Actions.CHAT_MESSAGE.getName(), mapper.convertValue(message, ObjectNode.class));
	}

	public static void sendDeath(CDeath death) {
		getSocket().emit(Actions.DEATH.getName(), mapper.convertValue(death, ObjectNode.class));
	}

	public static void sendEnterDungeon(CEnterDungeon enterDungeon) {
		getSocket().emit(Actions.ENTER_DUNGEON.getName(), mapper.convertValue(enterDungeon, ObjectNode.class));
	}

	public static void sendError(CError message) {
		getSocket().emit(Actions.ERROR.getName(), mapper.convertValue(message, ObjectNode.class));
	}

	public static void sendGiveItem(CGiveItem giveItem) {
		getSocket().emit(Actions.GIVE_ITEM.getName(), mapper.convertValue(giveItem, ObjectNode.class));
	}

	public static void sendFloatingText(CFloatingText floatingText) {
		getSocket().emit(Actions.FLOATING_TEXT.getName(), mapper.convertValue(floatingText, ObjectNode.class));
	}

	public static void sendLeaveDungeon(CLeaveDungeon leaveDungeon) {
		getSocket().emit(Actions.LEAVE_DUNGEON.getName(), mapper.convertValue(leaveDungeon, ObjectNode.class));
	}

	public static void sendPlayerMove(CPlayerMove playerMove) {
		getSocket().emit(Actions.PLAYER_MOVE.getName(), mapper.convertValue(playerMove, ObjectNode.class));
	}

	public static void sendWin(CWin win) {
		getSocket().emit(Actions.WIN.getName(), mapper.convertValue(win, ObjectNode.class));
	}
}
