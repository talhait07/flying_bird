package com.flying.bird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flying.bird.PlayBird;
import com.flying.bird.sprites.Bird;
import com.flying.bird.sprites.Tube;

/**
 * Created by rootnext on 11/22/16.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 120;
    private static final int TUBE_COUNT = 3;
    private static final int GROUND_Y_OFFSET = -60;
    private  int SCORE =0;
    public static  String YOURSCORE = "";


    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Array<Tube> tubes;
    private Vector2 groundPosition1,groundPosition2;
    private BitmapFont myFont;
    private Sound collideSound;




    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,200);
        cam.setToOrtho(false, PlayBird.WIDTH / 2, PlayBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        myFont = new BitmapFont();
        collideSound = Gdx.audio.newSound(Gdx.files.internal("collide.mp3"));


        tubes = new Array<Tube>();
        for (int i = 2; i <= TUBE_COUNT + 1; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        this.gsm = gsm;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        updateScore();
        bird.update(dt);
        cam.position.x = (bird.getPosition().x) + 80;

        for(int i = 0; i < tubes.size; i++ ){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2 ) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                int total_position = (int)tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT;

                tube.reposition(total_position);
            }

            if(tube.collides(bird.getBounds())){
                collideSound.play(0.5f);
                SCORE = 0;
                YOURSCORE = "score: " + SCORE;
                System.out.println("initial score: "+ SCORE);
                gsm.set(new PlayState(gsm));

            }else {
                    SCORE ++;
                    YOURSCORE = "score: " + SCORE;
                    System.out.println("score: "+ SCORE);
            }
        }
        if(bird.getPosition().y < ground.getHeight() + GROUND_Y_OFFSET){
            collideSound.play(0.5f);
            gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2),0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y,
                bird.getBird().getRegionWidth()/2, bird.getBird().getRegionHeight()/2,
                bird.getBird().getRegionWidth(), bird.getBird().getRegionHeight(), 1, 1, bird.getRotation());
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBtmTube().x, tube.getPosBtmTube().y);

        }


        sb.draw(ground, groundPosition1.x, groundPosition1.y);
        sb.draw(ground, groundPosition2.x, groundPosition2.y);
        myFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        myFont.setUseIntegerPositions(false);
        myFont.draw(sb, YOURSCORE,groundPosition1.x - 50, groundPosition1.y + 100);
        sb.end();

    }

    @Override
    public void dispose() {
        bird.dispose();
        bg.dispose();
        ground.dispose();
        collideSound.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        System.out.println("play state disposed");
    }

    private void updateGround(){

        if(cam.position.x - (cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth())
            groundPosition1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth())
            groundPosition2.add(ground.getWidth() * 2, 0);
    }

    private void updateScore(){

        if(cam.position.x - (cam.viewportWidth / 2) > groundPosition1.x + ground.getWidth())
            groundPosition1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPosition2.x + ground.getWidth())
            groundPosition2.add(ground.getWidth() * 2, 0);
    }
}
