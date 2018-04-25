package my.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
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
	Skin buttonSkin;
	TextureAtlas atlas;
	TextButton button;
	Stage stage;
	ImageButton imageButton;
	Business biz;
	Skin skin;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		img = new Texture("box.jpg");
		bitmapFont = new BitmapFont();
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(img));
		imageButton = new ImageButton(drawable);
		biz = new Business("Lemonade Stand",5,img,1,1,0,0,5,imageButton);


		//--------
		//stage = new Stage(new ExtendViewport(800, 840));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		//table.setSkin(skin);
		table.setFillParent(true); //fills the screen
		table.align(Align.top | Align.center);
		table.center().center();


		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = bitmapFont;
		textButtonStyle.fontColor = Color.BLACK;
		TextButton button1 = new TextButton("This is a button!!!", textButtonStyle);
		//table.add(button1);
		//table.add(imageButton).width((int)(imageButton.getWidth() * 0.5));
		table.add(imageButton).width((int)(imageButton.getWidth() * 0.5)).height((int)(imageButton.getHeight() * 0.5));
		//table.add(imageButton).height((int)(imageButton.getHeight() * 0.5));
		//table.row();
		stage.addActor(table);
		// ----------
		imageButton.addListener( new ClickListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				biz.buy(1);
				return true;
			}
		});
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		batch.begin();
		bitmapFont.draw(batch,""+biz.getQuantity(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		bitmapFont.draw(batch,""+biz.getQuantity(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		/*
		batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth()/4, Gdx.graphics.getHeight()/6-img.getHeight()/4, img.getWidth()/2, img.getHeight()/2);
		batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth()/4, Gdx.graphics.getHeight()/6-img.getHeight()/4 + 200, img.getWidth()/2, img.getHeight()/2);
		batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth()/4, Gdx.graphics.getHeight()/6-img.getHeight()/4 + 400, img.getWidth()/2, img.getHeight()/2);
		batch.draw(img, Gdx.graphics.getWidth()/2 - img.getWidth()/4, Gdx.graphics.getHeight()/6-img.getHeight()/4 + 600, img.getWidth()/2, img.getHeight()/2);
		*/
		//bitmapFont.draw(batch, "$10",Gdx.graphics.getWidth()/2 - img.getWidth()/4 + 45,Gdx.graphics.getHeight()/6-img.getHeight()/4 + 25);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
