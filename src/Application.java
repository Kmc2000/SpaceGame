import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Application extends JFrame {
    public Board board;
    public Application() {

        initUI();
    }

    private void initUI() {
        setSize(800, 800);
        setResizable(false);
        setTitle("Trench Run");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    	//getContentPane().add(bg);
    	JButton play = new JButton("play");
    	play.setBounds(0, 0, 100, 100);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    	play.setAlignmentX(CENTER_ALIGNMENT);
    	play.setAlignmentY(BOTTOM_ALIGNMENT);
        play.setLocation(x, y);
        play.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    board.startgame();
                    play.setVisible(false);
                }
        });
        play.setVisible(true);
        getContentPane().add(play);
        Board game = new Board();
        board = game;
        getContentPane().add(game);
        revalidate();
    }    
    
    private Container meme(ImageIcon imageIcon) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}

//http://zetcode.com/tutorials/javagamestutorial/basics/ CTRL C CTRL V