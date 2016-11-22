package com.flying.bird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flying.bird.PlayBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PlayBird.WIDTH;
		config.height = PlayBird.HEIGHT;
		config.title = PlayBird.TITLE;
		new LwjglApplication(new PlayBird(), config);
	}
}
