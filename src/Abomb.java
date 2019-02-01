public class Abomb extends AMissile {

	private final int BOARD_WIDTH = 500;
    private final int MISSILE_SPEED = 3;
    public int damage = 50;
    public long startTime;
    public long endTime = System.currentTimeMillis(); //Avoid runtime on first ever fire
    public int fireDelay = 1000; //1000 ms = 1s
    public long currentTime = System.currentTimeMillis();
    public long nextfire = 1;
    public int explodetimer = 150; //N ms to explode after being fired
    public boolean exploded = false;

    public Abomb(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {  //Try to fire   
    	loadImage("icons/ABomb.png");  
        getImageDimensions();	
    }

    public void deleteMissile(Board board) {
    	board.bombs.remove(this);
    }

    public void move() {//fix this 
        explodetimer --; //KaBOOOM
        x -= MISSILE_SPEED;
        if(explodetimer <= 0) {
        	explode();
        }
        }
    public void explode() {		//Process runs every tick
    	setVisible(false);
    	return;
    }
}
