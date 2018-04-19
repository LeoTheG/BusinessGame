package my.game;

import com.badlogic.gdx.graphics.Texture;

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

    public Business(String name, int cost, Texture img, int quantity, int payout, int x, int y, int multiplier){
        this.name = name;
        this.cost = cost;
        this.img = img;
        this.quantity = quantity;
        this.payout = payout;
        this.x = x;
        this.y = y;
        this.multipler = multiplier;
    }

}
