package input;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;
import states.State;

public class Controller
{
	private Handler handler;
	private float x, y; //actual position in space
	private float dispX, dispY; //where its drawn at on screen
	private float gridX, gridY; //where it's at on the grid
	
	public Controller(Handler handler)
	{
		this.handler = handler;
		this.x = 0;
		this.y = 0;
		this.dispX = 0;
		this.dispY = 0;
	}

	public void updatePosition()//so it selects the correct cell
	{
		//what grid tile its on
		this.gridX = (int) ((handler.getKeyManager().mX + handler.getCamera().getxOffset())/(handler.getWorld().CELL_SIZE));
		this.gridY = (int) ((handler.getKeyManager().mY + handler.getCamera().getyOffset())/(handler.getWorld().CELL_SIZE));
		//drawing it on the right place on the screen
		this.x = ((gridX - (handler.getCamera().getxOffset()/(handler.getWorld().CELL_SIZE))) * (handler.getWorld().CELL_SIZE));
		this.y = ((gridY - (handler.getCamera().getyOffset()/(handler.getWorld().CELL_SIZE))) * (handler.getWorld().CELL_SIZE));
	}
	
	private void getInput()
	{
		this.dispX = handler.getKeyManager().mX;
		this.dispY = handler.getKeyManager().mY;

		if(State.getState() == handler.getApplication().gameState)
		{
			if(handler.getKeyManager().leftClick)
			{
				handler.getWorld().placeCell((int) this.gridX, (int) this.gridY);
				handler.getKeyManager().leftClick = false;
			}

			if(handler.getKeyManager().rightClick)
			{
				handler.getKeyManager().rightClick = false;
			}

			if(handler.getKeyManager().space) //toggles if the world is running or not
			{
				handler.getWorld().setRunning(!handler.getWorld().getRunning());
				handler.getKeyManager().space = false;
			}
			if(handler.getKeyManager().rightArrow && !handler.getWorld().getRunning())//goes to the next generation
			{
				handler.getWorld().nextGeneration();
				handler.getKeyManager().rightArrow = false;
			}
			if(handler.getKeyManager().leftArrow && !handler.getWorld().getRunning())//goes to the previous generation
			{
				handler.getWorld().previousGeneration();
				handler.getKeyManager().leftArrow = false;
			}
			//zooming
			if(handler.getKeyManager().mouseWheelUp)//zoom in
			{
				handler.getWorld().CELL_SIZE++;
				handler.getKeyManager().mouseWheelUp = false;
			}
			if(handler.getKeyManager().mouseWheelDown)//zoom out
			{
				handler.getWorld().CELL_SIZE--;
				handler.getKeyManager().mouseWheelDown = false;
			}


			if(handler.getKeyManager().esc) //toggles if the world is running or not
			{
				State.setState(handler.getApplication().menuState);
				handler.getKeyManager().esc = false;
			}

			if(handler.getKeyManager().r) //randomizes the grid
			{
				handler.getWorld().randomGrid();
				handler.getKeyManager().r = false;
			}
			if(handler.getKeyManager().c) //clears the grid
			{
				handler.getWorld().clearGrid();
				handler.getKeyManager().c = false;
			}
			if(handler.getKeyManager().g) //toggles the grid view
			{
				handler.getWorld().gridView = !handler.getWorld().gridView;
				handler.getKeyManager().g = false;
			}

			//Moving the camera
			//WASD KEYS
			if(handler.getKeyManager().up && !handler.getKeyManager().down) handler.getCamera().move(0, -5);
			if(handler.getKeyManager().down && !handler.getKeyManager().up) handler.getCamera().move(0, 5);
			if(handler.getKeyManager().left && !handler.getKeyManager().right) handler.getCamera().move(-5, 0);
			if(handler.getKeyManager().right && !handler.getKeyManager().left) handler.getCamera().move(5, 0);
		}
	}

	public void tick()
	{
		getInput();
		updatePosition();
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, handler.getWorld().CELL_SIZE, handler.getWorld().CELL_SIZE);
	}
}