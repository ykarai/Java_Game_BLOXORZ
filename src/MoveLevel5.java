
public class MoveLevel5 extends Thread 
{

	MainPanel3DForStu myPanel;
	int x ;  
	int y ;
	boolean OneTime=true;
	public MoveLevel5(MainPanel3DForStu myPanel)
	{
		this.myPanel=myPanel;
		setDaemon(true);
		start();
	}

	public void run()
	{




		while (!myPanel.winner &&!myPanel.fall&&myPanel.level=="level5.YKL")
		{
			
			
			x = (int) ((Math.random()*(28-5+1))+5);  
			 y = (int) ((Math.random()*(18-5+1))+5);
			
			if (myPanel.gameTable.squares[x][y].status!=0)
			{
				
			
			myPanel.gameTable.squares[x][y].status=5;
			myPanel.repaint();
			try
			{
				sleep(1900);
			} catch(InterruptedException  e){}
		
			if(!myPanel.winner)
				myPanel.gameTable.squares[x][y].status=1;
			
			}
			
			
		}
			
		
			
			
			
			

		






	}


}
