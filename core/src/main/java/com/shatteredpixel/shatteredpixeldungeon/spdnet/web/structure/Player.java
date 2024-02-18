package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Player {
	private final String name;
	private final String power;
	private final Status status;
}
