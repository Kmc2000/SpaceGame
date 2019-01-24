public class Parallax extends Sprite {
	public int scroll_speed = 3;

	public Parallax(int x, int y) {
		super(x, y);
		loadImage("starshuge.png");
		getImageDimensions();
		System.out.println("aa");
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	public void process(Board board) {
		x -= scroll_speed;
        if (this.x <= -1 * board.B_WIDTH) {
        	 
            // If it has, line it back up so that its left edge is
            // lined up to the right side of the other background image
            this.x = this.x + board.B_WIDTH * 2;
	}
	}
} 
