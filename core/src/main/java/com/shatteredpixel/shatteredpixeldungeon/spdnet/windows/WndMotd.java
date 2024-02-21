package com.shatteredpixel.shatteredpixeldungeon.spdnet.windows;

import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.HeroSelectScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.StartScene;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.BlueButton;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.LabeledText;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.NetIcons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.windows.IconTitle;

public class WndMotd extends NetWindow {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 144;

	private static final int MARGIN = 2;

	public WndMotd(String motd, long seed) {
		super();

		int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

		float pos = 0;
		//TODO 多语言
		IconTitle tfTitle = new IconTitle(NetIcons.get(NetIcons.NEWS), "SPDNet--欢迎你");
		tfTitle.setRect(0, pos, width, 0);
		add(tfTitle);

		pos = tfTitle.bottom() + 2 * MARGIN;

		layoutBody(pos, motd, seed);
	}

	private void layoutBody(float pos, String motd, long seed) {
		int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

		RenderedTextBlock tfMesage = PixelScene.renderTextBlock(6);
		tfMesage.text(motd, width);
		tfMesage.setPos(0, pos);
		add(tfMesage);

		pos = tfMesage.bottom() + 2 * MARGIN;

		LabeledText seedText = new LabeledText("Seed", String.valueOf(seed), 6, 6) {
			@Override
			protected void layout() {
				super.layout();
				text().hardlight(0x008000);
			}
		};
		seedText.setPos(0, pos);
		add(seedText);

		BlueButton playBtn = new BlueButton("Play") {
			@Override
			protected void onClick() {
				super.onClick();
				if (GamesInProgress.checkAll().size() == 0) {
					GamesInProgress.selectedClass = null;
					GamesInProgress.curSlot = 1;
					if (!(ShatteredPixelDungeon.scene() instanceof HeroSelectScene))
						ShatteredPixelDungeon.switchScene(HeroSelectScene.class);
					else ShatteredPixelDungeon.switchNoFade(HeroSelectScene.class);

				} else {
					ShatteredPixelDungeon.switchNoFade(StartScene.class);
				}
			}
		};

		playBtn.setRect(width - 30, pos, 30, 20);
		add(playBtn);

		pos = playBtn.bottom() + 2 * MARGIN;

		resize(width, (int) (pos - MARGIN));
	}

	protected boolean enabled(int index) {
		return true;
	}

	protected void onSelect(int index) {
	}


}
