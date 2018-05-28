package my.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Timer;
import java.util.TimerTask;

public class Business {
    private String name;
    private long cost;
    private Texture img;
    private long quantity;
    private long payout;
    private int x;
    private int y;
    private int multipler;
    private ImageButton imageButton;
    private Player player;
    private Time time;
    private Timer timer;
    public static int TIMER_OFFSET_X = -50;
    public static int TIMER_OFFSET_Y = -20;
    public static double RATE_TIMER = 0.8;
    public static double RATE_PAYOUT = 1.25;
    public static double RATE_COST = 1.29;
    public static int OFFSET_X_PAYOUT = 50;
    public int timerTime = 10;

    private ProgressBar progressBar;

    static final long MILLION = 1000000L;
    static final long BILLION = 1000000000L;
    static final long TRILLION = 1000000000000L;

    public ImageButton getProgressBarButton(){
        return progressBar.getBarButton();
    }
    public int getProgressBarMaxWidth(){
        return progressBar.getMaxWidth();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPayout() {
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
    public int getTime(){
        return time.time;
    }
    public String displayCost(){
        return this.cost < MILLION ? String.valueOf(cost) :
                this.cost < BILLION ? cost / MILLION + "." + (new String(String.valueOf(cost%MILLION)))+ "M":
                this.cost < TRILLION ? cost / BILLION + "." + (new String(String.valueOf(cost%BILLION))) + "B":
                        this.cost / TRILLION + "." +  (new String(String.valueOf(cost%TRILLION))) + "T";
    }
    public String display(long amt){
         return amt < MILLION ? String.valueOf(amt):
                amt < BILLION ? amt / MILLION + "." + (new String(String.valueOf(amt%MILLION))).substring(0, 3) +"M":
                amt < TRILLION ? amt / BILLION + "." + (new String(String.valueOf(amt%BILLION))).substring(0, 3)+ "B":
                        amt / TRILLION +  "." + (new String(String.valueOf(amt%TRILLION))).substring(0,3) +"T";
    }
    public String displayPayout(){
        return this.payout < MILLION ? String.valueOf(payout) :
                this.payout < BILLION ? payout / MILLION + "." + (new String(String.valueOf(payout%MILLION))).substring(0, 3) +"M":
                this.payout < TRILLION ? payout / BILLION + "." + (new String(String.valueOf(payout%BILLION))).substring(0,3) + "B":
                        this.payout / TRILLION +  "." + (new String(String.valueOf(payout%TRILLION))).substring(0,3) +"T";
    }
    // called when user presses on button
    public void buy(int amt){
        this.quantity += amt;
        this.payout *= RATE_PAYOUT;
        switch ((int)this.payout){
            case 5:
                this.payout = 8;
                break;
            case 4:
                this.payout = 6;
                break;
            case 2:
            case 1:
            case 3:
                this.payout++;
                break;

        }
        this.cost *= RATE_COST;
    }
    public void init(){
        imageButton.addListener( new ClickListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			    // check for negative player money
                if (player.decMoney(cost) && getTime()>=100) {
                    buy(1);
                    time.time = 0;
                }
				return true;
			}
		});
        progressBar = new ProgressBar();
        getProgressBarButton().addListener( new ClickListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			    // check for negative player money
			    if (player.decMoney(cost) && getTime()>=100) {
                    buy(1);
                    time.time = 0;
                }
				return true;
			}
		});
        time = new Time();
        timer = new Timer();
        timer.schedule(new Helper(time), 0, timerTime*=RATE_TIMER);

    }
    public Vector2 getAbsCoords(){
        return imageButton.localToStageCoordinates(new Vector2(0,0));
    }
    // constantly called in render loop to check for payout
    public void timeCheck(){
        if (this.time.time  >= 100){
            time.time = 100;
            //player.incMoney(payout);
        }
    }
    // constructor
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
class Time {
    public int time = 0;
    public void increment(int amt){
        time += amt;
    }
    public void increment(){
        time++;
    }
}
class Helper extends TimerTask{
    private Time time;
    private boolean check = false;
    public Helper(Time time){
        this.time = time;
    }
    public void run()
    {
        if(!check){
            time.increment();
            check = true;
        }
        else check=false;
    }
}
