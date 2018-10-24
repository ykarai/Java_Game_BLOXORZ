import java.awt.Color;


public class Movelevel2 extends Thread 
{
	
		MainPanel3DForStu myPanel;
		int i,j;
		int a=0,m=0;
		public Movelevel2(MainPanel3DForStu myPanel)
		{
			this.myPanel=myPanel;
			setDaemon(true);
			start();
		}
		
		public void run()
		{
			
			while(a<1)	
			{


				for (i=15;i<22;i++)
				{
					if(!myPanel.fall)
					{
					for (j=5;j<18;j++)
				 	{
						myPanel.gameTable.squares[i][j].status=0;
						myPanel.gameTable.squares[i-3][j].status=1; 
						
						myPanel.repaint();
						try
						{
							sleep(50);
						} catch(InterruptedException  e){}
						
					}
					}

				}
			myPanel.gameTable.squares[15][11].status=5;
				
				while(m<3&&!myPanel.fall)
					
				{
				
					myPanel.gameTable.squares[12][12].color=Color.gray; 
					myPanel.gameTable.squares[12][13].color=Color.gray;
					myPanel.gameTable.squares[12][14].color=Color.gray;
					myPanel.gameTable.squares[12][15].color=Color.gray;
					
					myPanel.gameTable.squares[13][15].color=Color.gray;
					myPanel.gameTable.squares[14][15].color=Color.gray;
					myPanel.gameTable.squares[15][15].color=Color.gray;
					myPanel.gameTable.squares[16][15].color=Color.gray;
					myPanel.gameTable.squares[17][15].color=Color.gray;
					myPanel.gameTable.squares[18][12].color=Color.gray;
					myPanel.gameTable.squares[18][13].color=Color.gray;
					myPanel.gameTable.squares[18][14].color=Color.gray;
					myPanel.gameTable.squares[18][15].color=Color.gray;
					
					myPanel.gameTable.squares[13][8].color=Color.gray;
					myPanel.gameTable.squares[13][9].color=Color.gray;
					
					myPanel.gameTable.squares[17][8].color=Color.gray;
					myPanel.gameTable.squares[17][9].color=Color.gray;
				
				
				
				myPanel.repaint();
				
				try
				{
					sleep(400);
				} catch(InterruptedException  e){}
				
				
				
				
				myPanel.gameTable.squares[12][12].color=Color.ORANGE; 
				myPanel.gameTable.squares[12][13].color=Color.ORANGE;
				myPanel.gameTable.squares[12][14].color=Color.ORANGE;
				myPanel.gameTable.squares[12][15].color=Color.ORANGE;
				myPanel.gameTable.squares[13][15].color=Color.ORANGE;
				myPanel.gameTable.squares[14][15].color=Color.ORANGE;
				myPanel.gameTable.squares[15][15].color=Color.ORANGE;
				myPanel.gameTable.squares[16][15].color=Color.ORANGE;
				myPanel.gameTable.squares[17][15].color=Color.ORANGE;
				myPanel.gameTable.squares[18][12].color=Color.ORANGE;
				myPanel.gameTable.squares[18][13].color=Color.ORANGE;
				myPanel.gameTable.squares[18][14].color=Color.ORANGE;
				myPanel.gameTable.squares[18][15].color=Color.ORANGE;
				myPanel.gameTable.squares[13][8].color=Color.ORANGE;
				myPanel.gameTable.squares[13][9].color=Color.ORANGE;
				myPanel.gameTable.squares[17][8].color=Color.ORANGE;
				myPanel.gameTable.squares[17][9].color=Color.ORANGE;
				
				myPanel.repaint();
				
				try
				{
					sleep(400);
				} catch(InterruptedException  e){}
				
				m++;
				
				}
			
					
				a++;
			}	



		}	
		

}
