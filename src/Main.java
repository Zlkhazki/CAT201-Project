import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Main {
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		
		// Create a JMenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(true);

		JMenu menu = new JMenu("Brick Breaker");
		
	    // Create a JMenuItem
	    JMenuItem menuItem1 = new JMenuItem("START");
		
      // Set the menu bar for the frame
      menu.add(menuItem1);
    
      
      menuBar.add(menu);
      
      	obj.setJMenuBar(menuBar);
      
      // Set the size and visibility of the frame
     	obj.setSize(1000, 780);
      	obj.setVisible(true);
      	
      
      menuItem1.addActionListener(new ActionListener(){
    	  public void actionPerformed(ActionEvent e) {
			JFrame obj=new JFrame();
    		Gameplay gamePlay = new Gameplay();
					obj.setBounds(10, 10, 700, 600);
		obj.setTitle("BRICK BREAKER");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		obj.add(gamePlay);
      		obj.setVisible(true);
      }
      });
      
	}

}
