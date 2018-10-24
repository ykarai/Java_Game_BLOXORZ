public class Nefila extends Thread
{
	MainPanel3DForStu myPanel;
	
	public Nefila(MainPanel3DForStu myPanel)
	{
		this.myPanel=myPanel;
    	setDaemon(true);
		start();
	}
	
	public void run()
	{
		
		int i=0;
		
		while (i<30)
		{
			myPanel.rotateAFixK();

			myPanel.hazazatKubiaXYZ(0, 0, 10);
			try
			{
				sleep(40);
			} catch(InterruptedException  e){}

			myPanel.pFall0.z+=10;
			myPanel.pFall1.z+=10;

			i++;

			
			
			
			if(!(myPanel.turn=="Down")) 

				myPanel.fallK=true; 
			else
				if(i>2)
					myPanel.fallK=true;	
			
		}
		

		for (int j = 0; j < 15; j++) 
		{
			myPanel.shkia();
			
			try
			{
				sleep(30);
			} catch(InterruptedException  e){}	
		}
		
		
		
		myPanel.reset();
		myPanel.load(myPanel.level);
		myPanel.repaint();
	 
	}
	
	
		


}

