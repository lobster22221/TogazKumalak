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
import java.awt.*;


public class MyFrame extends JFrame
{
    //visible components
    private Board vBoard;
//    JTextField inputField;
    //TextFieldListener textListener;
    static String displayString = "";
  //  Label insertTextLabel;
    JPanel panel;
     private MouseClickHandler mouseClick = new MouseClickHandler();
   
    MyFrame()
    {
        this.setTitle("Togaz Kumulak");
        this.setSize(Configuration.FRAME_WIDTH, Configuration.FRAME_HEIGHT);
        //Construct components
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        vBoard = new Board();
         this.addMouseListener(mouseClick);
        //textListener = new TextFieldListener();
        String s = "input goes here";
        //inputField = new JTextField(s, s.length()-7);
        //panel = new JPanel();
        //panel.add(inputField);
        
    //    insertTextLabel = new Label("Input: ");
//Initiate listener
        //inputField.addActionListener(textListener);
        //Add to frame
//        contentPane.add(inputField);
      //  contentPane.add(insertTextLabel);
        contentPane.add(vBoard);
        displayString = vBoard.display();
        
        //insertTextLabel.setBounds(75,100, 100,25);
        //inputField.setBounds(125,100, 100,25);
        vBoard.setBounds(0,0,950,950);
        
        //  repaint();
//        contentPane.repaint();
       // panel.repaint();
//        vBoard.repaint();
    }
    
    private class MouseClickHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent event)
		{
                    vBoard.Clicked = vBoard.isMouseIntercepted(event.getPoint());
                       // System.out.print("Click");
                        if(vBoard.Clicked!= null)
                        {
                         
                            vBoard.Run();
                        }
                        repaint();

			
		}
	}
}
