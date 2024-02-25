package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure;

import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {
	private int challenges;
	private long seed;
	private int depth;
	private int heroClass;
	/**
	 * 护甲等阶, 大多数情况下就是Armor的tier
	 * 但是有两种特殊情况:
	 * 没穿护甲的时候是0
	 * 穿职业护甲的时候是10(和板甲区分开)
	 */
	private int armorTier;

	public Status(int challenges, long seed, int depth, int heroClass, Armor armor) {
		this.challenges = challenges;
		this.seed = seed;
		this.depth = depth;
		this.heroClass = heroClass;
		if (armor == null) {
			armorTier = 0;
		} else if (armor instanceof ClassArmor) {
			armorTier = 10;
		} else {
			armorTier = armor.tier;
		}
	}
}
