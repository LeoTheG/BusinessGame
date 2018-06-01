package my.game;

import com.badlogic.gdx.Gdx;

public class Player {
    private long money;
    public static int OFFSET_Y = -10;

    public Player(){
        this.money = 100000L;
    }
    public long getMoney(){
        return this.money;
    }
    public void incMoney(long amt){
        this.money += amt;
    }
    public boolean canDecMoney(long amt){
        if (amt <= this.money ) {
            return true;
        }
        else return false;
    }
    public void decMoney(long amt){
        if (canDecMoney(amt))
            this.money -= amt;
    }
    public float properX(){
        return Gdx.graphics.getWidth()/2;
    }
    public float properY(){
        return Gdx.graphics.getHeight()-30;
    }
}
