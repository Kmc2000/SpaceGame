import java.util.ArrayList; //excuse me java are you have stupid i already made this class and now i have to remake it with a stupid name???hello??

public class HealthIconn extends Sprite {
	private int cost = 100; //100 score
	private int health_benefit = 50; // 50 HP for 100 score 
	
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
			int math = craft.health + health_benefit;
			if(math > craft.max_health) { //No overhealing
				int temp_health_benefit = (math-craft.max_health);
				craft.health += temp_health_benefit;
				craft.score -= cost;
				return;
			}
			craft.health += health_benefit;	
			craft.score -= cost;
		}
		else if(craft.score >= (cost*3)) { //upgrade max health
			craft.max_health += 50;
			craft.health = craft.max_health;
			craft.score -= (cost*3);
		}

	}
}
