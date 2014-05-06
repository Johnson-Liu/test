import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;



public class OfficeClient extends Frame{
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	

	Character player = new Character(50, 50, this);
	List<Missile> missiles = new ArrayList<Missile>();
	
	

	Image offscreenImage = null;
 
	
	public void paint(Graphics g){
		for(int i=0; i< missiles.size(); i++){
			Missile m =missiles.get(i);
			m.draw(g);
		}
		player.draw(g);
	}
	 
	public void update(Graphics g) {   // solve the problem of twinkling
		 if(offscreenImage == null){
			 offscreenImage  = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		 }
		 Graphics goffScreen = offscreenImage.getGraphics();
		 Color c = goffScreen.getColor();
		 goffScreen.setColor(Color.BLACK);
		 goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		 goffScreen.setColor(c);		 
		 paint(goffScreen);
		 g.drawImage(offscreenImage, 0, 0, null);
	}

	
	public void lauchFrame() {
		this.setLocation(400, 300); //set the position of the window. 400 to left side; 300 to up side;
		this.setSize(GAME_WIDTH, GAME_HEIGHT);  // set the size of the window, width is 800, height is 600;
		this.setTitle("OfficeWar From India");
		this.addKeyListener(new KeyMonitor());
		this.addWindowListener(new WindowAdapter(){  // set the window which can be closed
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}	
		}); 
		this.setResizable(false);  //set the size of the window fixed		
		this.setBackground(Color.BLACK);  //set the background color as black
		this.addKeyListener(new KeyMonitor());
		setVisible(true);
		new Thread(new PaintThread()).start(); // Start the new thread
	}

	public static void main(String[] args) {
		OfficeClient tc = new OfficeClient();  
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable {  // the thread class
		public void run(){
			while(true){
				repaint();
				try{
					Thread.sleep(50);  // the circle will be repainted every 100 ms
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	private class KeyMonitor extends KeyAdapter{

		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
			
	
		
	}
}
