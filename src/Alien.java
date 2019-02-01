import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Alien extends Sprite {
//Well it seems that the mighty eclipse just doesn't want to  load a bunch of my classes which naturally breaks everything..
    private final int INITIAL_X = 400;
    public int health = 0;
    public int max_health = 200;
    public int scoreaward = 50;
    public int speed = 1; //start suuuper slow
    public boolean isboss = false;
    public int difficulty = 100;
    
    public Alien(int x, int y) {
        super(x, y);
        initAlien();
    }

    private void initAlien() {
        loadImage("icons/alienwarrior.png");
        getImageDimensions();
        Random rand = new Random();
        int s = rand.nextInt(5);//fix me
        speed = s; //make some super fast ones and some super slow ones
        health = max_health;
    }

    public void move(Board board) {
        if (x < 0) {
            x = 700;
            Random xd = new Random();
            int meme = xd.nextInt(500);
            y = meme;
        }
        if(x >= 700) {
        	speed = 3;
        }
        Random rand = new Random();
        int meme = rand.nextInt(20-board.difficulty); //20 - 1 = 19, and it has to be 0 or 1 to wiggle around
        switch(meme){
        	case(0):
        		y+=rand.nextInt(10);
        		break;
        	case(1):
        		y-=rand.nextInt(10);
        		break;
        	default:
        		break;
        		//y+=rand.nextInt(1);
        }
      //  y += rand.nextInt(3);
        x -= speed;
        int f = rand.nextInt(difficulty - board.difficulty);
        if ( f == 0 ) { //FIX ME 
        	fire(board);
        }
    }
    public void fire(Board board){
        Random rand = new Random();
        int firetype = rand.nextInt(2);
        if(firetype == 1) { //choose either a bomb or a missile
        	board.bombs.add(new Abomb(x + width, y + height/2));
        }
        else {
        	board.missiles.add(new AMissile(x + width, y + height / 2));
        }
    }
    public ArrayList<AMissile> getMissiles(Board board) {
        return board.missiles;
    }
    public ArrayList<Abomb> getbombs(Board board) {
        return board.bombs;
    }
    
    public void qdel(Craft craft) {
    	craft.score += scoreaward;
    }
}