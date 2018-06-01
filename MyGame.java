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
	Business biz;
	Business biz2;
	Business biz3;
	Player player;

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

		biz = new Business("Lemonade Stand",5,img,1,1,player,bitmapFont);
		biz2 = new Business("Icecream Truck",1000,img,0,50,player,bitmapFont);
		biz3 = new Business("Vape Store",5000,img,0,500,player,bitmapFont);


		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true); //fills the screen

		table.add(biz.getProgressBarButton());
		table.row();
		table.add(biz.getImageButton());
		table.row();
		table.add(biz2.getProgressBarButton()).spaceTop(10);
		table.row();
		table.add(biz2.getImageButton());
		table.row();
		table.add(biz3.getProgressBarButton()).spaceTop(10);
		table.row();
		table.add(biz3.getImageButton());
		stage.addActor(table);
		biz.getProgressBarButton().setTransform(true);
		biz2.getProgressBarButton().setTransform(true);
		biz3.getProgressBarButton().setTransform(true);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		biz.timeCheck();
		biz2.timeCheck();
		biz3.timeCheck();
		stage.act();
		stage.draw();
		biz.getProgressBarButton().setScale(biz.getTime()/100f,1);
		biz2.getProgressBarButton().setScale(biz2.getTime()/100f,1);
		biz3.getProgressBarButton().setScale(biz3.getTime()/100f,1);
		batch.begin();

		for (Business.labels label : Business.labels.values()){
			bitmapFont.draw(batch,biz.getGlyphLayout(label),biz.properX(label),biz.properY(label));
		}

		for (Business.labels label : Business.labels.values()){
			bitmapFont.draw(batch,biz2.getGlyphLayout(label),biz2.properX(label),biz2.properY(label));
		}
		for (Business.labels label : Business.labels.values()){
			bitmapFont.draw(batch,biz3.getGlyphLayout(label),biz3.properX(label),biz3.properY(label));
		}
		bitmapFont.draw(batch,"$"+player.getMoney(),player.properX(),player.properY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
