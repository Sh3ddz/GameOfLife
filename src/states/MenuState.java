package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import input.Controller;
import main.Handler;

public class MenuState extends State
{
	private Controller controller;
	
	
	private int currentChoice = 0;
	private String[] options = {
			"START",
			"HELP",
			"QUIT",
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	private boolean inHelpMenu;
	
	public MenuState(Handler handler)
	{
		super(handler);
		this.controller = handler.getController();
		
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 56);
		
		font = new Font("Arial", Font.PLAIN, 24);
	}
	
	public int getCurrentChoice()
	{
		return this.currentChoice;
	}
	
	public void setCurrentChoice(int currentChoice)
	{
		this.currentChoice = currentChoice;
	}
	
	private void select()
	{
		if(currentChoice == 0)
		{
			setState(handler.getApplication().gameState);
		}
		if(currentChoice == 1)
		{
			inHelpMenu = true;
		}
		if(currentChoice == 2)
		{
			System.exit(0);
		}
	}
	
	private void renderMenu(Graphics g)
	{
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Conway's Game of Life!", 20, 140);
		
		//draw menu options
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.drawString("(Use the arrow keys to navigate the menu!)", 200, 240);

		g.setFont(font);
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			
			g.drawString(options[i], 290, 280 + i * 30);
		}
	}
	
	private void renderHelpMenu(Graphics g)
	{
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Controls:", 160, 60);
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		g.drawString("Welcome to conway's game of life!", 50, 100);
		g.drawString("Click on a grid space to place or remove a cell from it", 50, 130);
		g.drawString("Press Spacebar to start or pause the simulation", 50, 160);
		g.drawString("Press C to clear the grid", 50, 190);
		g.drawString("Press R to randomize the grid", 50, 220);
		g.drawString("Press G to toggle grid view (the gray squares)", 50, 250);
		g.setColor(Color.RED);
		g.drawString("Press Backspace or ESC to return to the main menu", 50, 350);


	}
	
	public void getInput()
	{
		if(!inHelpMenu)
		{
			if(handler.getKeyManager().esc)
			{
				State.setState(handler.getApplication().gameState);
				handler.getKeyManager().esc = false;
			}
			if(handler.getKeyManager().enter)
			{
				select();
				handler.getKeyManager().enter = false;
			}
			if(handler.getKeyManager().upArrow)
			{
				currentChoice--;
				if(currentChoice == -1)
				{
					currentChoice = options.length - 1;
				}
				handler.getKeyManager().upArrow = false;
			}
			if(handler.getKeyManager().downArrow)
			{
				currentChoice++;
				if(currentChoice > options.length - 1)
				{
					currentChoice = 0;
				}
				handler.getKeyManager().downArrow = false;
			}
		}
		else
		{
			if(handler.getKeyManager().back || handler.getKeyManager().esc)
				inHelpMenu = false;
		}
	}
	
	@Override
	public void tick()
	{
		controller.tick();
		getInput();
	}

	@Override
	public void render(Graphics g) 
	{
		if(inHelpMenu)
			renderHelpMenu(g);
		else
			renderMenu(g);
		controller.render(g);
	}
}
