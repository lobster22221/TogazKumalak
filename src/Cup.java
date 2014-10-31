/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */
import java.awt.*;
public class Cup 
{   
    Cup(int seeds, Player owner)
    {
        this.nextCup = null;
        seedCount = seeds;
        this.owner = owner;
        //System.out.print("call");
    }
    Cup(int seeds, Player owner, int positionX, int positionY)
    {
        this(seeds,owner);
        x = positionX;
        y = positionY;
        
    }
    
    public String display()
    {
        return (" "+seedCount+" ");
    }
    
    public void display(Graphics2D g2)
    {
        
            System.out.print(seedCount);
        g2.drawRect(x, y, Configuration.CUP_WIDTH, Configuration.CUP_HEIGHT);
        //String s = ""+seedCount;
        int X = x+40;
        int Y = y;
        for(int i = 0; i < this.seedCount; i++)
        {
            int tempY = Y;
            int tempX = X+10;
            if(i%2 == 0)
            {
               // tempY //-=10;
                tempX -= 10;
                Y-=10;
            }
            
            g2.drawOval(tempX, tempY+i*10, 10, 10);
        }
        //g2.drawString(s, x+ Configuration.CUP_WIDTH/2, y + Configuration.CUP_HEIGHT/2);
    }
    /**
     * Place cup contents in hand
     * @return the amount of seeds in the cup before being emptied
     */
    public int emptyCup()
    {
        int count = seedCount;
        seedCount = 0;
        return count;
    }
    
    public void dropSeed()
    {
        seedCount++;
    }
    
    public int getSeedCount()
    {
        return seedCount;
    }
        
    public void setNextCup(Cup nextCup)
    {
        this.nextCup = nextCup;
    }
    public Cup getNextCup()
    {      
        return this.nextCup;
    }
    
    public Player getOwner()
    {
        return owner;
    }
    public void setIsHome(boolean b)
    {
        isHome = b;
    }
    public boolean isHome()
    {
        return isHome;
    }
    
   public boolean isIntercepted(Point p)
   {
       if(p.x < this.x)
       {
           return false;
       }
       if(p.x > this.x + Configuration.CUP_WIDTH) 
       {
           return false;
       }
       if(p.y < this.y)
       {
           return false;
       }
       if(p.y > this.y + Configuration.CUP_HEIGHT)
       {
           return false;
       }
       return true;  
   }
    private Cup nextCup;
    private int seedCount;
    private Player owner;
    private boolean isHome = false;
    
    private int x;
    private int y;
}
