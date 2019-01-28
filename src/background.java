
public class background extends Sprite {
	private final int INITIAL_X = 400;

    public background(int x, int y) {
        super(x, y);

        initbackground();
    }

    private void initbackground() {
        loadImage("icons/spacehuge.jpg");
       // getImageDimensions();
    }
}

