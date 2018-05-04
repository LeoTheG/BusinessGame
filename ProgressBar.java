package my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Leo-Laptop on 5/3/2018.
 */
public class ProgressBar {
    private Texture barTexture;
    private ImageButton barButton;
    private int maxWidth;

    Drawable drawable;
    public ProgressBar(){
       barTexture = new Texture("progress-bar.png");
       drawable  = new TextureRegionDrawable(new TextureRegion(barTexture));
       barButton = new ImageButton(drawable);
       maxWidth = barTexture.getWidth();
    }
    public ImageButton getBarButton(){
        return barButton;
    }
    public int getMaxWidth(){
        return maxWidth;
    }

}
