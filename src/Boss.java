import java.util.Random;

public class Boss extends Alien {
    
    public Boss(int x, int y) {
        super(x, y);
        initBoss();
    }

    private void initBoss() {
    	health = 5000;
        max_health = 5000;
        scoreaward = 500;
        speed = 3; //he moves up and down not to the left
        isboss = true;
        difficulty = 50;// fire loads
        loadImage("icons/alieninterceptor.png");
        getImageDimensions();
        this.health = this.max_health; //He starts with the correct HP but as soon as it's hit the HP drops to 200. Hmm.
    }

    @Override
    public void move(Board board) {
        Random rand = new Random();
        boolean changed = false;
        int f = rand.nextInt(50 - board.difficulty);
        if ( f == 0 ) { //FIX ME 
        	fire(board);
        }
        if (y >= 750 && !changed) {
        	speed = -2;
        	changed = true;
        }
        else if (y <= 0 && !changed){
        	speed = 2;
        	changed = true;
        }
        y += speed;
    }

    public void qdel(Craft craft) {
    	craft.score += scoreaward;
    }
}