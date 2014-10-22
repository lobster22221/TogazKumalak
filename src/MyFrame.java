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
import java.awt.event.*;


public class MyFrame extends JFrame
{
    //visible components
    private Board vBoard;
     JTextField inputString;
    TextFieldListener textListener;
    
    MyFrame()
    {
       
    }
    
    private class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String input = inputString.getText();
		}
	}
}
