public class Abomb extends AMissile {

	private final int BOARD_WIDTH = 500;
    private final int MISSILE_SPEED = 7;
    public int damage = 50;
    public long startTime;
    public long endTime = System.currentTimeMillis(); //Avoid runtime on first ever fire
    public int fireDelay = 1000; //1000 ms = 1s
    public long currentTime = System.currentTimeMillis();
    public long nextfire = 1;
    public int explodetimer = 150; //N ms to explode after being fired

    public Abomb(int x, int y) {
        super(x, y);
        
        initMissile();
    }
    
    private void initMissile() {  //Try to fire   
    	loadImage("icons/amissile.png");  
        getImageDimensions();	
    }

    public void deleteMissile(Alien Alien) {
    	Alien.bombs.remove(this);
    }

    public void move() {//fix this 
        x -= MISSILE_SPEED;
        explodetimer --; //KaBOOOM
        System.out.println(explodetimer);
        if(explodetimer <= 0) {
        	explode();
        }
        }
    public void explode() {		//Process runs every tick
    	loadImage("icons/ABomb.png");  
        getImageDimensions();
        System.out.print("boom");
    	return;
    }
}
