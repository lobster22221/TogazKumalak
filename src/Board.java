/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
public class Board extends JPanel
{
    private Player human;
    private Player computer;
    private Player whosTurn = human;
    private Scanner key;
    private Cup player1Cup[];
    private Cup player2Cup[];
    
    
    public Board()
    {
     key = new Scanner(System.in);   
    
    human = new Player(false);
    computer = new Player(true);
    player1Cup = new Cup[Configuration.CUPS_PER_SIDE];
    player2Cup = new Cup[Configuration.CUPS_PER_SIDE];
   
    //create
        for (int i = 0; i < Configuration.CUPS_PER_SIDE;i++)
        {            
            player1Cup[i] = new Cup(Configuration.SEEDS_PER_CUP, human);            
            player2Cup[i] = new Cup(Configuration.SEEDS_PER_CUP, computer);
            ///player2Cup[Configuration.CUPS_PER_SIDE-2-i].display();
        }
        //link
        for (int i = 0; i < Configuration.CUPS_PER_SIDE-1; i++)
        {
            
            player1Cup[i].setNextCup(player1Cup[i+1]);
            player2Cup[i + 1].setNextCup(player2Cup[i]);
        }
            player1Cup[8].setNextCup(player2Cup[8]);
            player2Cup[0].setNextCup(player1Cup[0]);
    }
    
        
    /**
     * This will be replaced by repaint()
     */
    public String display()
    {
        
        String s = "";
        for (int i = 0; i < player1Cup.length;i++)
        {
            s+=(player1Cup[i].display());
        }
        s +=  "   Player 1 Score: " + human.getScore() 
        +"\n===========================\n";
        for (Cup player2Cup1 : player2Cup) {
        s +=player2Cup1.display();
        }
        s += "   Player 2 Score: " + computer.getScore();
        return s;
        //print("\n"); 
    
    
    }
    public boolean Run()
    {
        boolean loop = true;
        int slot = 99999;
        while (loop)
        {            try //Make sure input is an int
            {
                slot = this.getUserInput();
                if (slot == -1)
                {
                    System.exit(1);
                    return true;
                }
                else
                {
                    slot--;
                }
                if ( slot < Configuration.CUPS_PER_SIDE)
                {
                    loop = false;  
                }
                else
                {
                    System.out.print("error, choose a number less than " + Configuration.CUPS_PER_SIDE + "\n");
                }
                if (player1Cup[slot].getSeedCount() == 0)
                {
                    System.out.print("error, slot is empty\n");
                    loop = true;
                }
            }
                catch (Exception e)
                {                
                    slot = 9999;
                    System.out.print("error, input is not a number " + Configuration.CUPS_PER_SIDE + "\n");
                }   
            
            
            
        }
        dropSeed(human, player1Cup[slot]);
        repaint();
        return false;
    }
    public int getUserInput()
    {
        int slot = 9999;
        String s = JOptionPane.showInputDialog("Enter a string between 1 and " + Configuration.CUPS_PER_SIDE + "  Enter -1 to Exit.");
        slot = Integer.parseInt(s);
        return slot;
    }
    
    public void dropSeed(Player owner, Cup start)
    {
        Cup c = start;
        int count = start.emptyCup(); //empty cup
        if(count == 1){c.getNextCup().dropSeed();} //If the cup has only 1 seed, move it to the next cup
        else if (count >1)
        {
            while(count > 0) //As long as seeds are in seedCount
            {
                c.dropSeed(); //Drop a single seed
                count--;//Reduce seeds in hand
                if (count != 0)
                {
                    c = c.getNextCup(); 
                }//start process for next cup
            }
        }
       
        //check if c is owned by the other player. If so, check if the player scored
        if (!c.getOwner().equals(owner))
        {             
            if(c.getSeedCount() % 2 == 0) //if it is even, capture pieces
            {
                owner.addScore(c.emptyCup());
            }
            else if(c.getSeedCount() == 3)//check for new home
            {
                if(owner.hasHome() == false) //Cannot have more than 1 home
                {
                    if(c != player1Cup[0] && c!= player2Cup[Configuration.CUPS_PER_SIDE-1]) //if cup is not in the last slot of either side due to obscure rule
                    {
                        //print("Home added\n");
                        c.setIsHome(true);
                        owner.addHome();
                        owner.addScore(c.emptyCup());
                    }
                    
                }
            }
        }
    }
    
    boolean end = false;
    final static int totalScore = Configuration.CUPS_PER_SIDE * Configuration.SEEDS_PER_CUP;
    
    public static void print(String message, int positionX, int positionY)
    {
        
        System.out.print(message);
    }
    
}
