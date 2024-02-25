package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import static com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net.getSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Events;
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
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerList;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SPlayerMove;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SServerMessage;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.events.SWin;

import io.socket.emitter.Emitter;

/**
 * 此类用于接收并解析服务器发送的消息
 */
public class Receiver {
	public static final ObjectMapper mapper = new ObjectMapper();

	public static void startAll() {
		Emitter.Listener onAchievement = args -> {
			try {
				Handler.handleAchievement(mapper.readValue(args[0].toString(), SAchievement.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onAnkhUsed = args -> {
			try {
				Handler.handleAnkhUsed(mapper.readValue(args[0].toString(), SAnkhUsed.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onChatMessage = args -> {
			try {
				Handler.handleChatMessage(mapper.readValue(args[0].toString(), SChatMessage.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onDeath = args -> {
			try {
				Handler.handleDeath(mapper.readValue(args[0].toString(), SDeath.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onEnterDungeon = args -> {
			try {
				Handler.handleEnterDungeon(mapper.readValue(args[0].toString(), SEnterDungeon.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onError = args -> {
			try {
				Handler.handleError(mapper.readValue(args[0].toString(), SError.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onExit = args -> {
			try {
				Handler.handleExit(mapper.readValue(args[0].toString(), SExit.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onFloatingText = args -> {
			try {
				Handler.handleFloatingText(mapper.readValue(args[0].toString(), SFloatingText.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onGiveItem = args -> {
			try {
				Handler.handleGiveItem(mapper.readValue(args[0].toString(), SGiveItem.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};

		Emitter.Listener onHero = args -> {
			try {
				Handler.handleHero(mapper.readValue(args[0].toString(), SHero.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onInit = args -> {
			try {
				Handler.handleInit(mapper.readValue(args[0].toString(), SInit.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onJoin = args -> {
			try {
				Handler.handleJoin(mapper.readValue(args[0].toString(), SJoin.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onLeaveDungeon = args -> {
			try {
				Handler.handleLeaveDungeon(mapper.readValue(args[0].toString(), SLeaveDungeon.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onPlayerList = args -> {
			try {
				Handler.handlePlayerList(mapper.readValue(args[0].toString(), SPlayerList.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onPlayerMove = args -> {
			try {
				Handler.handlePlayerMove(mapper.readValue(args[0].toString(), SPlayerMove.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onServerMessage = args -> {
			try {
				Handler.handleServerMessage(mapper.readValue(args[0].toString(), SServerMessage.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		Emitter.Listener onWin = args -> {
			try {
				Handler.handleWin(mapper.readValue(args[0].toString(), SWin.class));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		getSocket().on(Events.ACHIEVEMENT.getName(), onAchievement);
		getSocket().on(Events.ANKH_USED.getName(), onAnkhUsed);
		getSocket().on(Events.CHAT_MESSAGE.getName(), onChatMessage);
		getSocket().on(Events.DEATH.getName(), onDeath);
		getSocket().on(Events.ENTER_DUNGEON.getName(), onEnterDungeon);
		getSocket().on(Events.ERROR.getName(), onError);
		getSocket().on(Events.EXIT.getName(), onExit);
		getSocket().on(Events.FLOATING_TEXT.getName(), onFloatingText);
		getSocket().on(Events.GIVE_ITEM.getName(), onGiveItem);
		getSocket().on(Events.HERO.getName(), onHero);
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
		getSocket().off(Events.ANKH_USED.getName());
		getSocket().off(Events.CHAT_MESSAGE.getName());
		getSocket().off(Events.DEATH.getName());
		getSocket().off(Events.ENTER_DUNGEON.getName());
		getSocket().off(Events.EXIT.getName());
		getSocket().off(Events.FLOATING_TEXT.getName());
		getSocket().off(Events.GIVE_ITEM.getName());
		getSocket().off(Events.HERO.getName());
		getSocket().off(Events.INIT.getName());
		getSocket().off(Events.JOIN.getName());
		getSocket().off(Events.LEAVE_DUNGEON.getName());
		getSocket().off(Events.PLAYER_LIST.getName());
		getSocket().off(Events.PLAYER_MOVE.getName());
		getSocket().off(Events.SERVER_MESSAGE.getName());
		getSocket().off(Events.WIN.getName());
	}
}
