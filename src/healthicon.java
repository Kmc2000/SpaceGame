import java.util.ArrayList;

public class healthicon extends Sprite {
	private int cost = 500; //500 score
	private int health_benefit = 200; // 200 HP for 500 score 
	
	/////ADDD LOOADIMAGE TO ME!!!!!
	
	public healthicon(int x, int y) {
		super(x, y);
		initIcon();
	}
    private void initIcon() {
        loadImage("icons/healthicon.png");
        getImageDimensions();
        setVisible(false);
    }
	public void healCraft(Craft craft){
		if(craft.score >= cost){
			craft.health += health_benefit;	
			System.out.print("Repairs complete. You spent"+cost+"score to heal for"+health_benefit+"hit points");
		}

	}
}
