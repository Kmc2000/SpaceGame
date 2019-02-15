import java.util.ArrayList;

public class rapidfirepowerup extends Sprite {
	public int speed = 1;
	public boolean poweredup = false; //Have we given the craft a powerup? if so, count-down to remove it.
    public int poweruptime = 7000; //(ms) How long does the craft get a powerup for?
    private long lastpowerup = 0; //used in delay code
	
	public rapidfirepowerup(int x, int y) {
		super(x, y);
		initIcon();
	}
    private void initIcon() {
        loadImage("icons/weaponicon.png");
        getImageDimensions();
        setVisible(false);
    }
	public void powerup(Craft craft){ //All the collision detection stuff is handled in checkcollisions in board
		if(!isVisible()) {
			return;
		}
		if(!poweredup) {
			craft.rapidfire = true;
			poweredup = true;
			lastpowerup = System.currentTimeMillis();
		}

	}
	public void move(Craft craft) {
    	x -= speed;
    	long t = System.currentTimeMillis();
    	if (lastpowerup+poweruptime < t) { //time's up
        	craft.rapidfire = false;
        	poweredup = false;
    	}
    	y = 200;
    	if(x > 900) {
    		x -= 3;
    	}
        if (x < 0) {
            x = 900;
            setVisible(false);
        }

	//	y += 1;
	}
}
