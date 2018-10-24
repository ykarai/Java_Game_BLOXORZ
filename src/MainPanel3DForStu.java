/*
 * Created on Eiar 5767
 * update Av 5768
 * @author levian
 * for me
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;




import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.awt.Font;


public class MainPanel3DForStu extends JPanel implements MouseListener
{
	Thread thread;
	Matrix3DForStu mati=new Matrix3DForStu();
	Matrix3DForStu mati2=new Matrix3DForStu();
	Matrix3DForStu mati3=new Matrix3DForStu();

	Matrix3DForStu mabat=new Matrix3DForStu();  
	Matrix3DForStu mabat2=new Matrix3DForStu();
	Matrix3DForStu mabat3=new Matrix3DForStu();

	GameTable gameTable=new GameTable();


	Point3D p00;
	Point3D pMagoz;
	Point3D plight;

	Point3D p0;     //סדר הופעת המשטחים 
	Point3D p1;
	Point3D p2;
	Point3D p3;

	Point3D pFall0;  //נקודות לנפילה
	Point3D pFall1;

	Point3D pN;      //   נקודה שמאלית  של הקוביה  
	Point3D pK1;     // canMove

	int sederX[][]={ {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29},
			{29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0} };

	int sederY[][]={ {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19},
			{19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0} };
	int a[];
	int b[];

	boolean leftT=true;
	boolean upT=true;
	boolean downT=true;
	boolean rightT=true;	

	kubia basis;    //קוביה
	table basis2;   //שולחן


	Double KubiaSize=2.0;    //גודל קוביה   
	int Speed=9;      //מהירות סיבוב קוביה
	String turn;    //תזוזה נוכחית
	boolean doTurn=true; 

	boolean fall=false;//הפעלת אנימציית נפילה
	boolean fallK=false;//סדר הופעה בנפילה
	int letsan=1;


	boolean winner=false;//ירידה לחור
	boolean doWin=false;
	boolean rama=false;
	int ram =0;


	boolean setKubiaBigger=false; //הגדלה וההקטנה של הקוביה
	boolean setKubiaSmaller=false;  


	boolean l3=false;// תצוגת קוביה 4
	//	int z3=1;//4 תזוזת קוביה 



	String level;

	boolean edit=false; //הכנת משחק 
	boolean setEnd=false;
	boolean setKubia=false;

	Color PlColor=Color.lightGray;

	File selectedFile;    
	String  fileName;
	String  path=".";
	int L=0;//מעבר בין שלבים

	Image img = null;  //רקע
	
	AudioClip MoveClip,FallClip,WinClip;


	public MainPanel3DForStu()
	{
		super();
		try {
			img = ImageIO.read(new File("images/6.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		biuldElem();
		addMouseListener(this);
		//		setBackground(Color.white);

		level="level0.YKL";
		load(level);
		new MoveLevel0(this);
	}

	public void paintComponent (Graphics page)
	{
		super.paintComponent(page);
		page.drawImage(img, 0, 0, this);
		//		page.drawString("pN  "+ pN, 20, 20);



		int i,j;
		if (edit) 
		{

			for ( i = 1; i <29; i++)
			{
				for ( j = 1; j <19; j++)
				{
					if(gameTable.squares[i][j].status==1
							||gameTable.squares[i][j].status==5)
					{
						basis2.buildshape1(p00.x+(i*40), p00.y+(j*40), p00.z,10);

						basis2.pointstoguf();
						basis2.convertAndShow2(page,gameTable.squares[i][j].color);//,depth,prespctivCenter,perspectiv);

					}   
				}  

			}



		}
		else
		{

			if( p0.z>p1.z  )

				a=sederX[0];


			else//if( p1.z>=p0.z )

				a=sederX[1];	



			if( p1.z>p2.z  )

				b=sederY[0];
			else //if( p2.z>=p1.z )

				b=sederY[1];	




			boolean up=(check((int)p0.x, (int)p0.y, (int)p1.x, (int)p1.y,(int)p2.x, (int)p2.y));



			if ( (!up &&!fallK)||(up &&fallK)  )
			{
				ShowKubiaPaintCompment(page);
			}

			for ( i = 1; i <29; i++)
			{

				for ( j = 1; j <19; j++)
				{

					if(gameTable.squares[a[i]][b[j]-1].status==1)
					{
						upT=false;
					}
					if(gameTable.squares[a[i]+1][(b[j])].status==1)
					{
						rightT=false;
					}
					if(gameTable.squares[a[i]][b[j]+1].status==1)
					{
						downT=false;
					}
					if(gameTable.squares[a[i]-1][b[j]].status==1)
					{	
						leftT=false;
					}			

					if(gameTable.squares[a[i]][b[j]].status==1)
					{
						basis2.buildshape1(p00.x+(a[i]*40), p00.y+(b[j]*40), p00.z,10);
						basis2.mullMat(mabat);
						basis2.pointstoguf();
						basis2.convertAndShow(page,gameTable.squares[a[i]][b[j]].color,upT,rightT,downT,leftT,rama,pMagoz,plight);//,depth,prespctivCenter,perspectiv);
					} 
					if(gameTable.squares[a[i]][b[j]].status==5 && doWin)
					{
						rama=true;
						basis2.buildshape1(p00.x+(a[i]*40), p00.y+(b[j]*40), (p00.z),ram);
						basis2.mullMat(mabat);
						basis2.pointstoguf();
						basis2.convertAndShow(page,gameTable.squares[a[i]][b[j]].color,upT,rightT,downT,leftT,rama,pMagoz,plight);//,depth,prespctivCenter,perspectiv)
						rama=false;
					}

					upT=true;
					rightT=true;	
					downT=true;
					leftT=true;	
				}

			}
			if ((up &&!fallK)||(!up && fallK) )
			{

				ShowKubiaPaintCompment(page);
			}

		}

	}

	public void biuldElem()
	{


		basis2=new table(); 
		basis=new kubia();

		p00=new Point3D(0, 0,1500);	
		pMagoz=new Point3D(1000/2,650/2,1100);
		plight=new Point3D(1000/2+300,650/2-50,1600);

		p0 =new Point3D(p00.x,p00.y,p00.z);
		p1=new Point3D(p00.x+(40*30),p00.y,p00.z);
		p2=new Point3D(p00.x+(40*30),p00.y+(40*20),p00.z);
		p3=new Point3D((p1.x-p0.x)/2,(p2.y-p1.y)/2,p00.z);

		pN=new Point3D(2,2,1);
		pK1=new Point3D(2,2,1);

		pFall0=new Point3D((p00.x+pN.x*40),(p00.y+pN.y*40),(p00.z));
		pFall1=new Point3D((p00.x+pN.x*40)+40,(p00.y+pN.y*40),(p00.z));



		mati.setMatMove(-550, -250, -200);   
		mabat.mullMatMat(mati);   
		p0.mullMat(mati);
		p1.mullMat(mati);
		p2.mullMat(mati);
		p3.mullMat(mati);

		mati.SetBigger(p3.x,p3.y, p3.z,2.1,2.1,2.1);			
		mabat.mullMatMat(mati);
		p0.mullMat(mati);
		p1.mullMat(mati);
		p2.mullMat(mati);
		p3.mullMat(mati);
		
		for (int i = 0; i <10; i++)
		{

			RotateXFixDown();
		}
		for (int i = 0; i <4; i++)
		{

			rotateYFixLeft();
		}


		

		
	}

	public void ShowKubiaPaintCompment(Graphics page) 
	{

		if (ram!=KubiaSize*40)
		{
			if (pN.z==1)
			{
				basis.buildshape1(p00.x+pN.x*40, p00.y+pN.y*40, p00.z-(KubiaSize*40)+ram,40,40,(KubiaSize*40)-ram);
			}
			else if (pN.z==2)
			{
				basis.buildshape1(p00.x+pN.x*40, p00.y+pN.y*40, p00.z-40,(KubiaSize*40),40,40);
			}
			else if (pN.z==3)
			{
				basis.buildshape1(p00.x+pN.x*40, p00.y+pN.y*40, p00.z-40,40,(KubiaSize*40),40);
			}
			basis.mullMat(mabat3);
			basis.mullMat(mabat2);
			basis.mullMat(mabat);
			basis.pointstoguf();
			basis.convertAndShow(page ,pN,l3,pMagoz,plight);//,depth,prespctivCenter,perspectiv);	
		}

	}

	private boolean check(int x1, int y1, int x2, int y2, int x3, int y3) 
	{
		return x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)>=0.0;
	}

	public void move(int dx, int dy, int dz)
	{
		mati.setMatMove(dx,dy,dz);
		prepareToShowAndRepaint();
	}
	public void startAnimaition()
	{
		if (thread==null)
		{
			thread= new Animation3DForStu(this);
		}
	}
	public void suspendAnimation()
	{
		thread.suspend();
	}

	public void resumeAnimation()
	{
		thread.resume();
	}
	public void stopAnimation()
	{
		mati.setIdentity();
		thread.stop();
		thread=null;
	}


	public void moveLeft() 
	{

		move(-50,0,0);
	}

	public void moveRight() 
	{
		move(50,0,0);
	}

	public void movedown()
	{

		move(0,50,0);
	}

	public void moveup() 
	{
		move(0,-50,0);
	}

	public void backward() 

	{
		move(0,0,50);

	}

	public void forward() 
	{
		move(0,0,-50);

	}


	public void RotateXFixUp()
	{

		mati.rotateXFix(p3.x,p3.y, p3.z,Math.PI/25 );
		prepareToShowAndRepaint();
	}

	public void RotateXFixDown()
	{
		mati.rotateXFix(p3.x,p3.y, p3.z,-Math.PI/25 );
		prepareToShowAndRepaint();
	}


	public void rotateYFixLeft()
	{
		mati.rotateYFix(p3.x,p3.y, p3.z,Math.PI/25 );
		prepareToShowAndRepaint();	
	}
	public void rotateYFixRight()
	{
		mati.rotateYFix(p3.x,p3.y, p3.z,-Math.PI/25 );			
		prepareToShowAndRepaint();
	}


	public void rotateZFixBack() 
	{

		mati.rotateZFix(p3.x,p3.y,p3.z ,-Math.PI/25 );			
		prepareToShowAndRepaint();
	}
	public void rotateZFixFor() 
	{
		mati.rotateZFix(p3.x,p3.y,p3.z ,Math.PI/25 );			
		prepareToShowAndRepaint();

	}


	public void rotateAFixBack() 
	{
		mati.setMatRotateAxis(p0.x,p0.y,p0.z, p2.x,p2.y,p2.z, -Math.PI/25 );			
		prepareToShowAndRepaint(); 

	}
	public void rotateAFixFor() 
	{
		mati.setMatRotateAxis(p0.x,p0.y,p0.z, p2.x,p2.y,p2.z ,Math.PI/25 );			
		prepareToShowAndRepaint();

	}

	
	public void bigger() 
	{
		mati.SetBigger(p3.x,p3.y, p3.z,1.1,1.1,1.1);			
		prepareToShowAndRepaint();

	}
	public void smaller() 
	{
		mati.SetSmaller(p3.x,p3.y,p3.z,0.9,0.9,0.9);
		prepareToShowAndRepaint();
	}


	public void prepareToShowAndRepaint()
	{
		mabat.mullMatMat(mati);

		p0.mullMat(mati);
		p1.mullMat(mati);
		p2.mullMat(mati);
		p3.mullMat(mati);

		repaint();

	}







	public void RotateKubiaLeft() 
	{
		turn="Left";

		if(!fallK&& doTurn && !winner&&!l3&&!edit)
		{
			doTurn=false;
			if(pN.z==1)
			{

				pK1.setXYZ(pN.x-KubiaSize, pN.y, 2);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 

			}

			else if(pN.z==2)
			{

				pK1.setXYZ(pN.x-1, pN.y, 1);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 

			}

			else if(pN.z==3)
			{

				pK1.setXYZ(pN.x-1, pN.y, 3);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				}	

			}


		}



	}			



	public void RotateKubiaRight() 
	{
		turn="Right";

		if(!fallK && doTurn && !winner &&!l3&&!edit)
		{
			doTurn=false;
			if(pN.z==1)
			{
				pK1.setXYZ(pN.x+1, pN.y, 2);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 

			}

			else if(pN.z==2)
			{

				pK1.setXYZ(pN.x+KubiaSize, pN.y, 1);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 

			}

			else if(pN.z==3)
			{

				pK1.setXYZ(pN.x+1, pN.y, 3);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				}	

			}

		}


	}


	public void RotateKubiaUp() 
	{
		turn="Up"; 

		if(!fallK && doTurn && !winner&&!l3&&!edit)
		{
			doTurn=false;
			if(pN.z==1)
			{
				pK1.setXYZ(pN.x, pN.y-KubiaSize, 3);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 		
			}

			else if(pN.z==2)
			{

				pK1.setXYZ(pN.x, pN.y-1, 2);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 

			}

			else if(pN.z==3)
			{

				pK1.setXYZ(pN.x, pN.y-1, 1);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				}	

			}
		}

	}

	public void RotateKubiaDown()
	{
		turn="Down";  

		if(!fallK && doTurn && !winner&&!l3&&!edit)
		{
			doTurn=false;
			if(pN.z==1)
			{
				pK1.setXYZ(pN.x, pN.y+1, 3);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 		
			}

			else if(pN.z==2)
			{

				pK1.setXYZ(pN.x, pN.y+1, 2);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				} 

			}

			else if(pN.z==3)
			{

				pK1.setXYZ(pN.x, pN.y+KubiaSize, 1);
				if (canMove(pK1))
				{
					new KubiaMove(this,Speed);
				}	

			}

		}	


	}




	public  boolean  canMove(Point3D pk11) 
	{


		SoundMove();
		
		if(pk11.x<1|| pk11.y<1  ||  (pk11.x>28) || (pk11.y>18))
		{

			JOptionPane.showMessageDialog(this, "out of range");
			return false;
		}


		else if(pk11.z==1)
		{


			if(pk11.x==6 && pk11.y==7 &&level=="level1.YKL"&&gameTable.squares[6][7].color.equals(Color.pink))
			{
				gameTable.squares[8][8].status=1;  // גשר
				gameTable.squares[9][8].status=1;  // גשר 
				gameTable.squares[6][7].color=Color.green;
			}

			if(pk11.x==11 && pk11.y==7 && gameTable.squares[11][7].color.equals(Color.pink)
					&&level=="level1.YKL")
			{


				gameTable.squares[11][7].color=Color.green;//הגדלת קוביה
				setKubiaBigger=true;
			}

			if(pk11.x==16 && pk11.y==12&&gameTable.squares[16][12].color.equals(Color.pink)
					&&level=="level1.YKL")
			{


				gameTable.squares[16][12].color=Color.green;
				setKubiaSmaller=true;
			}

			if(pk11.x==19 && pk11.y==12
					&&level=="level1.YKL")
			{

				gameTable.squares[19][10].status=1;  // גשר 2
				gameTable.squares[19][11].status=1;  // גשר 2 
				gameTable.squares[20][10].status=1;  // גשר 2
				gameTable.squares[20][11].status=1;  // גשר 2 
				gameTable.squares[21][10].status=1;  // גשר 2
				gameTable.squares[21][11].status=1;  //  גשר 2

				gameTable.squares[19][12].color=Color.green;

			}



			if(  pk11.x==11 && pk11.y==11  && level=="level2.YKL"&&gameTable.squares[11][11].color.equals(Color.ORANGE) )
			{

				new Movelevel2(this);
				gameTable.squares[11][11].color=Color.lightGray;


			}


			if(pk11.x==8 && pk11.y==10 
					&&level == "level3.YKL"&& gameTable.squares[8][10].color.equals(Color.ORANGE))
			{
  			    new MoveLevel3(this);
				gameTable.squares[8][10].color=Color.lightGray;
			}

			if(level == "level4.YKL"&&pk11.x==10 && pk11.y==8
					&& gameTable.squares[10][8].color.equals(Color.yellow) )
			{
				new MoveLevel4(this);
				gameTable.squares[10][8].color=Color.lightGray;
			}

			if(level == "level5.YKL"&&pk11.x==13 && pk11.y==9
					&& gameTable.squares[13][9].color.equals(Color.yellow) )
			{
				new MoveLevel5(this);
				gameTable.squares[13][9].color=Color.lightGray;

			}



			if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==5)
			{
				winner=true;
				return true; 
			}

			if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==3)
			{
				return true;
			}

			if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==1)
			{
				return true;
			}
		}


		else if(pk11.z==2)
		{


			if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==1&&
					gameTable.squares[(int) (pk11.x+KubiaSize-1)][(int)pk11.y].status==1)
			{

				return true;
			}	
			else if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==5&&
					gameTable.squares[(int) (pk11.x+KubiaSize-1)][(int)pk11.y].status==1)
			{

				return true;
			}
			else if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==1&&
					gameTable.squares[(int) (pk11.x+KubiaSize-1)][(int)pk11.y].status==5)
			{

				return true;
			}
		}


		else if(pk11.z==3)
		{

			if(pk11.x==22 && pk11.y==10&&level=="level1.YKL")
			{

				gameTable.squares[19][10].status=0;  // גשר 2
				gameTable.squares[19][11].status=0;  // גשר 2 
				gameTable.squares[20][10].status=0;  // גשר 2
				gameTable.squares[20][11].status=0;  // גשר 2 
				gameTable.squares[21][10].status=0;  // גשר 2
				gameTable.squares[21][11].status=0;  //  גשר 2


			}

			if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==1&&
					gameTable.squares[(int)pk11.x][(int)(pk11.y+KubiaSize-1)].status==1)
			{
				return true;
			}
			else if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==5&&
					gameTable.squares[(int)pk11.x][(int)(pk11.y+KubiaSize-1)].status==1)
			{

				return true;
			}
			else if(gameTable.squares[(int)pk11.x][(int)pk11.y].status==1&&
					gameTable.squares[(int)pk11.x][(int)(pk11.y+KubiaSize-1)].status==5)
			{

				return true;
			}


		}

		fall=true;
		return true;

		
	}

	public void RotateKube(double alfa) 
	{
		if(pN.z==1)
		{
			if (turn=="Up") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40)+40,(p00.y+pN.y*40),(p00.z), -alfa);	
			}
			else if (turn=="Right") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40)+40,(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40)+40,(p00.y+pN.y*40)+40,(p00.z), -alfa);	
			}
			else if (turn=="Down") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40)+40,(p00.z),
						(p00.x+pN.x*40)+40,(p00.y+pN.y*40)+40,(p00.z), alfa);	
			}
			else if (turn=="Left") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40),( p00.z),
						(p00.x+pN.x*40),(p00.y+pN.y*40)+40,(p00.z), alfa);	
			}
		}	

		else if(pN.z==2)
		{
			if (turn=="Up") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40)+40*KubiaSize,(p00.y+pN.y*40),(p00.z), -alfa);	

			}
			else if (turn=="Right") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40)+40*KubiaSize,(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40)+40*KubiaSize,(p00.y+pN.y*40)+40,(p00.z), -alfa);	
			}
			else if (turn=="Down") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40)+40,(p00.z),
						(p00.x+pN.x*40)+40*KubiaSize,(p00.y+pN.y*40)+40,(p00.z), alfa);	

			}
			else if (turn=="Left") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40),(p00.y+pN.y*40)+40,(p00.z), alfa);	
			}
		}

		else if(pN.z==3)
		{
			if (turn=="Up") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40),( p00.z),
						(p00.x+pN.x*40)+40,(p00.y+pN.y*40),(p00.z), -alfa);	
			}
			else if (turn=="Right") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40)+40,(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40)+40,(p00.y+pN.y*40)+KubiaSize*40,(p00.z), -alfa);	
			} 
			else if (turn=="Down") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40)+KubiaSize*40,(p00.z),
						(p00.x+pN.x*40)+40,(p00.y+pN.y*40)+KubiaSize*40,(p00.z), alfa);	
			}
			else if(turn=="Left") 
			{
				mati2.setMatRotateAxis((p00.x+pN.x*40),(p00.y+pN.y*40),(p00.z),
						(p00.x+pN.x*40),(p00.y+pN.y*40)+KubiaSize*40,(p00.z), alfa);	
			}
		}	

		prepareToShowAndRepaint2();

	}

	public void setMabat2ToIdentity() 
	{

		mabat2.setIdentity();

	}

	public void setNewPosition() 
	{

		pN.setXYZ(pK1.x, pK1.y, pK1.z);
		doTurn=true;
	}



	public void prepareToShowAndRepaint2()
	{

		mabat2.mullMatMat(mati2);
		repaint();
	}






	public void hazazatKubiaXYZ(int dx, int dy, int dz)
	{
		mati3.setMatMove(dx,dy,dz);
		prepareToShowAndRepaint3();
	}

	public void rotateAFixK() 
	{
		mati3.setMatRotateAxis(pFall0.x,pFall0.y,pFall0.z,pFall1.x,pFall1.y,pFall1.z
				,(letsan*Math.PI/7) );			
		prepareToShowAndRepaint3(); 
	}

	public void shkia() 
	{
		mati3.SetSmaller(pFall0.x,pFall0.y,pFall0.z,0.9,0.9,0.9);
		prepareToShowAndRepaint3(); 
	}


	public void prepareToShowAndRepaint3()
	{

		mabat3.mullMatMat(mati3);;
		repaint();
	}





	public void SetNefilaPoints()
	{
		if(pN.z==1)
		{
			if (turn=="Up") 
			{
				letsan=-1;
				pFall0.setXYZ((p00.x+pN.x*40),(p00.y+pN.y*40)+20,(p00.z)-(KubiaSize*40)/2);
				pFall1.setXYZ((p00.x+pN.x*40)+40,(p00.y+pN.y*40)+20,(p00.z)-(KubiaSize*40)/2);	


			}
			else if (turn=="Right") 
			{
				letsan=-1;
				pFall0.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40),(p00.z)-(KubiaSize*40)/2);
				pFall1.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40)+40,(p00.z)-(KubiaSize*40)/2);	
			}
			else if (turn=="Down") 
			{

				pFall0.setXYZ((p00.x+pN.x*40),(p00.y+pN.y*40)+20,(p00.z)-(KubiaSize*40)/2);
				pFall1.setXYZ((p00.x+pN.x*40)+40,(p00.y+pN.y*40)+20,(p00.z)-(KubiaSize*40)/2);	
			}
			else if (turn=="Left") 
			{

				pFall0.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40),( p00.z)-(KubiaSize*40)/2);
				pFall1.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40)+40,(p00.z)-(KubiaSize*40)/2);	
			}
		}	

		else if(pN.z==2)
		{
			if (turn=="Up") 
			{
				letsan=-1;
				pFall0.setXYZ((p00.x+pN.x*40),(p00.y+pN.y*40)+20,(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+(KubiaSize*40),(p00.y+pN.y*40)+20,(p00.z)-20);	

			}
			else if (turn=="Right") 
			{
				letsan=-1;
				pFall0.setXYZ((p00.x+pN.x*40)+(KubiaSize*40)/2,(p00.y+pN.y*40),(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+(KubiaSize*40)/2,(p00.y+pN.y*40)+40,(p00.z)-20);
			}
			else if (turn=="Down") 
			{


				pFall0.setXYZ((p00.x+pN.x*40),(p00.y+pN.y*40)+20,(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+(KubiaSize*40),(p00.y+pN.y*40)+20,(p00.z)-20);		

			}
			else if (turn=="Left") 
			{

				pFall0.setXYZ((p00.x+pN.x*40)+(KubiaSize*40)/2,(p00.y+pN.y*40),(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+(KubiaSize*40)/2,(p00.y+pN.y*40)+40,(p00.z)-20);	
			}
		}

		else if(pN.z==3)
		{
			if (turn=="Up") 
			{
				letsan=-1;
				pFall0.setXYZ((p00.x+pN.x*40),(p00.y+pN.y*40)+(KubiaSize*40)/2,(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+40,(p00.y+pN.y*40)+(KubiaSize*40)/2,(p00.z)-20 );	
			}
			else if (turn=="Right") 
			{
				letsan=-1;
				pFall0.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40),(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40)+(KubiaSize*40),(p00.z)-20 );	
			} 
			else if (turn=="Down") 
			{

				pFall0.setXYZ((p00.x+pN.x*40),(p00.y+pN.y*40)+(KubiaSize*40)/2,(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+40,(p00.y+pN.y*40)+(KubiaSize*40)/2,(p00.z)-20 );	
			}
			else if(turn=="Left") 
			{

				pFall0.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40),(p00.z)-20);
				pFall1.setXYZ((p00.x+pN.x*40)+20,(p00.y+pN.y*40)+(KubiaSize*40),(p00.z)-20 );	
			}
		}

	}

	public void SartNefilaAnimation()
	{

		new Nefila (this);

	}

	public void startEdit()
	{
		edit=true;
		repaint(); 

	}

	public void stopEdit() 
	{
		edit=false;

		repaint(); 
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		System.out.println(e.getX()+"  "+e.getY());	

        if((e.getX()/40)<27 &&(e.getX()/40)>2 && (e.getY()/40)<17 && (e.getY()/40)>2)
        {
        	
        if ( edit && setEnd )
		{
			gameTable.squares[e.getX()/40][e.getY()/40].status=5;
			gameTable.squares[e.getX()/40][e.getY()/40].color=Color.black;
			setEnd=false;

		}
		else if ( edit &&  setKubia )
		{
			gameTable.x=e.getX()/40;
			gameTable.y=e.getY()/40;
			gameTable.z=1;
			gameTable.squares[e.getX()/40][e.getY()/40].color=Color.CYAN;
			gameTable.squares[e.getX()/40][e.getY()/40].status=1;
			setKubia=false;

		}
		else if (edit)
		{
			if(gameTable.squares[e.getX()/40][e.getY()/40].status==1)
			{
				gameTable.squares[e.getX()/40][e.getY()/40].status=0;

			}
			else
			{
				gameTable.squares[e.getX()/40][e.getY()/40].status=1;
				gameTable.squares[e.getX()/40][e.getY()/40].color=PlColor;

			}

		}

		repaint();
        }

	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
	

	public void load(String fileName)
	{
		try 
		{
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			gameTable= (GameTable)in.readObject();
			in.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		level=fileName.intern() ;
		pN.setXYZ(gameTable.x,gameTable.y,gameTable.z);

	}



	public void loadWithChoise()
	{
		JFileChooser fileChooser = new JFileChooser(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("YKL","YKL");
		fileChooser.setFileFilter(filter);

		int choice=fileChooser.showOpenDialog(null);

		if(choice!=JFileChooser.APPROVE_OPTION)
			return;  

		selectedFile=fileChooser.getSelectedFile();
		path=selectedFile.getAbsolutePath();
		fileName=selectedFile.getName();



		if(!fileName.endsWith(".YKL"))
			return;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			gameTable= (GameTable)in.readObject();
			in.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}

		repaint();
		level=fileName.intern() ;

		pN.setXYZ(gameTable.x,gameTable.y,gameTable.z);




	}



	public void saveWithChoise() 
	{

		JFileChooser fileChooser = new JFileChooser(path);
		//		   FileNameExtensionFilter filter = new FileNameExtensionFilter(
		//		            "Java , c# ,c++, h files", "java", "cs","cpp","h");
		//		        fileChooser.setFileFilter(filter);

		FileNameExtensionFilter filter = new FileNameExtensionFilter( "YKL","YKL");
		fileChooser.setFileFilter(filter);

		int choice=fileChooser.showSaveDialog(null);

		if(choice!=JFileChooser.APPROVE_OPTION)
			return;  

		selectedFile=fileChooser.getSelectedFile();
		path=selectedFile.getAbsolutePath();
		fileName=selectedFile.getName()+".YKL";

		try {

			FileOutputStream fos = new FileOutputStream(fileName);//GZIPOutputStream gzos = new GZIPOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(gameTable);
			out.close();
		}
		catch (IOException e) {
			System.out.println(e); 
		}
	
	}

	public void SoundMove() 
	{

		try {

			MoveClip=Applet.newAudioClip(new URL("file:images/move.wav"));
			MoveClip.play();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void SoundFall() 
	{

		try {

			FallClip=Applet.newAudioClip(new URL("file:images/weee.wav"));
			FallClip.play();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Soundwinner() 
	{

		try {

			WinClip=Applet.newAudioClip(new URL("file:images/Applause.wav"));
			WinClip.play();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void reset() 
	{

		KubiaSize=2.0;    //גודל קוביה   
		Speed=9;      //מהירות סיבוב קוביה
		doTurn=true; 
		fall=false;//הפעלת אנימציית נפילה
		fallK=false;//סדר הופעה בנפילה
		letsan=1;
		winner=false;//ירידה לחור
		doWin=false;
		rama=false;
		ram =0;
		setKubiaBigger=false; //הגדלה וההקטנה של הקוביה
		setKubiaSmaller=false;  
		l3=false;// תצוגת קוביה 4
		edit=false; //הכנת משחק 
		setEnd=false;
		setKubia=false;
		PlColor=Color.lightGray;
		mabat3.setIdentity(); ///איפוס מטריצת נפילה


	}

	public void MoveAlltables() 
	{

		new MoveAllTables(this);

	}



}







