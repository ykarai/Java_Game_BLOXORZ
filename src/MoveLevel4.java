

import java.awt.Color;


public class MoveLevel4 extends Thread 
{

	MainPanel3DForStu myPanel;
	int i,j;
	boolean OneTime=true;
	public MoveLevel4(MainPanel3DForStu myPanel)
	{
		this.myPanel=myPanel;
		setDaemon(true);
		start();
	}

	public void run()
	{




		for (i=6;i<16;i++)
		{
			if(OneTime&&!myPanel.fall)
			{
			for (j=5;j<10;j++)
			{
				myPanel.gameTable.squares[i][j].status=0;
				myPanel.gameTable.squares[i+10][j].status=1; 

				myPanel.repaint();
				if(myPanel.gameTable.squares[(int)myPanel.pN.x][(int)myPanel.pN.y].status!=1 && OneTime&&!myPanel.fall)
				{
					donefila();	
					OneTime=false;
				}
				try
				{
					sleep(100);
				} catch(InterruptedException  e){}

			}

			}
		}


		myPanel.gameTable.squares[24][7].status=5;
		myPanel.repaint();


	}


	public void donefila()
	{
		
		myPanel.SetNefilaPoints();
		myPanel.SartNefilaAnimation();
		myPanel.SoundFall();
	
	}
}
