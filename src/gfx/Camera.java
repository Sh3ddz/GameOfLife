package gfx;

import main.Application;
import main.Handler;
import worlds.World;

public class Camera 
{
	private Handler handler;
	private float xOffset, yOffset;
	private int zoomLevel;
	
	public Camera(Application app, World world, Handler handler)
	{
		this.handler = handler;
		this.xOffset = 0;
		this.yOffset = 0;
		this.zoomLevel = 3;
	}
	
	public void move(float xAmt, float yAmt)
	{
		this.xOffset += xAmt;
		this.yOffset += yAmt;
	}
	
	public void setPosition(float x, float y)
	{
		this.xOffset = x;
		this.yOffset = y;
	}

	public float getxOffset() 
	{
		return xOffset;
	}

	public void setxOffset(float xOffset) 
	{
		this.xOffset = xOffset;
	}

	public float getyOffset()
	{
		return yOffset;
	}

	public void setyOffset(float yOffset) 
	{
		this.yOffset = yOffset;
	}
	
	public int getZoomLevel()
	{
		return this.zoomLevel;
	}
	
	public void setZoomLevel(int z)
	{
		if(z < 1)
			z = 1;
		if(z > 5)
			z = 5;
		if(zoomLevel < z)
		{
			for(int i = 0; i <= z - zoomLevel; i++)
			{
				zoomIn();
			}
		}
		else
			if(zoomLevel > z)
			{
				for(int i = 0; i <= zoomLevel - z; i++)
				{
					zoomOut();
				}
			}
	}
	
	//zooms towards the mouse position
	public void zoomIn()
	{
		if(zoomLevel < 5)
		{
			int mousePosX = handler.getKeyManager().mX;
			int mousePosY = handler.getKeyManager().mY;
			this.xOffset = (float) (xOffset *2) + (mousePosX);
			this.yOffset = (float) (yOffset *2) + (mousePosY);
			//uncomment this if you want it to zoom towards the center of the screen.
			//this.xOffset = (float) (xOffset *2) - (handler.getWidth()/2);
			//this.yOffset = (float) (yOffset *2) - (handler.getHeight()/2);
			this.zoomLevel++;
		}
	}
	
	//zooms away from the center of the screen
	public void zoomOut()
	{
		if(zoomLevel > 1)
		{
			this.xOffset = (float) (xOffset /2) - (handler.getWidth()/4);
			this.yOffset = (float) (yOffset /2) - (handler.getHeight()/4);
			this.zoomLevel--;
		}
	}
	
	public void zoomReset()
	{
		setZoomLevel(3);
	}
	
	public void cameraReset()
	{
		this.xOffset = 0;
		this.yOffset = 0;
		this.zoomLevel = 3;
	}
}
