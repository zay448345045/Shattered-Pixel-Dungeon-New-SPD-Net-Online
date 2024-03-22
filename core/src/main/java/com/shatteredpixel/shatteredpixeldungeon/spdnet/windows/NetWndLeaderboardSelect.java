package com.shatteredpixel.shatteredpixeldungeon.spdnet.windows;

import com.shatteredpixel.shatteredpixeldungeon.ui.OptionSlider;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.ColorBlock;

public class NetWndLeaderboardSelect extends Window {
	RenderedTextBlock title;
	ColorBlock sep1;
	OptionSlider optType;
	// ↓缺一个文本输入框
	//
	ColorBlock sep2;
	OptionSlider optChallenge;
	OptionSlider optWinOnly;
	OptionSlider optMode;
	ColorBlock sep3;
	OptionSlider optSortCriteria;

	public NetWndLeaderboardSelect() {
	}
}
