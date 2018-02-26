package states;

import java.awt.Graphics;

import input.Controller;
import main.Handler;
import worlds.World;

public class GameState extends State
{
	private World world;
	private Controller controller;
	
	public GameState(Handler handler)
	{
		super(handler);
		this.world = new World(handler);
		handler.setWorld(world);
		this.controller = handler.getController();
	}
	
	@Override
	public void tick()
	{
		controller.tick();
		world.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		world.render(g);
		controller.render(g);
	}

}
