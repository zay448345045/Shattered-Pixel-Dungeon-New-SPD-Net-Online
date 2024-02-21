package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui;

import static com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene.landscape;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.windows.NetWindow;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;

public class NetBtn extends StyledButton {
	public static final int HEIGHT = 24;
	public static final int MIN_WIDTH = 30;

	private ShatteredPixelDungeon instance = ((ShatteredPixelDungeon) ShatteredPixelDungeon.instance);

	public NetBtn() {
		super(landscape() ? Chrome.Type.WINDOW : Chrome.Type.GREY_BUTTON_TR, "");
		icon(NetIcons.get(NetIcons.GLOBE));
		icon.brightness(landscape() ? 0.4f : 0.6f);
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
