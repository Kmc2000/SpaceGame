
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Craft extends Sprite {

    private int dx; //X coords
    private int dy;
    private int max_acceleration = 4; //LUDCRIOUS acceleration
    private long t;
    public int fireDelay = 75; //100 is ideal
    protected ArrayList<Missile> missiles;
    private long lastfired = 0; //used in delay code
    public int health = 200;
    public int score = 0; //How much score for killing enemies
    public int max_health = 200; //Max health
    public int damage = 50;
    public int weaponslvl = 1; //Weapon upgrade levels make your weapons stronger and hit harder
    protected ArrayList<Character> pressed = new ArrayList<Character>(); //This counts the missiles that the board handles, the craft handles its own. This stops missiles deleting when the aliens die.
    private boolean firing = false; //Space down? fire, this should allow you to move and shoot at the same time
    public boolean rapidfire = false; //used by rapidfire powerup
    public boolean newtonian_mode = false; //Want to use newtonian movement?
    
    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        missiles = new ArrayList<>();
        loadImage("icons/playership.png");
        getImageDimensions();
    }

    public void move() {
    	if(firing) {
    		fire();
    	}
    	if(dx >= max_acceleration) {
    		dx = max_acceleration;
    	}
    	if(dy >= max_acceleration) {
    		dy = max_acceleration;
    	}
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
    
    public synchronized void keyPressed(KeyEvent e) { //Shit's broke FIX IT
    	if(dx >= max_acceleration) {
    		dx = max_acceleration;
    	}
    	if(dy >= max_acceleration) {
    		dy = max_acceleration;
    	}
        int key = e.getKeyCode();
        	
        if (key == KeyEvent.VK_SPACE) {
            fire();
            firing = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -max_acceleration;
            CheckForFirePress(e);
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = max_acceleration;
            CheckForFirePress(e);
        }

        if (key == KeyEvent.VK_UP) {
            dy = -max_acceleration;
            CheckForFirePress(e);
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = max_acceleration;
            CheckForFirePress(e);
        }
    }

    public void fire() {
    	t = System.currentTimeMillis();
    	if (lastfired+fireDelay < t || rapidfire) {
    		switch(weaponslvl) {
    		case(1):
        		missiles.add(new Missile(x + width, y + height / 2));
        		lastfired = System.currentTimeMillis();
        		damage = 50;
        		return;
    		case(2):
        		missiles.add(new megamissile(x + width, y + height / 2));
    			lastfired = System.currentTimeMillis();
    			damage = 600;
    			return;
    		default:
        		missiles.add(new supermissile(x + width, y + height / 2));
        	    lastfired = System.currentTimeMillis();
        	    damage = 1000;
        	    return;
    		}

    	}
    }

    public void print(String s) {
    	System.out.println(s);
    }
    
    public synchronized void keyReleased(KeyEvent e) {
    	if(newtonian_mode) {
    		return;
    	}
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
        if (key == KeyEvent.VK_SPACE) {
            firing = false;
        }
    }
}