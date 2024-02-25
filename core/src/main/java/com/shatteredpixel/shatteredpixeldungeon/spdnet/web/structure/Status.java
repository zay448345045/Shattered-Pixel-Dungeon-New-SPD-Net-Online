package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Status {
	private int challenges;
	private long seed;
	private int depth;
}
