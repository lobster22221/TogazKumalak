/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */

public class Board 
{
    public Cup player1Cup[];
    public Cup player2Cup[];
    public Board()
    {
    player1Cup = new Cup[Configuration.CUPS_PER_SIDE];
    player2Cup = new Cup[Configuration.CUPS_PER_SIDE];
   // player2Cup[];
        for (int i = 0; i < Configuration.CUPS_PER_SIDE;i++)
        {
            
            player1Cup[i] = new Cup(Configuration.SEEDS_PER_CUP, player1Cup[i]);
            player2Cup[Configuration.CUPS_PER_SIDE-1-i] = new Cup(Configuration.SEEDS_PER_CUP, player2Cup[Configuration.CUPS_PER_SIDE-1-i]);
        }
    }
    
    public void display()
    {
        for (int i = 0; i < player1Cup.length;i++)
        {
           //System.out.print(i + "\n");
            player1Cup[i].display();
        }
        System.out.print("\n========\n");
        for (int i = 0; i < player2Cup.length;i++)
        {
            
            player2Cup[i].display();
        }
    }
    
    public void Run()
    {
        display();
    }
    
    private int player1Score = 0;
    private int player2Score = 0;
    
    final static int totalScore = Configuration.CUPS_PER_SIDE * Configuration.SEEDS_PER_CUP;
    
    
}
