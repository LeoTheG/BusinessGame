package my.mygdx.game;

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
    public boolean decMoney(long amt){
        if (amt <= this.money ) {
            this.money -= amt;
            return true;
        }
        else return false;
    }
}
