package worlds;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;

public class World
{
	private Handler handler;
	private boolean running = false;
	public boolean gridView = true;

	private int width, height;
	private int[][] grid;
	public final int CELL_SIZE = 10;

	public World(Handler handler)
	{
		this.handler = handler;
		init();
	}

	private void init()
	{
		this.width = handler.getWidth()/CELL_SIZE;
		this.height = handler.getHeight()/CELL_SIZE;
		this.grid = new int[width][height];
		for(int y = 0; y < grid[0].length; y++)
		{
			for(int x = 0; x < grid.length; x++)
			{
				grid[x][y] = handler.randomWithRange(0,1);
			}
		}
	}

	public void randomGrid()
	{
		for(int y = 0; y < grid[0].length; y++)
		{
			for(int x = 0; x < grid.length; x++)
			{
				grid[x][y] = handler.randomWithRange(0,1);
			}
		}
	}

	public void clearGrid()
	{
		this.grid = new int[width][height];
	}

	public void placeCell(int gridX, int gridY)
	{
		if(gridX < 0 || gridX >= width)
			return;
		if(gridY < 0 || gridY >= height)
			return;

		if(grid[gridX][gridY] == 0)
			grid[gridX][gridY] = 1;
		else
			if(grid[gridX][gridY] == 1)
				grid[gridX][gridY] = 0;
	}

	private void nextGeneration()
	{
		int[][] nextGen = new int[width][height];

		for(int y = 0; y < grid[0].length; y++)
		{
			for(int x = 0; x < grid.length; x++)
			{
				//counting the neighbors
				int aliveNeighbours = 0;
				for(int i = -1; i <= 1; i++)
					for(int j = -1; j <= 1; j++)
					{
						if(x + j < 0 || x + j >= width || y + i < 0 || y + i >= height)//so it doesn't count spaces off the grid
							continue;
						if(x+j == x && y + i == y)//so it doesn't count itself
							continue;
						aliveNeighbours += grid[x + j][y + i];
					}

				if(grid[x][y] == 1) //if the cell is alive
				{
					if(aliveNeighbours < 2)//if it has fewer than 2 neighbors
						nextGen[x][y] = 0;//it dies due to underpopulation;
					else
						if(aliveNeighbours == 2 || aliveNeighbours == 3)//if it has 2 or 3 neighbors
							nextGen[x][y] = 1;//it lives
						else
							if(aliveNeighbours>3)//if it has more than 3 neighbors
								nextGen[x][y] = 0;//it dies due to underpopulation
				}
				else//if the cell is dead
				{
					if(aliveNeighbours == 3)
						nextGen[x][y] = 1;
				}

			}
		}
		this.grid = nextGen; //setting the grid to the next generation
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public boolean getRunning()
	{
		return this.running;
	}
	
	public void tick()
	{
		if(running)
			nextGeneration();
	}
	
	public void render(Graphics g)
	{
		//filling in background (just white)
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

		//filling in grid
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / CELL_SIZE +0);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / CELL_SIZE +1);
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / CELL_SIZE +0);
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / CELL_SIZE +1);

		for(int y = yStart; y < yEnd; y++)
		{
			for(int x = xStart; x < xEnd; x++)
			{
				switch(grid[x][y])
				{
					case 0:
						g.setColor(Color.WHITE);
						break;
					case 1:
						g.setColor(Color.BLACK);
				}
				g.fillRect((int)(x * CELL_SIZE - handler.getCamera().getxOffset()), (int)(y  * CELL_SIZE - handler.getCamera().getyOffset()), CELL_SIZE, CELL_SIZE);
			}
		}
		//rendering in the grid (just intersecting lines instead of rectangles to make it faster)
		if(gridView)
		{
			g.setColor(Color.GRAY);
			for(int x = xStart; x < xEnd+1; x++)
				g.drawLine((int)(x * CELL_SIZE - handler.getCamera().getxOffset()), (int)(0-handler.getCamera().getyOffset()), (int)(x * CELL_SIZE - handler.getCamera().getxOffset()), (int)(height*CELL_SIZE-handler.getCamera().getyOffset()));
			for(int y = yStart; y < yEnd+1; y++)
				g.drawLine((int)(0-handler.getCamera().getxOffset()), (int)(y  * CELL_SIZE - handler.getCamera().getyOffset()), (int)(width*CELL_SIZE-handler.getCamera().getxOffset()), (int)(y  * CELL_SIZE - handler.getCamera().getyOffset()));
		}
	}
}
