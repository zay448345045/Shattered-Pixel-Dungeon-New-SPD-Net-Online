package com.shatteredpixel.shatteredpixeldungeon.spdnet.ui.scene;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Rankings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.TitleScene;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.GameRecord;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.Net;
import com.shatteredpixel.shatteredpixeldungeon.ui.Archs;
import com.shatteredpixel.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndDailies;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Music;
import com.watabou.utils.GameMath;

import java.util.ArrayList;

public class NetRankingsScene extends PixelScene {

	private static final float ROW_HEIGHT_MAX = 20;
	private static final float ROW_HEIGHT_MIN = 12;

	private static final float MAX_ROW_WIDTH = 160;

	private static final float GAP = 4;

	private Archs archs;
	private String playerName;
	Integer challengeCount;
	private Boolean winOnly;
	private String gameMode;
	private String sortCriteria;
	private int amountPerPage;
	private int totalPages;
	private int currentPage;
	private int totalElements;
	private ArrayList<GameRecord> records;

	@Override
	public void create() {

		super.create();

		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.THEME_1, Assets.Music.THEME_2},
				new float[]{1, 1},
				false);

		uiCamera.visible = false;

		int w = Camera.main.width;
		int h = Camera.main.height;

		archs = new Archs();
		archs.setSize(w, h);
		add(archs);

		Rankings.INSTANCE.load();

		RenderedTextBlock title = PixelScene.renderTextBlock("当前显示: " + Net.name + "的排行榜", 9);
		title.hardlight(Window.TITLE_COLOR);
		title.setPos(
				(w - title.width()) / 2f,
				(20 - title.height()) / 2f
		);
		align(title);
		add(title);

		if (Rankings.INSTANCE.records.size() > 0) {

			//attempts to give each record as much space as possible, ideally as much space as portrait mode
			float rowHeight = GameMath.gate(ROW_HEIGHT_MIN, (uiCamera.height - 26) / Rankings.INSTANCE.records.size(), ROW_HEIGHT_MAX);

			float left = (w - Math.min(MAX_ROW_WIDTH, w)) / 2 + GAP;
			float top = (h - rowHeight * Rankings.INSTANCE.records.size()) / 2;

			int pos = 0;

			for (GameRecord rec : records) {
				RecordButton row = new RecordButton(pos, pos == Rankings.INSTANCE.lastRecord, rec);
				float offset = 0;
				if (rowHeight <= 14) {
					offset = (pos % 2 == 1) ? 5 : -5;
				}
				row.setRect(left + offset, top + pos * rowHeight, w - left * 2, rowHeight);
				add(row);

				pos++;
			}

			if (Rankings.INSTANCE.totalNumber >= Rankings.TABLE_SIZE) {

				RenderedTextBlock label = PixelScene.renderTextBlock(8);
				label.hardlight(0xCCCCCC);
				label.setHightlighting(true, Window.SHPX_COLOR);
				label.text(Messages.get(this, "total") + " _" + Rankings.INSTANCE.wonNumber + "_/" + Rankings.INSTANCE.totalNumber);
				add(label);

				label.setPos(
						(w - label.width()) / 2,
						h - label.height() - 2 * GAP
				);
				align(label);

			}

		} else {

			RenderedTextBlock noRec = PixelScene.renderTextBlock(Messages.get(this, "no_games"), 8);
			noRec.hardlight(0xCCCCCC);
			noRec.setPos(
					(w - noRec.width()) / 2,
					(h - noRec.height()) / 2
			);
			align(noRec);
			add(noRec);

		}

		ExitButton btnExit = new ExitButton();
		btnExit.setPos(Camera.main.width - btnExit.width(), 0);
		add(btnExit);

		int left = 0;

		if (Rankings.INSTANCE.latestDaily != null) {
			IconButton btnDailies = new IconButton(Icons.CALENDAR.get()) {
				@Override
				protected void onClick() {
					ShatteredPixelDungeon.scene().addToFront(new WndDailies());
				}

				@Override
				protected void onPointerUp() {
					icon.hardlight(0.5f, 1f, 2f);
				}
			};
			btnDailies.icon().hardlight(0.5f, 1f, 2f);
			btnDailies.setRect(left, 0, 20, 20);
			left += 20;
			add(btnDailies);
		}

		if (Dungeon.daily) {
			addToFront(new WndDailies());
		}

		fadeIn();
	}

	@Override
	public void destroy() {
		super.destroy();
		//so that opening daily records does not trigger WndDailies opening on future visits
		Dungeon.daily = Dungeon.dailyReplay = false;
	}

	@Override
	protected void onBackPressed() {
		ShatteredPixelDungeon.switchNoFade(TitleScene.class);
	}

	public void setRankings(int totalPages, int currentPage, int totalElements, ArrayList<GameRecord> records) {
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.totalElements = totalElements;
		this.records = records;
	}
}
