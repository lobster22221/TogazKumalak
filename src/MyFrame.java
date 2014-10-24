/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.event.*;


public class MyFrame extends JFrame
{
    //visible components
    private Board vBoard;
     JTextField inputString;
    TextFieldListener textListener;
    static String displayString = "";
    MyFrame()
    {
        this.setTitle("Togaz Kumulak");
        this.setSize(500,500);
        this.setVisible(true);
       
        vBoard = new Board();
        this.vBoard.setVisible(true); 
        displayString = vBoard.display();
        
        repaint();
        
    }
    @Override
    public void paint(Graphics g)
    {
        //System.out.print("yeeee");
        
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponents(g2);
        int row = 1;
        for(String s : displayString.split("\n"))
        {
        g2.drawString(s, 100, row +=50);
        }
        //g2.drawString("test\nHello", 100, 200 +50);
    }
    public boolean update()
    {
//        System.out.print("update");
        boolean exit = vBoard.Run();
        displayString = vBoard.display();
        
        repaint();
        return exit;
    }
    
    private class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String input = inputString.getText();
                        
                        
		}
	}
}
