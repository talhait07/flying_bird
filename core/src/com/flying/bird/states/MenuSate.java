package com.flying.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flying.bird.PlayBird;

/**
 * Created by rootnext on 11/22/16.
 */

public class MenuSate extends State {
    private Texture background;
    private Texture playButton;
//    protected GameStateManager gsm;

    public MenuSate(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.jpg");
        playButton = new Texture("playbutton.png");
        this.gsm = gsm;

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, PlayBird.WIDTH, PlayBird.HEIGHT);
        sb.draw(playButton,(PlayBird.WIDTH/2 - playButton.getWidth()/2), PlayBird.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
        background.dispose();

    }

}
