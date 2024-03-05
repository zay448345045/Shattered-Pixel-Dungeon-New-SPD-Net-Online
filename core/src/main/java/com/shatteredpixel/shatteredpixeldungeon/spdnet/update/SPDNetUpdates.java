package com.shatteredpixel.shatteredpixeldungeon.spdnet.update;


import com.badlogic.gdx.Net;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.services.updates.AvailableUpdateData;
import com.shatteredpixel.shatteredpixeldungeon.services.updates.UpdateService;
import com.shatteredpixel.shatteredpixeldungeon.spdnet.SPDNetConfig;
import com.watabou.noosa.Game;

import java.util.regex.Pattern;

public class SPDNetUpdates extends UpdateService {

	private static Pattern descPattern = Pattern.compile("(.*?)(\r\n|\n|\r)(\r\n|\n|\r)---", Pattern.DOTALL + Pattern.MULTILINE);
	private static Pattern versionCodePattern = Pattern.compile("internal version number: ([0-9]*)", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supportsUpdatePrompts() {
		return true;
	}

	@Override
	public boolean supportsBetaChannel() {
		return true;
	}

	@Override
	public void checkForUpdate(boolean useMetered, boolean includeBetas, UpdateResultCallback callback) {

		if (!useMetered && !Game.platform.connectedToUnmeteredNetwork()) {
			callback.onConnectionFailed();
			return;
		}

		SPDNetConfig.refreshConfig(new Net.HttpResponseListener() {
			@Override
			public void handleHttpResponse(Net.HttpResponse httpResponse) {
				if (SPDNetConfig.config == null) {
					callback.onConnectionFailed();
				} else {
					String latestSPDVersion = SPDNetConfig.config.get("SPDVersion").asText();
					String latestNetVersion = SPDNetConfig.config.get("NetVersion").asText();
					if (isVersionNewer(ShatteredPixelDungeon.version, latestSPDVersion) || isVersionNewer(ShatteredPixelDungeon.netVersion.split("-")[0], latestNetVersion)) {
						AvailableUpdateData update = new AvailableUpdateData();
						update.versionName = latestSPDVersion + "-" + latestNetVersion;
						update.desc = SPDNetConfig.config.get("changeLog").asText();
						update.URL = SPDNetConfig.config.get("GithubUpdateUrl").asText();
						update.giteeURL = SPDNetConfig.config.get("GiteeUpdateUrl").asText();
						callback.onUpdateAvailable(update);
					} else {
						callback.onNoUpdateFound();
					}
				}
			}

			@Override
			public void failed(Throwable t) {
				callback.onConnectionFailed();
			}

			@Override
			public void cancelled() {
				callback.onConnectionFailed();
			}
		});

	}

	@Override
	public void initializeUpdate(AvailableUpdateData update) {
		Game.platform.openURI(update.URL);
	}

	@Override
	public boolean supportsReviews() {
		return false;
	}

	@Override
	public void initializeReview(ReviewResultCallback callback) {
		//does nothing, no review functionality here
		callback.onComplete();
	}

	@Override
	public void openReviewURI() {
		//does nothing
	}

	private static boolean isVersionNewer(String currentVersion, String newVersion) {
		int[] currentVersionNumbers = splitVersion(currentVersion);
		int[] lastedVersionNumbers = splitVersion(newVersion);
		boolean isVersionNewer = false;
		if (currentVersionNumbers[0] < lastedVersionNumbers[0]) {
			isVersionNewer = true;
		} else if (currentVersionNumbers[0] == lastedVersionNumbers[0]) {
			if (currentVersionNumbers[1] < lastedVersionNumbers[1]) {
				isVersionNewer = true;
			} else if (currentVersionNumbers[1] == lastedVersionNumbers[1]) {
				if (currentVersionNumbers[2] < lastedVersionNumbers[2]) {
					isVersionNewer = true;
				}
			}
		}
		return isVersionNewer;
	}

	private static int[] splitVersion(String version) {
		String[] parts = version.split("\\.");
		int[] numbers = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			numbers[i] = Integer.parseInt(parts[i]);
		}
		return numbers;
	}
}
