package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.actors;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Player;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Status;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.Map;
import java.util.Set;

/**
 * 用于在当前客户端呈现的其他玩家类
 */
public class NetHero extends Hero {

	{
		alignment = Alignment.NEUTRAL;
	}

	public String name;
	public int tier;

	public NetHero(String name) {
		super();
		this.name = name;
	}

	public static NetHero findPlayerAtCell(int cell) {
		for (NetHero player : Dungeon.level.players) {
			if (player.pos == cell) {
				return player;
			}
		}
		return null;
	}

	/**
	 * 不存储
	 */
	@Override
	public void storeInBundle(Bundle bundle) {
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
	}

	@Override
	public boolean act() {
		return true;
	}

	/**
	 * 使用十字架
	 *
	 * @param isBlessed 当前爆的这个十字架是否被祝福
	 */
	public void useAnkh(boolean isBlessed, int blessedAnkhLeft, int unblessedAnkhLeft) {
		curAction = null;
		interrupt();
		SpellSprite.show(this, SpellSprite.ANKH);
		Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
	}

	// 死了啦, 都是你害的啦
	@Override
	public void die(Object cause) {
		destroy();
		// 掉楼好像是会使用转圈消失的动画, 这段代码在哪呢?
		if (cause != Chasm.class) sprite.die();
		Game.runOnRenderThread(() -> Sample.INSTANCE.play(Assets.Sounds.DEATH));
	}

	@Override
	public void move(int newPos, boolean travelling) {
		// 简简单单改个位
		sprite.interruptMotion();
		sprite.move(pos, newPos);
		pos = newPos;
	}

	/**
	 * 把当前在线玩家与当前楼层同步
	 * 切换楼层或者其他玩家离开当前层时调用
	 * GameScene不太方便直接删除玩家 >:( 玩家离开当前层暂时先用这个方法
	 */
	public static void syncWithCurrentLevel() {
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			GameScene.clearPlayers();
			Set<Map.Entry<String, Player>> entries = Net.playerList.entrySet();
			for (Map.Entry<String, Player> entry : entries) {
				addPlayer(entry.getValue());
			}
		}
	}

	public static void addPlayer(Player player) {
		if (ShatteredPixelDungeon.scene() instanceof GameScene) {
			Status status = player.getStatus();
			if (status == null) {
				return;
			}
			if (status.getSeed() == Dungeon.seed && status.getDepth() == Dungeon.depth) {
				NetHero hero = new NetHero(player.getName());
				hero.heroClass = status.getHeroClassEnum();
				hero.tier = status.getArmorTier();
				hero.pos = status.getPos();
				GameScene.addPlayer(hero);
			}
		}
	}

	public static NetHero getPlayer(String name) {
		if (Dungeon.level == null) {
			return null;
		}
		for (NetHero player : Dungeon.level.players) {
			if (player.name.equals(name)) {
				return player;
			}
		}
		return null;
	}
}
