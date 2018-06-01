package my.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.EnumMap;
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
    private BitmapFont bitmapFont;
    private boolean autobuyer;

    private ProgressBar progressBar;
    private EnumMap<labels,GlyphLayout> labelMap = new EnumMap<labels, GlyphLayout>(labels.class);

    public enum labels {NAME,COST,QUANTITY,PAYOUT};

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
    //TODO: Make it work for all other labels
    public void updateLabels(){
        String midfix="";
        String prefix="";
        for(labels label: labels.values()){
            if(label==labels.COST || label==labels.PAYOUT){
                midfix=" $";
                prefix=label.toString().toLowerCase();
            }
            else if (label==labels.NAME){
                midfix="";
            }
            else {
                midfix = ": ";
                prefix=label.toString().toLowerCase();
            }
            labelMap.put(label,(new GlyphLayout(bitmapFont,prefix+midfix+stringifyLabelValue(label))));
        }
    }
    // called when user presses on button
    public void buy(int amt){
        this.quantity += amt;
        this.payout *= RATE_PAYOUT;
        this.cost*= RATE_COST;
        updateLabels();

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
    }

    private void payPlayerPayout(){
        player.incMoney(this.payout);
    }
    private String stringifyLabelValue(labels label){
        String ans = "";
        switch (label){
            case QUANTITY:
                ans+=this.quantity;
                break;
            case PAYOUT:
                ans+=this.payout;
                break;
            case NAME:
                ans+=this.name;
                break;
            case COST:
                ans+=this.cost;
                break;
        }
        return ans;
    }
    private void initLabelMap(){
        updateLabels();
    }
    public float properX(labels label){
        return (Gdx.graphics.getWidth()-labelMap.get(label).width)/2 + getPadding(label)[0];
    }
    public float properY(labels label){
        return getAbsCoords().y+imageButton.getHeight()/2+labelMap.get(label).height + getPadding(label)[1];
    }
    public GlyphLayout getGlyphLayout(labels label){
        return labelMap.get(label);
    }

    public Vector2 getAbsCoords(){
        return imageButton.localToStageCoordinates(new Vector2(0,0));
    }
    // constantly called in render loop to check for payout
    public void timeCheck(){
        if (this.time.time  >= 100){
            if (autobuyer && quantity > 0){
                time.time = 0;
                payPlayerPayout();
            }
            else time.time = 100;
        }
    }
    // For button label padding
    public int[] getPadding(labels label){
        int[] paddingXY = {0,0};
        switch(label){
            case COST:
                paddingXY[0]=(int)(-1*imageButton.getWidth()/2 + getGlyphLayout(label).width/2) + 5;
                paddingXY[1]=(int)(-1*imageButton.getHeight()/2) + 10;
                break;
            case NAME:
                paddingXY[0]=0;paddingXY[1]=0;
                break;
            case PAYOUT:
                paddingXY[0]=(int)(imageButton.getWidth()/2 - getGlyphLayout(label).width/2) - 15;
                paddingXY[1]=(int)(-1*imageButton.getHeight()/2) + 10;
                break;
            case QUANTITY:
                paddingXY[0]=(int)(getGlyphLayout(labels.NAME).width + 5);
                paddingXY[1]=0;
                break;
        }
        return paddingXY;
    }
    public float getTotalHeight(){
        return imageButton.getHeight() + progressBar.getHeight();
    }
    public ImageButton getImageButton(){
        return imageButton;
    }
    public void init(){
        ClickListener clickListenerMainButton = new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                // check for negative player money
                if (player.canDecMoney(cost)){// && getTime()>=100) {
                    player.decMoney(cost);
                    buy(1);
                    //time.time = 0;
                }
                return true;
            }
        };
        // Click => get payout
        ClickListener clickListenerProgressButton = new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (getTime()>=100 && quantity > 0) {
                    payPlayerPayout();
                    time.time = 0;
                }
                return true;
            }
        };
        progressBar = new ProgressBar();
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(img));
        imageButton = new ImageButton(drawable);
        imageButton.addListener(clickListenerMainButton);
        getProgressBarButton().addListener(clickListenerProgressButton);
        time = new Time();
        timer = new Timer();
        timer.schedule(new Helper(time), 0, timerTime*=RATE_TIMER);
        labelMap.put(labels.COST, (new GlyphLayout(bitmapFont,"Cost: $"+this.cost)));
        autobuyer = true;
    }
    // constructor
    public Business(String name, int cost, Texture img, int quantity, int payout, Player player, BitmapFont bmf){
        this.name = name;
        this.cost = cost;
        this.img = img;
        this.quantity = quantity;
        this.payout = payout;
        this.player = player;
        this.bitmapFont = bmf;

        init();
        initLabelMap();

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
