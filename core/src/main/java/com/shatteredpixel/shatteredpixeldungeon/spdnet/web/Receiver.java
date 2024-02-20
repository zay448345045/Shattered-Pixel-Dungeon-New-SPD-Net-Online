package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.getSocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Events;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Player;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SAchievement;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SBackpack;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SChatMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SDeath;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SEnterDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SError;
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
			Handler.handleAchievement((SAchievement) args[0]);
		};
		Emitter.Listener onBackpack = args -> {
			Handler.handleBackpack((SBackpack) args[0]);
		};
		Emitter.Listener onChatMessage = args -> {
			Handler.handleChatMessage((SChatMessage) args[0]);
		};
		Emitter.Listener onDeath = args -> {
			Handler.handleDeath((SDeath) args[0]);
		};
		Emitter.Listener onEnterDungeon = args -> {
			Handler.handleEnterDungeon((SEnterDungeon) args[0]);
		};
		Emitter.Listener onError = args -> {
			Handler.handleError((SError) args[0]);
		};
		Emitter.Listener onExit = args -> {
			Handler.handleExit((SExit) args[0]);
		};
		Emitter.Listener onGiveItem = args -> {
			Handler.handleGiveItem((SGiveItem) args[0]);
		};
		Emitter.Listener onFloatingText = args -> {
			Handler.handleFloatingText((SFloatingText) args[0]);
		};
		Emitter.Listener onInit = args -> {
			Handler.handleInit((SInit) args[0]);
		};
		Emitter.Listener onJoin = args -> {
			Handler.handleJoin((SJoin) args[0]);
		};
		Emitter.Listener onLeaveDungeon = args -> {
			Handler.handleLeaveDungeon((SLeaveDungeon) args[0]);
		};
		Emitter.Listener onPlayerList = args -> {
			Handler.handlePlayerList((SPlayerList) args[0]);
		};
		Emitter.Listener onPlayerMove = args -> {
			Handler.handlePlayerMove((SPlayerMove) args[0]);
		};
		Emitter.Listener onServerMessage = args -> {
			Handler.handleServerMessage((SServerMessage) args[0]);
		};
		Emitter.Listener onWin = args -> {
			Handler.handleWin((SWin) args[0]);
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
