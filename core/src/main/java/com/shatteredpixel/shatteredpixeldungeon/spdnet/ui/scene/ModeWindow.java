package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.scene;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.Mode;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.NetInProgress;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.SPDNetChrome;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import lombok.Getter;
import lombok.Setter;

public class ModeWindow extends Window {
	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 220;
	private static final int HEIGHT = 150;
	private static final int MARGIN = 2;

	public ModeWindow() {
		super(PixelScene.landscape() ? WIDTH_L : WIDTH_P, HEIGHT, Chrome.get(Chrome.Type.WINDOW));
		int buttonWidth = (width - (Mode.values().length - 1) * MARGIN) / Mode.values().length;
		for (int i = 0; i < Mode.values().length; i++) {
			ModeButton button = new ModeButton(this, Mode.values()[i], buttonWidth, 20);
			button.setPos(i * (buttonWidth + MARGIN), MARGIN);
			add(button);
		}
	}

	@Getter
	@Setter
	private static class ModeButton extends StyledButton {
		private final Mode mode;
		private final Window window;

		public ModeButton(Window window, Mode mode, int width, int height) {
			super(Chrome.Type.RED_BUTTON, mode.getName().substring(0, 2), 9);
			this.window = window;
			this.mode = mode;
			icon(mode.getIcon());
			this.width = width;
			this.height = height;
		}

		@Override
		protected void onClick() {
			window.destroy();
			NetInProgress.mode = mode;
		}

		@Override
		public void update() {
			super.update();
			if (mode == NetInProgress.mode) {
				bg = Chrome.get(Chrome.Type.RED_BUTTON);
			} else {
				bg = SPDNetChrome.get(SPDNetChrome.Type.BUTTON);
			}
		}
	}
}
