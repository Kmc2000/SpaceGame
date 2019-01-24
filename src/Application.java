import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Application extends JFrame {
    
    public Application() {

        initUI();
    }

    private void initUI() {
        setSize(700, 700);
        setTitle("Spacegame the game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    	//getContentPane().add(bg);
    	
        System.out.println("lrefskm,x");
       	getContentPane().add(new Board());
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