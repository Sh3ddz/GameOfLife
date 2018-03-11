package main;

public class Launcher 
{
   public static void main(String[] args)
   {
      Application app = new Application("Conway's Game of Life!", 640, 480);
      //Application app = new Application("Conway's Game of Life!", 1920, 1080);

      app.start();
   }
}