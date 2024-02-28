package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.scene;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.Image;

import lombok.Getter;

@Getter
public enum Mode {
	IRONMAN("铁人模式", "没有玩家之间的实质性交互，使用你的技巧争夺排行榜上的高分，证明自己是地牢高手。", new Image(Assets.Sprites.RATKING, 0, 0, 16, 16)),
	FUN("娱乐模式", "随便摸，乐就行了。 : )", new Image(Assets.Sprites.RAT, 0, 0, 16, 16)),
	DAILY("每日挑战", "每天都有新的地牢，每天都有新排行榜", new Image(Assets.Sprites.RAT, 0, 15, 16, 16)),
	;
	private final String name;
	private final String description;
	private final Image icon;

	Mode(String name, String description, Image icon) {
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
}