import java.awt.* ;
import java.awt.event.KeyEvent;

//comments
public class Character {
	public static final int XSPEED = 5;  //set the speed of the character in x direction  
	public static final int YSPEED = 5;  //set the speed of the character in y direction
	public static final int WIDTH = 30;  //set the width of the character
	public static final int HEIGHT = 30; //set the height of the character 	
	
	
	private int x, y;
	OfficeClient tc;
	
	private boolean bL = false, bU = false, bR = false, bD = false;
	
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Character(int x, int y, OfficeClient tc){
		this(x, y);
		this.tc = tc;
	}
	
    public enum Direction {
        LEFT, UP, RIGHT, DOWN, STOP
    }
    
    private Direction dir = Direction.STOP;
    private Direction barrelDir = Direction.DOWN;  //this is the direction of the barrel
    
    void move(){
    	switch(dir){
    	case LEFT:
    		x -= XSPEED;
    		break;
    	case UP:
    		y -=YSPEED;
    		break;
    	case RIGHT:
    		x += XSPEED;
    		break;
    	case DOWN:
    		y += YSPEED;
    		break;
    	case STOP:
    		break;
    	}
    	if(this.dir != Direction.STOP)   // as long as the character moves, the direction of the barrel is the same as that of character
    		this.barrelDir = this.dir;
    }
    

	public void draw(Graphics g){ 
		Color c = g.getColor();  
		g.setColor(Color.white); 
		g.fillOval(x, y, WIDTH, HEIGHT);  //paint the circle which represents the player	
		g.setColor(c);	
		move();
	    float lineWidth = 3.0f;     // set the width of the barrel
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		switch(barrelDir){      // paint the barrel of our character
		case LEFT:
			((Graphics2D)g).drawLine(x + Character.WIDTH/2, y + Character.HEIGHT/2, x, y + Character.HEIGHT/2);
			break;
		case UP:
			((Graphics2D)g).drawLine(x + Character.WIDTH/2, y + Character.HEIGHT/2, x + Character.WIDTH/2, y);
			break;
		case RIGHT:
			((Graphics2D)g).drawLine(x + Character.WIDTH/2, y + Character.HEIGHT/2, x + Character.WIDTH, y + Character.HEIGHT/2);
			break;
		case DOWN:
			((Graphics2D)g).drawLine(x + Character.WIDTH/2, y + Character.HEIGHT/2, x + Character.WIDTH/2, y + Character.HEIGHT);
			break;
		}
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		}
		locateDirection();
	}
	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.LEFT;
		else if(!bL && bU && !bR && !bD) dir = Direction.UP;
		else if(!bL && !bU && bR && !bD) dir = Direction.RIGHT;
		else if(!bL && !bU && !bR && bD) dir = Direction.DOWN;  
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_SPACE:   //every time the "space" is released, one missile will be fired; 
			fire();
		    break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false ;
			break;
		}
		locateDirection();
	}
	public Missile fire(){
		int x = this.x + Character.WIDTH/2 - Missile.WIDTH/2;   //set the missile fired from the center of our Character
		int y = this.y + Character.HEIGHT/2 - Missile.WIDTH/2;
		Missile m = new Missile(x, y, barrelDir ); // pass the position  and the direction of the barrel to the missile 
		tc.missiles.add(m);
		return m;  
	}

	
}
