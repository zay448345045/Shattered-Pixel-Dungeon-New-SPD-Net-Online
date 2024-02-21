package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui;

import com.shatteredpixel.shatteredpixeldungeon.spdnet.windows.NetWindow;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
public class NetBtn extends IconButton {
	public static final int HEIGHT = 20;
	public static final int MIN_WIDTH = 21;

	public NetBtn() {
		super(NetIcons.get(NetIcons.GLOBE));
	}

	@Override
	protected void onClick() {
		super.onClick();
		NetWindow.showServerInfo();
	}

	@Override
	protected boolean onLongClick() {
		NetWindow.showKeyInput();
		return true;
	}
}
