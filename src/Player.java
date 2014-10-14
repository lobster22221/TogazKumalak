/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */

public class Player
{
    private boolean computer;
    private int score = 0;
    private boolean hasHome = false;
    public Player(boolean isComputer)
    {
        computer = isComputer;
        score = 0;
    }
    
    public void addScore(int value)
    {
        score += value;
    }
    
    public int getScore()
    {
        return score;
    }
    public void resetScore()
    {
        score = 0;
    }
    public boolean isComputer()
    {
        return computer;
    }
    public boolean hasHome()
    {
        return hasHome;
    }
    
    public void addHome()
    {
        hasHome = true;
    }
}