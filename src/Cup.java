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
    
    
    
    Cup(int seeds)
    {
        this.nextCup = null;
        seedCount = seeds;
        //System.out.print("call");
    }    
    public void display()
    {
        System.out.print(" "+seedCount+" ");
    }
    /**
     * For when this cup is passed over while distributing seeds
     * @param seedCount seeds remaining
     * @param player //Who is distributing the seeds, player 1 or 2?
     */
    public void dropSeed(int seedCount, Player player)
    {
        
        if (seedCount >= 1)
        {
            //Take seed from hand, and put in this cup
            this.seedCount++;
            seedCount--;
            
            //Continue if the new seed count is greater than 0
            if (seedCount > 0)
            {
                nextCup.dropSeed(seedCount, player);
            }
            
        }
    }
    public void setNextCup(Cup nextCup)
    {
        this.nextCup = nextCup;
    }
    public Cup getNextCup()
    {      
        return this.nextCup;
    }
    
    private Cup nextCup;
    private int seedCount;
    private int owner;
    
}
