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
import java.awt.event.*;
import java.util.Random;

public class Board extends JPanel
{
    private Player human;
    private Player computer;
    private Player whosTurn = human;
    private Scanner key;
    private Cup player1Cup[];
    private Cup player2Cup[];
    private String input = "";
    private Random r;
     Cup Clicked = null;
    
    public Board()
    {
     key = new Scanner(System.in);   
     r = new Random(4);
    human = new Player(false);
    computer = new Player(true);
    player1Cup = new Cup[Configuration.CUPS_PER_SIDE];
    player2Cup = new Cup[Configuration.CUPS_PER_SIDE];
   
    //create
        for (int i = 0; i < Configuration.CUPS_PER_SIDE;i++)
        {            
            player1Cup[i] = new Cup(Configuration.SEEDS_PER_CUP, human, i*100+25, 100);            
            player2Cup[i] = new Cup(Configuration.SEEDS_PER_CUP, computer, i*100+25, 200);
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
            repaint();
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
        s += "   Player 2 Score: " + computer.getScore() + "\n";
        return s;
        //print("\n"); 
    
    
    }
    public boolean Run()
    {
       // dropSeed(human, player1Cup[slot]);
        if(Clicked != null && Clicked.getOwner() == human)
        {
        dropSeed(human, Clicked);
        }
        int cpuInput = (int)(r.nextInt(Configuration.CUPS_PER_SIDE) );
       System.out.print("\n" +cpuInput + "\n");
        boolean end = false;
        if (player2Cup[cpuInput].getSeedCount() > 0)
        {
            dropSeed(computer, player2Cup[cpuInput]);
        }
        else
        {
            for(int i = 0; i < Configuration.CUPS_PER_SIDE; i++)
            {
                
                if (player2Cup[i].getSeedCount() > 0)
                {
                        dropSeed(computer, player2Cup[cpuInput]);
                        break;
                }
            }
        }
        repaint();
        return false;
    }
    
    public int getUserInput()
    {
        int slot = 9999;
        try
        {
        slot = Integer.parseInt(input);
        }
        catch(Exception e)
        {
            System.out.print("Not a number, choosing error value\n");
            return 0;
        }
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
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponents(g2);
        
        int row = 100;
        String d = display();
        for(String s : d.split("\n"))
        {
            //g2.drawString(s, 100, row +=50);
        }
        for (int i = 0; i < player1Cup.length;i++)
        {
            //g2.drawRect(325, 020, 300, 100);
            g2.drawString("Player 1 Score: " + human.getScore(),450-15, 50);
            player1Cup[i].display(g2);
            player2Cup[i].display(g2);
            g2.drawRect(325, 450, 300, 400);
            g2.drawString("Player 2 Score: " + computer.getScore(),450-15, 350);
            
        }
 
            System.out.print("\n");      
         
        
	}
    public void setInput(String s)
    {
        this.input = s;
    }
    public String getInput()
    {
        return this.input;
    }
    public Cup isMouseIntercepted(Point P)
    {
        Cup c = null;
        for (int i = 0; i < this.player1Cup.length;i++)
        {
            if (player1Cup[i].isIntercepted(P))
            {
                c = player1Cup[i];
//                System.out.print(player1Cup[i].getSeedCount());
            }
            if (player2Cup[2].isIntercepted(P))
            {
                c = player1Cup[i];
            }
        }
        return c;
    }
}
