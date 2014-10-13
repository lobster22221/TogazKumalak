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
        print("\n========\n");
        for (int i = 0; i < player2Cup.length;i++)
        {
            
            player2Cup[i].display();
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
                try
                {
                    
                int slot = key.nextInt();
                //Trash newline
                key.nextLine();
                loop = false;   
                }
                catch (Exception e)
                {
                    key.nextLine();
                    print("error, choose a number less than " + Configuration.SEEDS_PER_CUP + "\n");
                }
               
            }
            
            
           
            
            return false;
        }
        else
        {
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
