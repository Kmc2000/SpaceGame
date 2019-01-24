public class ParallaxNebula extends Sprite {
	public int scroll_speed = 2;

	public ParallaxNebula(int x, int y) {
		super(x, y);
		loadImage("nubulaehuge.png");
		getImageDimensions();
		System.out.println("aa");
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	public void process() {
		x -= scroll_speed;
		if(x <= -800) {
			x = 800;
		}
			
	}

}
