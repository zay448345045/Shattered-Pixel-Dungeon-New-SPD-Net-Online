package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.scene;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class ModeWindow extends Window {
	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 220;
	private static final int HEIGHT = 150;

	private static final int MARGIN = 2;
	public ModeWindow() {
		super(PixelScene.landscape() ? WIDTH_L : WIDTH_P, HEIGHT, Chrome.get(Chrome.Type.WINDOW));

	}
}
