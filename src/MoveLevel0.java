

import java.awt.Color;
import java.awt.Color;


public class MoveLevel0 extends Thread 
{
	
		MainPanel3DForStu myPanel;
		int i,j;
		int a=0,m=0;
		public MoveLevel0(MainPanel3DForStu myPanel)
		{
			this.myPanel=myPanel;
			setDaemon(true);
			start();
		}
		
		public void run()
		{
			try
			{
				sleep(500);
			} catch(InterruptedException  e){}
			for (int i = 0; i < 9; i++)
			{	
		       	myPanel.RotateKubiaRight();	
		       	try
				{
					sleep(500);
				} catch(InterruptedException  e){}
			}
		 	myPanel.RotateKubiaDown();
		 	
		 	try
			{
				sleep(500);
			} catch(InterruptedException  e){}
		 	for (int i = 0; i < 9; i++)
			{
		 		  
				try
				{
					sleep(500);
				} catch(InterruptedException  e){}
		     	myPanel.RotateKubiaLeft();	
		     
			}
			myPanel.winner=true;
		    myPanel.repaint();
		}	
		

}
