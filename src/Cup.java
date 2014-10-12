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
    /**
     * For a cup that doesn't point to another. Also a default constructor
     */
    Cup()
    {
        nextCup = null;
        seedCount = Configuration.SEEDS_PER_CUP;
    }
    /**
     * 
     * @param seeds If there is a need for an unusual amount of seeds
     */
    Cup(int seeds)
    {
        nextCup = null;
        seedCount = seeds;
    }
    /**
     * @param nextCup 
     */
    Cup(Cup nextCup)
    {
        this.nextCup = nextCup;
        seedCount = Configuration.SEEDS_PER_CUP;
    }
    /**
     * 
     * @param seeds for unusual number of seeds
     * @param nextCup when seeds are moved, they are moved to this cup
     */
    Cup(int seeds, Cup nextCup)
    {
        this.nextCup = nextCup;
        seedCount = seeds;
    }
    
    public void display()
    {
        System.out.print(" "+seedCount+" ");
    }
    private Cup nextCup;
    private int seedCount;
    
}
