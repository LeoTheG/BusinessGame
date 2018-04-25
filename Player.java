package my.game;

public class Player {
    private int money;

    public Player(){
        this.money = 0;
    }
    public int getMoney(){
        return this.money;
    }
    public void incMoney(int amt){
        this.money += amt;
    }
    public void decMoney(int amt){
        this.money -= amt;
    }
}
