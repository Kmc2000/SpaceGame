
public class supermissile extends Missile { //A stronger and faster missile with a larger hitbox

    private final int BOARD_WIDTH = 800;
    private final int MISSILE_SPEED = 6;
    public int damage = 1000;
    public long startTime;
    public long endTime = System.currentTimeMillis(); //Avoid runtime on first ever fire
    public int fireDelay = 1000; //1000 ms = 1s
    public long currentTime = System.currentTimeMillis();
    public long nextfire = 1;
    public int health = 200;

    public supermissile(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {  //Try to fire  
    	loadImage("icons/mediummissile.png");  
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
}