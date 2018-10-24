/*
 * Created on Eiar 5767
 * @author levian
 * for Student
 */

public class Animation3DForStu extends Thread
{
	MainPanel3DForStu myPanel;

	public Animation3DForStu(MainPanel3DForStu myPanel)
	{
		this.myPanel=myPanel;
		setDaemon(true);
		start();
	}
	

	public void run()
	{
		while (true)
		{
	
			
			
			for(int i=0;i<3;i++)
			{
				myPanel.rotateYFixLeft();
				
		        try
			    {
				sleep(100);
			    } catch(InterruptedException  e){}
			}
				

		}
		
	}
	
}

