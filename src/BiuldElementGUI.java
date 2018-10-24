/*
 * Created on Eiar 5767
 * update Tamuz 5769
 * @author levian
 * for Student
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class BiuldElementGUI extends JToolBar implements ActionListener ,KeyListener
{
	MainPanel3DForStu mP;

	
	JButton setEnd;
	
	JButton setKubia;
	
	JButton clean;
	
	JButton colorR,colorY,colorB,colorG ;
	
	
	
    enum state{READEY,RUN, PAUSE, STOP}
    state matsav;

    public BiuldElementGUI(MainPanel3DForStu m,String name)
	{
		super(name);
		mP=m;

for (int i = 0; i <20; i++)
{
		addSeparator() ;
   
}	
	
		addSeparator() ;
		addSeparator() ; 
		
		
		setEnd= new JButton("הוסף נקודת סיום");
		setEnd.setForeground(Color.blue);
		setEnd.addActionListener(this);
		setEnd.setFont(new Font("David Transparent",Font.BOLD,16));
		setEnd.setEnabled(false);
		add( setEnd); 



		addSeparator() ;
		addSeparator() ; 

		setKubia= new JButton("הוסף קוביה");
		setKubia.setForeground(Color.blue);
		setKubia.addActionListener(this);
		setKubia.setFont(new Font("David Transparent",Font.BOLD,16));
		setKubia.setEnabled(false);
		add(setKubia); 

		addSeparator() ;
		addSeparator() ; 

		
		clean= new JButton("נקה לוח");
		clean.setForeground(Color.blue);
		clean.addActionListener(this);
		clean.setFont(new Font("David Transparent",Font.BOLD,16));
		add(clean); 
		
		
		
		addSeparator() ;
		addSeparator() ; 


		
		addSeparator() ;
		addSeparator() ; 
		
		colorR= new JButton(new ImageIcon("images/red.jpg"));;
		colorR.addActionListener(this);
		add(colorR);
		addSeparator() ;

		colorY= new JButton(new ImageIcon("images/yellow.jpg"));;
		colorY.addActionListener(this);
		add(colorY);
		addSeparator() ;

		colorB= new JButton(new ImageIcon("images/blue.jpg"));;
		colorB.addActionListener(this);
		add(colorB);
		addSeparator() ;

		colorG= new JButton(new ImageIcon("images/green.jpg"));;
		colorG.addActionListener(this);
		add(colorG);
			 
	}
	
    public void keyTyped(KeyEvent e)
    {
    	char ch=e.getKeyChar();
    	switch (ch)
    	{


    	default:
    		break;
    	}
    }
	
	public void actionPerformed (ActionEvent event)
	{
		JButton now=(JButton)event.getSource();

		
		

	
		 if (now==setEnd)
		{
  		    setEnd.setEnabled(false);
			mP.setEnd=true;
		}
	
		else if (now==setKubia)
		{
			mP.setKubia=true;
			setKubia.setEnabled(false);
		} 
		 
		else if (now==clean)
		{
			for (int i = 0; i <29; i++)
			{
				for (int j = 0; j <19; j++)
				{ 
					mP.gameTable.squares[i][j].status=0;
				}  
			}
			 mP.repaint();
			 setEnd.setEnabled(true);
			 setKubia.setEnabled(true);

		} 
		 
		else if (now==colorR)
		{
			mP.setEnd=false;
			mP.PlColor=Color.red;
		}
		else if (now==colorY)
		{
			mP.setEnd=false;
			mP.PlColor=Color.yellow;
		}
		else if (now==colorB)
		{
			mP.setEnd=false;
			mP.PlColor=Color.blue;
		}
		else if (now==colorG)
		{
			mP.setEnd=false; 
			mP.PlColor=Color.green;
        }
	
	
	}
	


	public void keyPressed(KeyEvent arg0) 
	{
		if (arg0.isActionKey())
		{
			System.out.println(arg0.getKeyCode());
			switch (arg0.getKeyCode()) 
			{
			case 37:  	        //left
				mP.RotateKubiaLeft();
				
				break;
			
			
			case 39:           //right 	 
				mP.RotateKubiaRight();
				break;
			
			
			case 38:          	//up
				mP.RotateKubiaUp();               
				break;
			
			
			case 40:  	       //down
			    mP.RotateKubiaDown();
				break;
			}
		}
	}
	public void keyReleased(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	

	
	
}
