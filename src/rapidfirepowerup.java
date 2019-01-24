import java.util.ArrayList;

public class rapidfirepowerup extends Sprite {
	public int speed = 1;
    
	public rapidfirepowerup(int x, int y) {
		super(x, y);
		initIcon();
	}
    private void initIcon() {
        loadImage("weaponicon.png");
        getImageDimensions();
        setVisible(false);
    }
	public void powerup(Craft craft){ //All the collision detection stuff is handled in checkcollisions in board
		if(!isVisible()) {
			return;
		}
		craft.rapidfire = true; //except it doesnt work omegalul
		System.out.print("powerup");
	}
	public void move(Craft craft) {
    	x -= speed;
    	//y -= (int) Math.sin(x);
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
