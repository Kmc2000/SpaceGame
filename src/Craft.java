
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {

    private int dx; //X coords
    private int dy;
    private int speed = 5; //this will make it super fast
    private long t;
    public int fireDelay = 200; //100 is ideal
    protected ArrayList<Missile> missiles;
    private long lastfired = 0; //used in delay code
    public int health = 1000;
    public int score = 0; //How much score for killing enemies
    public int max_health = 1000; //Max health
    public int weaponslvl = 1; //Weapon upgrade levels make your weapons stronger and hit harder

    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        missiles = new ArrayList<>();
        loadImage("craft.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public void CheckForFirePress(KeyEvent e) {
    	int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -speed;
            CheckForFirePress(e);
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = speed;
            CheckForFirePress(e);
        }

        if (key == KeyEvent.VK_UP) {
            dy = -speed;
            CheckForFirePress(e);
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = speed;
            CheckForFirePress(e);
        }
    }

    public void fire() {
    	t = System.currentTimeMillis();
    	if (lastfired+fireDelay < t) {
    		switch(weaponslvl) {
    		case(1):
        		missiles.add(new Missile(x + width, y + height / 2));
        		lastfired = System.currentTimeMillis();
        		return;
    		case(2):
        		missiles.add(new megamissile(x + width, y + height / 2));
    			lastfired = System.currentTimeMillis();
    			return;
    		default:
        		missiles.add(new supermissile(x + width, y + height / 2));
        	    lastfired = System.currentTimeMillis();
        	    return;
    		}

    	}
    }

    public void print(String s) {
    	System.out.println(s);
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}