package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure;

/**
 * 消息接收类型
 */
public enum Events {
	ACHIEVEMENT("achievement"),
	BACKPACK("backpack"),
	CHAT_MESSAGE("chatMessage"),
	DEATH("death"),
	ENTER_DUNGEON("enterDungeon"),
	EXIT("exit"),
	FLOATING_TEXT("floatingText"),
	GIVE_ITEM("giveItem"),
	INIT("init"),
	JOIN("join"),
	LEAVE_DUNGEON("leaveDungeon"),
	PLAYER_LIST("playerList"),
	PLAYER_MOVE("playerMove"),
	SERVER_MESSAGE("serverMessage"),
	WIN("win");


	private final String name;

	Events(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
