package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Player {
	private String name;
	private String power;
	private Status status;
}
