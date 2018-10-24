import java.awt.Color;




public class MoveLevel3 extends Thread 
{

	MainPanel3DForStu myPanel;
	int i,j;
	int m,a=0;
	boolean stop=false;
	public MoveLevel3(MainPanel3DForStu myPanel)
	{
		this.myPanel=myPanel;
		setDaemon(true);
		start();
	}

	public void run()
	{

		myPanel.l3=true;
		try
		{
			sleep(700);
		} catch(InterruptedException  e){}
		
		
		while(!stop&&myPanel.level=="level3.YKL"&&!myPanel.fall&&
			!myPanel.gameTable.squares[8][10].color.equals(Color.ORANGE))
		{
		
		i=4;
		m=21;
		
		a=0;
		while(a<4)	
		{

			for (j=7;j<13;j++)
			{
				myPanel.gameTable.squares[i][j].status=0;
				myPanel.gameTable.squares[i+5][j].status=1;
			
			}

			i++;
         	for (j=7;j<13;j++)
			{
				myPanel.gameTable.squares[m][j].status=0;
				myPanel.gameTable.squares[m-5][j].status=1;
			}

			m--;

			myPanel.gameTable.squares[20-a][11].status=1;
			myPanel.gameTable.squares[20-a-1][11].status=5;
			
			myPanel.pN.x++;
			myPanel.repaint();

						
			try
			{
				sleep(80);
			} catch(InterruptedException  e){}

			a++;
	
		}	
		

		myPanel.l3=false;
		myPanel.repaint();
		try
		{
			sleep(1200);
		} catch(InterruptedException  e){}
		
		if(myPanel.pN.x>12)
		{
		 stop=true;
		}
		
		if(!stop)
		{

		myPanel.l3=true;
		myPanel.repaint();
		try
		{
			sleep(230);
		} catch(InterruptedException  e){}
		}
		
		
				
		i=12;
		m=13;
		
		a=0;
		
		
		while(a<4&&!stop )	
		{
			
			for (j=7;j<13;j++)
			{
				myPanel.gameTable.squares[i][j].status=0;
				myPanel.gameTable.squares[i-5][j].status=1;
				}

			i--;

			for (j=7;j<13;j++)
			{
				myPanel.gameTable.squares[m][j].status=0;
				myPanel.gameTable.squares[m+5][j].status=1;
			}

			m++;

			myPanel.gameTable.squares[16+a][11].status=1;
			myPanel.gameTable.squares[16+a+1][11].status=5;
			
			    myPanel.pN.x--;
				myPanel.repaint();
          
			try
			{
				sleep(80);
			} catch(InterruptedException  e){}

			a++;
		}	
		
		
		try
		{
			sleep(800);
		} catch(InterruptedException  e){}

		
		
		}   
	
	}	


}
