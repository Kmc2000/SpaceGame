
public class Missile extends Sprite {

    private final int BOARD_WIDTH = 900;
    private final int MISSILE_SPEED = 8;
    public int damage = 50;
    public long startTime;
    public long endTime = System.currentTimeMillis(); //Avoid runtime on first ever fire
    public int fireDelay = 1000; //1000 ms = 1s
    public long currentTime = System.currentTimeMillis();
    public long nextfire = 1;
    public int health = 50; //better missiles can hit more targets before dying, 50 hp = 1 enemy killed

    public Missile(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {  //Try to fire   
    	loadImage("icons/missile.png");  
        getImageDimensions();	
    }

    public void deleteMissile(Craft craft) {
    	craft.missiles.remove(this);
    }

    public void move() {
        x += MISSILE_SPEED;
        }
    public void process(Craft craft) {		//Process runs every tick
    	if (x > BOARD_WIDTH) {
            vis = false;
            deleteMissile(craft);
    	}
    }
} #error OI, add in the new beam sprite when rapidfire is true!!!!