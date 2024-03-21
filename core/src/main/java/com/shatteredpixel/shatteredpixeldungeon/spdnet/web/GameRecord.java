package com.shatteredpixel.shatteredpixeldungeon.spdnet.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shatteredpixel.shatteredpixeldungeon.Rankings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameRecord {
	private String cause;
	private boolean win;
	private int score;
	@JsonProperty("class")
	private String heroClass;
	private int tier;
	private int level;
	private int depth;
	private boolean ascending;
	private String date;
	private String version;
	@JsonProperty("net_version")

	private String netVersion;
	@JsonProperty("game_mode")
	private String gameMode;

	private Hero hero;
	private String badges;
	private String handlers;
	private int challenges;
	@JsonProperty("challenge_amount")
	private int challengeAmount;
	@JsonProperty("game_version")
	private int gameVersion;
	private long seed;
	@JsonProperty("custom_seed")
	private String customSeed;
	private boolean daily;
	private boolean dailyReplay;

	private int gold;
	private int maxDepth;
	private int maxAscent;
	private int enemiesSlain;
	private int foodEaten;
	private int potionsCooked;
	private int priranhas;
	private int ankhsUsed;
	@JsonProperty("prog_score")
	private int progScore;
	@JsonProperty("item_val")
	private int itemVal;
	@JsonProperty("tres_score")
	private int tresScore;
	@JsonProperty("flr_expl")
	private String flrExpl;
	@JsonProperty("expl_score")
	private int explScore;
	@JsonProperty("boss_scores")
	private int[] bossScores;
	@JsonProperty("tot_boss")
	private int totBoss;
	@JsonProperty("quest_scores")
	private int[] questScores;
	@JsonProperty("tot_quest")
	private int totQuest;
	@JsonProperty("win_mult")
	private float winMult;
	@JsonProperty("chal_mult")
	private float chalMult;
	@JsonProperty("total_score")
	private int totalScore;
	private int upgradesUsed;
	private int sneakAttacks;
	private int thrownAssists;
	private int spawnersAlive;
	private int duration;
	private boolean qualifiedForNoKilling;
	private boolean qualifiedForBossRemainsBadge;
	private boolean qualifiedForBossChallengeBadge;
	private boolean amuletObtained;
	private boolean won;
	private boolean ascended;
	@JsonProperty("player_name")
	private String playerName;

	public String desc() {
		if (win) {
			if (ascending) {
				return Messages.get(Rankings.Record.class, "ascended");
			} else {
				return Messages.get(Rankings.Record.class, "won");
			}
		} else if (getClass(cause) == null) {
			return Messages.get(Rankings.Record.class, "something");
		} else {
			String result = Messages.get(getClass(cause), "rankings_desc", (Messages.get(getClass(cause), "name")));
			if (result.contains(Messages.NO_TEXT_FOUND)) {
				return Messages.get(Rankings.Record.class, "something");
			} else {
				return result;
			}
		}
	}

	public Class getClass(String cause) {
		String clName = cause.replace("class ", "");
		if (!clName.isEmpty()) {
			return Reflection.forName(clName);
		}
		return null;
	}

	public void setHero(String hero) {
		Hero heroObject = new Hero();
		heroObject.restoreFromBundle(Bundle.fromString(hero));
		this.hero = heroObject;
	}
}
