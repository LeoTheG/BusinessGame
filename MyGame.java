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
	Player player;

	int textOffset;

	@Override
	public void create () {
		player = new Player();
		batch = new SpriteBatch();
		img = new Texture("box.jpg");
		textOffset = 10;
		bitmapFont = new BitmapFont();
		bitmapFont.setColor(Color.BLACK);
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(img));
		imageButton = new ImageButton(drawable);
		biz = new Business("Lemonade Stand",5,img,1,1,0,0,5,imageButton,player);


		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true); //fills the screen
		table.align(Align.top | Align.center);
		table.center().center();

		table.add(imageButton).width((int)(imageButton.getWidth() * 0.5)).height((int)(imageButton.getHeight() * 0.5));
		stage.addActor(table);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		biz.timeCheck();
		stage.act();
		stage.draw();
		batch.begin();

		// drawing text on screen
		bitmapFont.draw(batch,""+biz.getQuantity(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		bitmapFont.draw(batch,"$"+biz.display(player.getMoney()),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() + player.OFFSET_Y);
		//bitmapFont.draw(batch,""+biz.getTime(),Gdx.graphics.getWidth()/2 + biz.TIMER_OFFSET_X,Gdx.graphics.getHeight()/2 + biz.TIMER_OFFSET_Y);
		bitmapFont.draw(batch,biz.getTime()+"%",Gdx.graphics.getWidth()/2 + biz.TIMER_OFFSET_X,Gdx.graphics.getHeight()/2);
		bitmapFont.draw(batch,"Cost: $"+biz.display(biz.getCost()),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2 + biz.TIMER_OFFSET_Y);
		bitmapFont.draw(batch,"Payout: $"+biz.display(biz.getPayout()),Gdx.graphics.getWidth()/2 + biz.OFFSET_X_PAYOUT,Gdx.graphics.getHeight()/2);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
