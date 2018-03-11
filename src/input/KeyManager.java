package input;

import java.awt.event.*;

import javax.swing.SwingUtilities;

public class KeyManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private boolean[] keys;
	public boolean up, down, left, right, space, enter, esc, back, shift, upArrow, downArrow, leftArrow, rightArrow, debugMode, leftClick, rightClick, middleClick, mouseWheelUp, mouseWheelDown, dragging;
	public boolean r, c, g;
	public int mX,mY,cX,cY,dX,dY;
	
	public KeyManager()
	{
		keys = new boolean[256];
	}
	
	public void tick()
	{
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
		if(e.getKeyCode() == KeyEvent.VK_F3)
			debugMode = true;
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			enter = true;
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			back = true;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			upArrow = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			downArrow = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			leftArrow = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightArrow = true;

		//toggle keys (dont put in key released)
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			esc = true;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			space = true;
		if(e.getKeyCode() == KeyEvent.VK_R)
			r = true;
		if(e.getKeyCode() == KeyEvent.VK_C)
			c = true;
		if(e.getKeyCode() == KeyEvent.VK_G)
			g = true;

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
		if(e.getKeyCode() == KeyEvent.VK_F3)
			debugMode = false;
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			enter = false;
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			back = false;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			upArrow = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			downArrow = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			leftArrow = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightArrow = false;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
	    dragging = true;
		dY = e.getY();
	    dX = e.getX();
		mY = e.getY();
		mX = e.getX();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
	      mY=e.getY();
	      mX=e.getX();		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
	      cY=e.getY();
	      cX=e.getX();
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			leftClick = true;
		}
		if(SwingUtilities.isRightMouseButton(e))
		{
			rightClick = true;
			//for right click camera movements
			cY = e.getY();
			cX = e.getX();
		}
		if(SwingUtilities.isMiddleMouseButton(e))
		{
			middleClick = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		dragging = false;
		if(SwingUtilities.isLeftMouseButton(e))
		{
			leftClick = false;
		}
		if(SwingUtilities.isRightMouseButton(e))
		{
			rightClick = false;
		}
		if(SwingUtilities.isMiddleMouseButton(e))
		{
			middleClick = false;
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e)
	{
	    int notches = e.getWheelRotation();
	    if (notches < 0)
	    {
	    	mouseWheelUp = true;
	    	mouseWheelDown = false;
	    } 
	    else 
	    {
	    	mouseWheelDown = true;
	    	mouseWheelUp = false;
	    }
	}

}