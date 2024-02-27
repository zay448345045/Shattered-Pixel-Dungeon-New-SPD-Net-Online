package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {
	private String name;
	private String power;
	private Status status;
}
