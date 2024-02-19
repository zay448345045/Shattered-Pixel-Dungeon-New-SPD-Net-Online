package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.getSocket;

import com.alibaba.fastjson2.JSONObject;
import com.shatteredpixel.shatteredpixeldungeon.Rankings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.scenes.RankingsScene;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Actions;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;

/**
 * 此类用于发送消息给服务器
 */
public class Sender {
	public static void sendAchievement(String achievement, boolean unique) {
		getSocket().emit(Actions.ACHIEVEMENT.getName(), achievement, unique);
	}

	public static void sendBackpack(Belongings belongings) {
		getSocket().emit(Actions.BACKPACK.getName(), belongings);
	}

	public static void sendChatMessage(String message) {
		getSocket().emit(Actions.CHAT_MESSAGE.getName(), message);
	}

	public static void sendDeath(String cause) {
		getSocket().emit(Actions.DEATH.getName(), cause);
	}

	public static void sendEnterDungeon(Status status) {
		getSocket().emit(Actions.ENTER_DUNGEON.getName(), status);
	}

	public static void sendEnterDungeon(int challenges, long seed, int depth, JSONObject hero) {
		Status status = new Status(challenges, seed, depth, hero);
		sendEnterDungeon(status);
	}

	public static void sendError(String message) {
		getSocket().emit(Actions.ERROR.getName(), message);
	}

	public static void sendGiveItem(JSONObject item) {
		getSocket().emit(Actions.GIVE_ITEM.getName(), item);
	}

	public static void sendFloatingText(int type, String text) {
		getSocket().emit(Actions.FLOATING_TEXT.getName(), type, text);
	}

	public static void sendLeaveDungeon() {
		getSocket().emit(Actions.LEAVE_DUNGEON.getName());
	}

	public static void sendPlayerMove(int depth, int pos) {
		getSocket().emit(Actions.PLAYER_MOVE.getName(), depth, pos);
	}

	public static void sendWin(Rankings.Record record) {
		getSocket().emit(Actions.WIN.getName(), record);
	}
}
