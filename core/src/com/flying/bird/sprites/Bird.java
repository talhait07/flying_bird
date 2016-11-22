package com.flying.bird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by rootnext on 11/22/16.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 velocity;
    private Vector3 position;

    private Texture bird;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
    }

    public void update(float dt){
        if(position.y > 0)

            velocity.add(0,GRAVITY,0);

        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);

        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);

    }

    public void jump(){
        velocity.y = 250;
    }
}