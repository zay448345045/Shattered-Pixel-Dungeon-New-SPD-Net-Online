package com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.actions;

import com.shatteredpixel.shatteredpixeldungeon.Rankings;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.web.structure.Data;
import com.watabou.utils.Bundle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CWin extends Data {
	// 胜利记录, 实际上为Rankings.Record json字符串
	private String record;

	public CWin(Rankings.Record record) {
		Bundle bundle = new Bundle();
		record.storeInBundle(bundle);
		this.record = bundle.toString();
	}
}
