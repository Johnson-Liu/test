import java.awt.* ;

public class Missile {
	public static final int XSPEED = 10;  // set the speed of the missile in x direction 
	public static final int YSPEED = 10;  // set the speed of the missile in y direction
	public static final int WIDTH = 15;  //set the width of the character
	public static final int HEIGHT = 15; //set the height of the character 	
	
	int x, y;
	
	Character.Direction dir;
	
	public Missile(int x, int y, Character.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);   // the color of the missile
		g.fillOval(x, y, WIDTH, HEIGHT);    // the area  of the missile
		g.setColor(c);		
		move();
	}
	
	private void move() {
		switch(dir) {
		case LEFT:
			x -= XSPEED;
			break;
		case UP:
			y -= YSPEED;
			break;
		case RIGHT:
			x += XSPEED;
			break;
		case DOWN:
			y += YSPEED;
			break;

		}
		
	}
	
}