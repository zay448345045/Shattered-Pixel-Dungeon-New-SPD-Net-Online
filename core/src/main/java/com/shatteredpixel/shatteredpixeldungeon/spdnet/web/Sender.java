package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.getSocket;

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
		getSocket().emit(Actions.ACHIEVEMENT.getName(), achievement);
	}

	public static void sendBackpack(CBackpack backpack) {
		getSocket().emit(Actions.BACKPACK.getName(), backpack);
	}

	public static void sendChatMessage(CChatMessage message) {
		getSocket().emit(Actions.CHAT_MESSAGE.getName(), message);
	}

	public static void sendDeath(CDeath death) {
		getSocket().emit(Actions.DEATH.getName(), death);
	}

	public static void sendEnterDungeon(CEnterDungeon enterDungeon) {
		getSocket().emit(Actions.ENTER_DUNGEON.getName(), enterDungeon);
	}

	public static void sendError(CError message) {
		getSocket().emit(Actions.ERROR.getName(), message);
	}

	public static void sendGiveItem(CGiveItem giveItem) {
		getSocket().emit(Actions.GIVE_ITEM.getName(), giveItem);
	}

	public static void sendFloatingText(CFloatingText floatingText) {
		getSocket().emit(Actions.FLOATING_TEXT.getName(), floatingText);
	}

	public static void sendLeaveDungeon(CLeaveDungeon leaveDungeon) {
		getSocket().emit(Actions.LEAVE_DUNGEON.getName(), leaveDungeon);
	}

	public static void sendPlayerMove(CPlayerMove playerMove) {
		getSocket().emit(Actions.PLAYER_MOVE.getName(), playerMove);
	}

	public static void sendWin(CWin win) {
		getSocket().emit(Actions.WIN.getName(), win);
	}
}
