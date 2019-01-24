
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    public Craft craft;
    public HealthIconn HealthIconn;
    public Weaponicon weaponicon;
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private boolean inwave = true; //Are we currently in a wave of combat? if not, then spawn the things for upgrades and spam more enemies.
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 700;
    private final int B_HEIGHT = 700;
    private final int DELAY = 15;
    private ArrayList <Missile> toDelete = new ArrayList<Missile>();
    private long lastspawn;
    private int spawndelay = 10000;
    private boolean stopspammingme = true;
    private int spawn_amt = 5;//start easy, get hard
    public int difficulty = 1; //increase by 0.2 every wave to avoid megadeath
    private ArrayList<Alien> alienstokill = new ArrayList<Alien>();
    
    private Image bg;

    private final int[][] pos = {
        {2380, 29}, {2500, 59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239},
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
    };

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setVisible(true);
        ingame = true;
        setBounds(0, 0, 700, 700);
        
        bg = Toolkit.getDefaultToolkit().createImage("icons/space.jpg");
        
        //setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);
        HealthIconn = new HealthIconn(50, 50); //FIX this doesn't load!
        HealthIconn.setVisible(false); //come back to this later
        weaponicon = new Weaponicon(150, 50); //FIX this doesn't load!
        weaponicon.setVisible(false); //come back to this later
        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();
        while (aliens.size() < spawn_amt) {
        	Random r = new Random();
        	int [] p = pos[r.nextInt(pos.length)];
        	aliens.add(new Alien(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(bg, 0, 0, null);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
    	if(HealthIconn.isVisible()) {
            g.drawImage(HealthIconn.getImage(), 50,50, this);   		
    	}
    	if(weaponicon.isVisible()) {
    		g.drawImage(weaponicon.getImage(), 150,50, this);   
    	}
        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                    this);
        }
        
        ArrayList<Missile> ms = craft.getMissiles();
        int msg = ms.size();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
    //    g.drawString("Missiles:" + msg,10,10);

        for (Missile m : ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
            ArrayList<AMissile> ams = a.getMissiles();
            for ( AMissile missile : ams ) {
            	if (missile.isVisible()) {
                    g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
                }
            }
        }
        for (Alien a : alienstokill) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
            ArrayList<AMissile> ams = a.getMissiles();
            for ( AMissile missile : ams ) {
            	if (missile.isVisible()) {
                    g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
                }
            }
        }
        g.setColor(Color.GREEN);
        g.drawString("Health: " + craft.health, 5, 15);
        g.setColor(Color.RED);  
        g.drawString("Aliens left in wave:" +aliens.size(), 5, 30);
        g.drawString("Score:" +craft.score, 5, 45);
     //   g.drawImage(background, 0, 0, this);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 3);
        String scr = "Your score: "+craft.score;
        g.setColor(Color.red);
        g.drawString(scr, (B_WIDTH - fm.stringWidth(scr)) / 2,
                (int) (B_HEIGHT / 2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(!inwave) {
    		long t;
        	t = System.currentTimeMillis();
        	if (lastspawn+spawndelay < t) {
        		initAliens();
            	inwave = true;
                HealthIconn.setVisible(false);
                weaponicon.setVisible(false);
                stopspammingme = false;
        	}
    	}
        inGame();
        updateCraft();
        updateMissiles();
        updateAliens();
        checkCollisions();
        cleanup();
        repaint();
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
        process();
    }

    private void process(){ //Import things that need to be called every tick go here
    	checkCollisions();
    	 for(Alien a : aliens){
        	 for (AMissile E : a.missiles) {
              	Rectangle r31 = craft.getBounds();
              	Rectangle r33 = E.getBounds();

              	if (r33.intersects(r31) && E.isVisible()) {
              		craft.health -= E.damage;
              		System.out.println(craft.health);
              		if(craft.health <= 0){
              			craft.setVisible(false);
                          ingame = false;
                      	}
                      	E.setVisible(false); //aliens now randomly die for no reason kek
                      }               		 
        	 }

        }
    }
    
    private void updateCraft() {

        if (craft.isVisible()) {
            craft.move();
        }
        if(craft.health <= 0){
        	ingame = false;
        }
        checkCollisions();
    }

    private void updateMissiles() {

        ArrayList<Missile> ms = craft.getMissiles();

        if(ms.size() > 0){
            for (int i = 0; i < ms.size(); i++) {
                Missile m = ms.get(i);
                if(m != null){
                	m.process(craft);
                }
                if (m.isVisible()) {
                    m.move();
                }
                else {
                	m = null;
                }
            }	
        }
        for(Alien a : aliens){
	        ArrayList<AMissile> ams = a.getMissiles();
	        if(ams.size() > 0){
	            for (int i = 0; i < ams.size(); i++) {
	                AMissile m = ams.get(i);
	                if(m != null){
	                	m.process(a);
	                }
	                if (m.isVisible()) {
	                    m.move();
	                }
	                else {
	                	m = null;
	                }
	            }	
	        }       	
        }
        for(Alien a : alienstokill){
	        ArrayList<AMissile> ams = a.getMissiles();
	        if(ams.size() > 0){
	            for (int i = 0; i < ams.size(); i++) {
	                AMissile m = ams.get(i);
	                if(m != null){
	                	m.process(a);
	                }
	                if (m.isVisible()) {
	                    m.move();
	                }
	                else {
	                	m = null;
	                }
	            }	
	        }       	
        }


    }

    private void updateAliens() {
        if (aliens.isEmpty()) {
            //ingame = false;
        	inwave = false;
        	handle_wave(); //Aliens dead! allow you to buy upgrades now
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            if (aliens.get(i).isVisible()) {
                aliens.get(i).move(this);
                aliens.get(i).speed = 1+difficulty;
            } else {
        		Alien target = aliens.get(i);
        		alienstokill.add(target);
        		target.missiles = aliens.get(i).missiles;
            	aliens.remove(i);	
            	}
                
            }
        }

    public void checkCollisions() {
        Rectangle r3 = craft.getBounds();
        Rectangle hp = HealthIconn.getBounds();
        Rectangle wp = weaponicon.getBounds();
        if(HealthIconn.isVisible()) {
        	if(hp.intersects(r3)) {
        		HealthIconn.healCraft(craft);
        	}
        }
        if(weaponicon.isVisible()) {
        	if(wp.intersects(r3)) {
        		weaponicon.upgradeCraft(craft);
        	}
        }
        for (Alien alien : aliens) {
            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {
              //  craft.setVisible(false);
            	alien.setVisible(false);
            	alien.qdel(craft);
            	craft.health -= 50;
            }
        }
        
        for (Missile m : craft.getMissiles()) {

            Rectangle r1 = m.getBounds();

            for (Alien alien : aliens) {
                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                	alien.health -= m.damage;
                	if(alien.health < 0){
                		alien.setVisible(false);
                		alien.qdel(craft);
                	}
                	m.health -= 50;
                	if(m.health <= 0) {
                    	m.setVisible(false); //Better missiles hit more enemies before dying.
                    	toDelete.add(m);	
                	}

                }
               
               
            }
        }
    }
    
    private void cleanup() {
    	while (!toDelete.isEmpty())
        {
        	toDelete.get(0).deleteMissile(craft);
        	toDelete.remove(0);
        }
    }
    private void handle_wave() {
    	if(inwave) {
    		return;
    	} //Thanks stackexchange
    	HealthIconn.setVisible(true);
    	weaponicon.setVisible(true);
    	if(!stopspammingme) {
        	lastspawn = System.currentTimeMillis();
    		stopspammingme = true;
    		difficulty += 0.2;
    		spawn_amt += 5;
    	}


    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}