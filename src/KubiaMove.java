import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;


public class KubiaMove extends Thread
{
	MainPanel3DForStu myPanel;
	int num;
	int a;
	
	
	public KubiaMove(MainPanel3DForStu myPanel,int num)
	{
		this.myPanel=myPanel;
		this.num=num;
		setDaemon(true);
		start();
	}
	
	public void run()
	{
		int i=0,j=0;
		double teta=(Math.PI/2)/num;
		while (i<num)
		{
			myPanel.RotateKube(teta);

			try
			{
				sleep(28);
			} catch(InterruptedException  e){}

			i++;
		}
		myPanel.setMabat2ToIdentity();
		myPanel.setNewPosition();
		myPanel.repaint();
	
		
		
		if(myPanel.setKubiaBigger)
		{
			while (j<4) 
			{
        		myPanel.KubiaSize+=(0.5);
				myPanel.repaint();
			    try
				{
					sleep(60);
				} catch(InterruptedException  e){}
				j++;

			}
		    myPanel.setKubiaBigger=false;

		}
		
		if(myPanel.setKubiaSmaller)
		{
        	while (j<4) 
			{
				myPanel.KubiaSize-=0.5;
				myPanel.repaint();
				try
				{
					sleep(60);
				} catch(InterruptedException  e){}
				j++;
			}
            myPanel.setKubiaSmaller=false;
		}

		
					
		if(myPanel.fall)
		{
			
			myPanel.SetNefilaPoints();
			myPanel.SartNefilaAnimation();
			myPanel.SoundFall();	
				
			
		}	
	
		
		if(myPanel.winner)
		{
			
			myPanel.Soundwinner();
			int d=0;
			myPanel.doWin=true;
			myPanel.gameTable.squares[(int) (myPanel.pN.x)][(int) (myPanel.pN.y)].color
			=Color.cyan;

			while (d<(myPanel.KubiaSize*40)/5)
			{
				myPanel.ram+=5;
				myPanel.repaint();

				try
				{
					sleep(30);
				} catch(InterruptedException  e){}
				d++;

			}
			
			myPanel.MoveAlltables();
		
			try
			{
				sleep(3500);
			} catch(InterruptedException  e){}
			if(myPanel.level=="level0.YKL")
			  JOptionPane.showMessageDialog(null," החל לשחק              ");
			else
				JOptionPane.showMessageDialog(null," כל הכבוד!               "); 
			
			    
			
			    myPanel.reset();
			    if (myPanel.L<5)
				{
			    myPanel.L++;
				}
			    myPanel.level=("level"+myPanel.L+".YKL");
				myPanel.load(myPanel.level);
				myPanel.repaint();
				
		
		}
		
		
		
		
		
	}
	
	
		


}

