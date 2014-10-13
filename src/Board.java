/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */

import java.util.Scanner;
public class Board 
{
    public Cup player1Cup[];
    public Cup player2Cup[];
    Scanner key;
    public Board()
    {
     key = new Scanner(System.in);   
    
    
    player1Cup = new Cup[Configuration.CUPS_PER_SIDE];
    player2Cup = new Cup[Configuration.CUPS_PER_SIDE];
   
    //create
        for (int i = 0; i < Configuration.CUPS_PER_SIDE;i++)
        {            
            player1Cup[i] = new Cup(i);            
            player2Cup[i] = new Cup(i);
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
        print("\n========\n");
        for (Cup player2Cup1 : player2Cup) {
            player2Cup1.display();
        }
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
            while (loop)
            {        
                print("\nWhich slot would you like to move seeds from? (choose from 1 to " + Configuration.SEEDS_PER_CUP + ") : "); 
                try //Make sure input is an int
                {
                    
                int slot = key.nextInt();
                //Trash newline
                key.nextLine();
                if ( slot < Configuration.SEEDS_PER_CUP)
                {
                    loop = false;  
                }
                else
                {
                    print("error, choose a number less than " + Configuration.SEEDS_PER_CUP + "\n");
                }
                 
                }
                catch (Exception e)
                {
                    key.nextLine();
                    print("error, input is not a number " + Configuration.SEEDS_PER_CUP + "\n");
                }               
            }                
            //Move beans
            return false;
        }
        else
        {
            //exit
            return true;
        }
    }
    
    private int player1Score = 0;
    private int player2Score = 0;
    boolean end = false;
    final static int totalScore = Configuration.CUPS_PER_SIDE * Configuration.SEEDS_PER_CUP;
    
    public static void print(String message)
    {
        System.out.print(message);
    }
}
