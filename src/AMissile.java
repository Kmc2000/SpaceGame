

public class AMissile extends Sprite {

    private final int BOARD_WIDTH = 500;
    private final int MISSILE_SPEED = 7;
    public int damage = 50;
    public long startTime;
    public long endTime = System.currentTimeMillis(); //Avoid runtime on first ever fire
    public int fireDelay = 1000; //1000 ms = 1s
    public long currentTime = System.currentTimeMillis();
    public long nextfire = 1;

    public AMissile(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {  //Try to fire   
    	loadImage("icons/amissile.png");  
        getImageDimensions();	
    }

    public void deleteMissile(Board board) {
    	board.missiles.remove(this);
    }

    public void move() {
        x -= MISSILE_SPEED;
        }
    public void process() {		//Process runs every tick
    	return;
    //	if (x > BOARD_WIDTH) {
      //      vis = false;
      //      deleteMissile(Alien);
    //	}
    }
}