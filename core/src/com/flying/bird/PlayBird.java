package com.flying.bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flying.bird.states.GameStateManager;
import com.flying.bird.states.MenuSate;

public class PlayBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 700;
	public static final java.lang.String TITLE = "Flying bird";

	private GameStateManager gsm;
	private Music music;


	SpriteBatch batch;
	Texture img;

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("badlogic.jpg");
		gsm.push(new MenuSate(gsm));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
}
