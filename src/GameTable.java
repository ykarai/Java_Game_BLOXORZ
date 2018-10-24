import java.awt.Color;
import java.io.Serializable;


public class GameTable implements Serializable
{
	
	int i,j;
	Square squares[][];
	
	int x;//מיקום קוביה
	int y;
	int z;
	int a,b,c;
	
	
	Color color= Color.lightGray;

	public GameTable() 
	{
		squares=new Square[30][20]; 

		for (i=0;i<30;i++)
		{
			for (j=0;j<20;j++)
			{
				squares[i][j]=new Square(0,color);
			}
			
		}
		
		
	}
	





}
