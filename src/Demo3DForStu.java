/*
 * Created on Eiar 5767
 * update on Tamuz 5769
 * @author levian
 * for Student
 */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JToolBar;



public class Demo3DForStu
{
	
 	public static void main(String[] args) 
	{
		JFrame myFrame=new JFrame("Project3D for Student");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MainPanel3DForStu mainPanel=new MainPanel3DForStu();
	    BiuldElementGUI jB=new BiuldElementGUI(mainPanel,"Option");
	    BiuldAndShowGUIStu jTB=new BiuldAndShowGUIStu(mainPanel,"joe",jB);
	    mainPanel.setOpaque(true);
	    mainPanel.setPreferredSize(new Dimension(1000,650));
		myFrame.add(jTB,BorderLayout.PAGE_START);
		myFrame.add(mainPanel,BorderLayout.CENTER);
		myFrame.add(jB,BorderLayout.PAGE_END);
		myFrame.pack();
		myFrame.setVisible(true);
	}
}
