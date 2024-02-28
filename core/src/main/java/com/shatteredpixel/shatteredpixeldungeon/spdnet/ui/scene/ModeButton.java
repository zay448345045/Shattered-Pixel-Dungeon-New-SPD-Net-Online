package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.scene;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.TitleScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.watabou.noosa.Game;

public class ModeButton extends StyledButton {


	public ModeButton(Mode mode) {
		super(Chrome.Type.RED_BUTTON, mode.getName(), 9);
		icon(mode.getIcon());
		width = 70;
		height = 20;
	}

	public void setMode(Mode mode) {
		addToBack(Chrome.get(Chrome.Type.RED_BUTTON));

		text = PixelScene.renderTextBlock(9);
		text.text(mode.getName());
		add(text);
		icon(mode.getIcon());
	}

	@Override
	protected void onClick() {
		// TODO
	}
}
