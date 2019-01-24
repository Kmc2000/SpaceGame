import java.util.ArrayList; //excuse me java are you have stupid i already made this class and now i have to remake it with a stupid name???hello??

public class HealthIconn extends Sprite {
	private int cost = 50; //50 score
	private int health_benefit = 50; // 50 HP for 50 score 
	
	/////ADDD LOOADIMAGE TO ME!!!!!
	
	public HealthIconn(int x, int y) {
		super(x, y);
		initIcon();
	}
    private void initIcon() {
        loadImage("healthicon.png");
        getImageDimensions();
        setVisible(false);
    }
	public void healCraft(Craft craft){ //All the collision detection stuff is handled in checkcollisions in board
		if(craft.score >= cost && craft.health <= craft.max_health){
			craft.health += health_benefit;	
			System.out.print("Repairs complete. You spent"+cost+"score to heal for"+health_benefit+"hit points");
			craft.score -= cost;
		}

	}
}
