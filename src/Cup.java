/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */
public class Cup 
{   
    Cup(int seeds, Player owner)
    {
        this.nextCup = null;
        seedCount = seeds;
        this.owner = owner;
        //System.out.print("call");
    }    
    public void display()
    {
        System.out.print(" "+seedCount+" ");
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
    private Cup nextCup;
    private int seedCount;
    private Player owner;
    private boolean isHome = false;
    
}
