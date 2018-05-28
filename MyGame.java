package my.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont bitmapFont;
	Stage stage;
	ImageButton imageButton;
	ImageButton imageButton2;
	Business biz;
	Business biz2;
	Player player;
	float progressBarScale = 0.0f;
	int padding = 100;

	int textOffset;
	static int PADDING_Y_BUSINESS_NAME = 10;

	@Override
	public void create () {
		player = new Player();
		// drawing images
		batch = new SpriteBatch();
		img = new Texture("box.jpg");
		textOffset = 10;
		// draws text to screen
		bitmapFont = new BitmapFont();
		bitmapFont.setColor(Color.BLACK);

		Drawable drawable = new TextureRegionDrawable(new TextureRegion(img));
		imageButton = new ImageButton(drawable);
		imageButton2 = new ImageButton(drawable);

		biz = new Business("Lemonade Stand",5,img,1,1,0,0,5,imageButton,player);
		biz2 = new Business("Icecream Truck",1000,img,0,50,0,0,5,imageButton2,player);


		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true); //fills the screen

		table.add(biz.getProgressBarButton());
		table.row();
		table.add(imageButton);
		table.row();
		table.add(biz2.getProgressBarButton()).spaceTop(10);
		table.row();
		table.add(imageButton2);
		stage.addActor(table);
		biz.getProgressBarButton().setTransform(true);
		biz2.getProgressBarButton().setTransform(true);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		biz.timeCheck();
		biz2.timeCheck();
		stage.act();
		stage.draw();
		biz.getProgressBarButton().setScale(biz.getTime()/100f,1);
		biz2.getProgressBarButton().setScale(biz2.getTime()/100f,1);
		batch.begin();
		bitmapFont.draw(batch,biz.getName(),biz.getAbsCoords().x,biz.getAbsCoords().y+PADDING_Y_BUSINESS_NAME);
		// drawing text on screen
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
