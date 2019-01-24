public class Weaponicon extends Sprite {
	private int cost = 500; //500 score to upgrade your weapon
	private int weaponslvl = 50; // 50 HP for 50 score 
	
	/////ADDD LOOADIMAGE TO ME!!!!!
	
	public Weaponicon(int x, int y) {
		super(x, y);
		initIcon();
	}
    private void initIcon() {
        loadImage("weaponsicon.png");
        getImageDimensions();
        setVisible(false);
    }
	public void upgradeCraft(Craft craft){ //All the collision detection stuff is handled in checkcollisions in board
		if(craft.score >= cost){
			craft.weaponslvl += 1;	
			System.out.print("Weapons upgraded.");
			craft.score -= cost;
			craft.fireDelay += 100;
		}

	}
}