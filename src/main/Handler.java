package main;

import gfx.Camera;
import input.Controller;
import input.KeyManager;
import worlds.World;

public class Handler 
{
	private Application application;
	private Controller controller;
	
	private World world;
	
	public Handler(Application app)
	{
		this.application = app;
	}
	
	public Camera getCamera()
	{
		return application.getCamera();
	}
	
	public KeyManager getKeyManager()
	{
		return application.getKeyManager();
	}
	
	public int getWidth()
	{
		return application.getWidth();
	}
	
	public int getHeight()
	{
		return application.getHeight();
	}
	
	public Application getApplication() 
	{
		return application;
	}

	public void setGame(Application application) 
	{
		this.application = application;
	}
	
	public World getWorld()
	{
		return this.world;
	}
	
	public void setWorld(World world)
	{
		this.world = world;
	}

	public Controller getController()
	{
		return this.controller;
	}
	
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	//some helper methods.
	public int randomWithRange(int min, int max)
	{ 
		int range = (max - min) + 1; 
		return (int)(Math.random() * range) + min; 
	}
	
	public int distform(int x, int y, int x1, int y1)
	{
	   return ((int) Math.sqrt((Math.pow((x1-x),2))+(Math.pow((y1-y),2))));
	}
	
	public float distform(float x, float y, float x1, float y1)
	{
	   return ((float) Math.sqrt((Math.pow((x1-x),2))+(Math.pow((y1-y),2))));
	}
}