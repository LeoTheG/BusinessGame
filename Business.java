package my.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Business {
    private String name;
    private int cost;
    private Texture img;
    private int quantity;
    //TODO: timer class
    private int payout;
    private int x;
    private int y;
    private int multipler;
    private ImageButton imageButton;
    private Player player;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMultipler() {
        return multipler;
    }

    public void setMultipler(int multipler) {
        this.multipler = multipler;
    }

    public void buy(int amt){
        this.quantity += amt;
    }
    public void init(){
        imageButton.addListener( new ClickListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				buy(1);
				player.decMoney(cost);
				return true;
			}
		});
    }

    public Business(String name, int cost, Texture img, int quantity, int payout, int x, int y, int multiplier, ImageButton imageButton, Player player){
        this.name = name;
        this.cost = cost;
        this.img = img;
        this.quantity = quantity;
        this.payout = payout;
        this.x = x;
        this.y = y;
        this.multipler = multiplier;
        this.imageButton = imageButton;
        this.player = player;

        init();

    }

}
