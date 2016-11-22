package com.flying.bird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by rootnext on 11/22/16.
 */

public class Tube {
    public static final int TUBE_WIDTH = 50;

    private static final int FLACTUATE = 20;
    private static final int TUBE_GAP = 100;
    private static final int LOWEROPPENING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBtmTube;
    private Random rand;

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBtmTube() {
        return posBtmTube;
    }

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(FLACTUATE + TUBE_GAP + LOWEROPPENING));
        posBtmTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

    }

    public void reposition( float x){
        posTopTube.set(x, rand.nextInt(FLACTUATE + TUBE_GAP + LOWEROPPENING));
        posBtmTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }
}
