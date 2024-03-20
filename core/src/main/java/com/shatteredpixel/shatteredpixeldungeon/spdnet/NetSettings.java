package com.shatteredpixel.shatteredpixeldungeon.spdnet;

import lombok.Getter;
import lombok.Setter;

/**
 * 用来存储某些长期保存的变量
 */
public class NetSettings {
	@Getter
	@Setter
	private static String key = "default";

}
