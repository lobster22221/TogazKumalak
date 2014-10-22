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
import java.util.Scanner;
public class Board extends JFrame
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
    
        
    
    public void display()
    {
        for (int i = 0; i < player1Cup.length;i++)
        {
            
            
            player1Cup[i].display();
        }
        print("   Player 1 Score: " + human.getScore());
        print("\n===========================\n");
        for (Cup player2Cup1 : player2Cup) {
            player2Cup1.display();
        }
        print("   Player 2 Score: " + computer.getScore());
        print("\n");
    }
    
    public boolean Run()
    {
        display();
        print("It is your turn. Continue game Y/N: ");
        String userInput = key.nextLine();
        if (userInput.toUpperCase().equals("Y"))
        {
            //run
            
            //int slot = Configuration.SEEDS_PER_CUP;
            boolean loop = true;
            int slot = 99999;
            while (loop)
            {        
                print("\nWhich slot would you like to move seeds from? (choose from 1 to " + Configuration.SEEDS_PER_CUP + ") : "); 
                try //Make sure input is an int
                {
                    
                slot = key.nextInt();
                //Trash newline
                key.nextLine();
                if ( slot < Configuration.CUPS_PER_SIDE)
                {
                    loop = false;  
                }
                else
                {
                    print("error, choose a number less than " + Configuration.CUPS_PER_SIDE + "\n");
                }
                 
                }
                catch (Exception e)
                {
                    key.nextLine();
                    print("error, input is not a number " + Configuration.CUPS_PER_SIDE + "\n");
                }   
                
                if (player1Cup[slot].getSeedCount() == 0)
                {
                    print("error, slot is empty\n");
                    loop = true;
                }
            }  
            dropSeed(human, player1Cup[slot]);
            return false;
        }
        else
        {
            //exit
            return true;
        }
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
                        print("Home added\n");
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
    
    public static void print(String message)
    {
        
        System.out.print(message);
    }
    
}
