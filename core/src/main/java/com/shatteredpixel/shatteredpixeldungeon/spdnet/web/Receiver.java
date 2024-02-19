package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.getSocket;

import com.alibaba.fastjson2.JSONObject;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Events;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Player;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.socket.emitter.Emitter;

/**
 * 此类用于接收并解析服务器发送的消息
 */
public class Receiver {

	public static void startAll() {
		Emitter.Listener onAchievement = args -> {
			String name = (String) args[0];
			String message = (String) args[1];
			boolean unique = (boolean) args[2];
			Handler.handleAchievement(name, message, unique);
		};
		Emitter.Listener onBackpack = args -> {
			String name = (String) args[0];
			Belongings belongings = (Belongings) args[1];
			Handler.handleBackpack(name, belongings);
		};
		Emitter.Listener onChatMessage = args -> {
			String name = (String) args[0];
			String message = (String) args[1];
			Handler.handleChatMessage(name, message);
		};
		Emitter.Listener onDeath = args -> {
			String name = (String) args[0];
			String cause = (String) args[1];
			Handler.handleDeath(name, cause);
		};
		Emitter.Listener onEnterDungeon = args -> {
			String name = (String) args[0];
			Status status = (Status) args[1];
			Handler.handleEnterDungeon(name, status);
		};
		Emitter.Listener onError = args -> {
			String message = (String) args[0];
			Handler.handleError(message);
		};
		Emitter.Listener onExit = args -> {
			String name = (String) args[0];
			Handler.handleExit(name);
		};
		Emitter.Listener onGiveItem = args -> {
			String name = (String) args[0];
			JSONObject item = (JSONObject) args[1];
			Handler.handleGiveItem(name, item);
		};
		Emitter.Listener onFloatingText = args -> {
			String name = (String) args[0];
			int type = (int) args[1];
			String text = (String) args[2];
			Handler.handleFloatingText(name, type, text);
		};
		Emitter.Listener onInit = args -> {
			String motd = (String) args[0];
			Map<String, String> seeds = (Map<String, String>) args[1];
			Handler.handleInit(motd, seeds);
		};
		Emitter.Listener onJoin = args -> {
			String name = (String) args[0];
			String power = (String) args[1];
			Handler.handleJoin(name, power);
		};
		Emitter.Listener onLeaveDungeon = args -> {
			String name = (String) args[0];
			Handler.handleLeaveDungeon(name);
		};
		Emitter.Listener onPlayerList = args -> {
			List<Player> players = (List<Player>) args[0];
			Handler.handlePlayerList(players);
		};
		Emitter.Listener onPlayerMove = args -> {
			String name = (String) args[0];
			int depth = (int) args[1];
			int pos = (int) args[2];
			Handler.handlePlayerMove(name, depth, pos);
		};
		Emitter.Listener onServerMessage = args -> {
			String message = (String) args[0];
			Handler.handleServerMessage(message);
		};
		Emitter.Listener onWin = args -> {
			String name = (String) args[0];
			int challenges = (int) args[1];
			Handler.handleWin(name, challenges);
		};
		getSocket().on(Events.ACHIEVEMENT.getName(), onAchievement);
		getSocket().on(Events.BACKPACK.getName(), onBackpack);
		getSocket().on(Events.CHAT_MESSAGE.getName(), onChatMessage);
		getSocket().on(Events.DEATH.getName(), onDeath);
		getSocket().on(Events.ENTER_DUNGEON.getName(), onEnterDungeon);
		getSocket().on(Events.ERROR.getName(), onError);
		getSocket().on(Events.EXIT.getName(), onExit);
		getSocket().on(Events.GIVE_ITEM.getName(), onGiveItem);
		getSocket().on(Events.FLOATING_TEXT.getName(), onFloatingText);
		getSocket().on(Events.INIT.getName(), onInit);
		getSocket().on(Events.JOIN.getName(), onJoin);
		getSocket().on(Events.LEAVE_DUNGEON.getName(), onLeaveDungeon);
		getSocket().on(Events.PLAYER_LIST.getName(), onPlayerList);
		getSocket().on(Events.PLAYER_MOVE.getName(), onPlayerMove);
		getSocket().on(Events.SERVER_MESSAGE.getName(), onServerMessage);
		getSocket().on(Events.WIN.getName(), onWin);
	}

	public static void cancelAll() {
		getSocket().off(Events.ACHIEVEMENT.getName());
		getSocket().off(Events.BACKPACK.getName());
		getSocket().off(Events.CHAT_MESSAGE.getName());
		getSocket().off(Events.DEATH.getName());
		getSocket().off(Events.ENTER_DUNGEON.getName());
		getSocket().off(Events.EXIT.getName());
		getSocket().off(Events.GIVE_ITEM.getName());
		getSocket().off(Events.FLOATING_TEXT.getName());
		getSocket().off(Events.INIT.getName());
		getSocket().off(Events.JOIN.getName());
		getSocket().off(Events.LEAVE_DUNGEON.getName());
		getSocket().off(Events.PLAYER_LIST.getName());
		getSocket().off(Events.PLAYER_MOVE.getName());
		getSocket().off(Events.SERVER_MESSAGE.getName());
		getSocket().off(Events.WIN.getName());
	}
}
