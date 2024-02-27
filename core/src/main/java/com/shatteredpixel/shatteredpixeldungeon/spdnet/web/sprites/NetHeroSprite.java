package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.actors.NetHero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.RectF;

public class NetHeroSprite extends CharSprite {

	private static final int FRAME_WIDTH = 12;
	private static final int FRAME_HEIGHT = 15;

	private static final int RUN_FRAMERATE = 20;

	private static TextureFilm tiers;

	private Animation fly;
	private Animation read;

	public NetHeroSprite(NetHero player) {
		texture(player.heroClass.spritesheet());
		link(player);
		updateArmor();

		if (ch.isAlive())
			idle();
		else
			die();
	}

	public void updateArmor() {

		TextureFilm film = new TextureFilm(tiers(), ((NetHero) ch).tier, FRAME_WIDTH, FRAME_HEIGHT);

		idle = new Animation(1, true);
		idle.frames(film, 0, 0, 0, 1, 0, 0, 1, 1);

		run = new Animation(RUN_FRAMERATE, true);
		run.frames(film, 2, 3, 4, 5, 6, 7);

		die = new Animation(20, false);
		die.frames(film, 8, 9, 10, 11, 12, 11);

		attack = new Animation(15, false);
		attack.frames(film, 13, 14, 15, 0);

		zap = attack.clone();

		operate = new Animation(8, false);
		operate.frames(film, 16, 17, 16, 17);

		fly = new Animation(1, true);
		fly.frames(film, 18);

		read = new Animation(20, false);
		read.frames(film, 19, 20, 20, 20, 20, 20, 20, 20, 20, 19);

		if (Dungeon.hero.isAlive())
			idle();
		else
			die();
	}

	@Override
	public void place(int p) {
		super.place(p);
	}

	@Override
	public void move(int from, int to) {
		super.move(from, to);
		if (ch != null && ch.flying) {
			play(fly);
		}
	}

	@Override
	public void idle() {
		super.idle();
		if (ch != null && ch.flying) {
			play(fly);
		}
	}

	@Override
	public void jump(int from, int to, float height, float duration, Callback callback) {
		super.jump(from, to, height, duration, callback);
		play(fly);
	}

	public synchronized void read() {
		animCallback = new Callback() {
			@Override
			public void call() {
				idle();
				ch.onOperateComplete();
			}
		};
		play(read);
	}

	@Override
	public void bloodBurstA(PointF from, int damage) {
		PointF c = center();
		int n = (int) Math.min(9 * Math.sqrt((double) damage / ch.HT), 9);
		Splash.at(c, PointF.angle(from, c), 3.1415926f / 2, blood(), n);
	}

	@Override
	public void update() {
		sleeping = ch.isAlive() && ((Hero) ch).resting;

		super.update();
	}

	public void sprint(float speed) {
		run.delay = 1f / speed / RUN_FRAMERATE;
	}

	public static TextureFilm tiers() {
		if (tiers == null) {
			SmartTexture texture = TextureCache.get(Assets.Sprites.ROGUE);
			tiers = new TextureFilm(texture, texture.width, FRAME_HEIGHT);
		}

		return tiers;
	}

	public static Image avatar(HeroClass cl, int armorTier) {

		RectF patch = tiers().get(armorTier);
		Image avatar = new Image(cl.spritesheet());
		RectF frame = avatar.texture.uvRect(1, 0, FRAME_WIDTH, FRAME_HEIGHT);
		frame.shift(patch.left, patch.top);
		avatar.frame(frame);

		return avatar;
	}
}
