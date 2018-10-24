import java.awt.Color;
import java.awt.Color;

public class MoveAllTables extends Thread
{



	MainPanel3DForStu myPanel;
	int i,j;
	int a=0,m=0;
	public MoveAllTables(MainPanel3DForStu myPanel)
	{
		this.myPanel=myPanel;
		setDaemon(true);
		start();
	}

	public void run()
	{

		for (i=1;i<29;i++)
		{

			for (j=1;j<19;j++)
			{
				if(myPanel.gameTable.squares[i][j].status!=0)
				{

					myPanel.gameTable.squares[i][j].status=0;


					myPanel.repaint();
					try
					{
						sleep(30);
					} catch(InterruptedException  e){}
				}


			}


		}

	}	


}



