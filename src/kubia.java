import java.awt.Color;
import java.awt.Graphics;


public class kubia 
{

	int numOfPoint=4;
	double xReal[]=new double[8];
	double yReal[]=new double[8];
 	double zReal[]=new double[8]; 
	
	double xGuf[][]= new double[numOfPoint+2][numOfPoint];
	double yGuf[][]= new double[numOfPoint+2][numOfPoint];
	double zGuf[][]= new double[numOfPoint+2][numOfPoint];

	int xInt[]=new int[numOfPoint];
 	int yInt[]=new int[numOfPoint];
	int d=1100;
	int i;
	Color colorP=Color.blue;
	Color colorG=Color.CYAN ;
	Color colors[][]=
	{  { colorG,colorG,colorP,
	    colorP,colorP,colorP },
	   { colorP,colorP,colorP,
	    colorG,colorP,colorG },
	   { colorP,colorP,colorG,
	    colorP,colorG,colorP }  } ;

	Point3D pt1=new Point3D(),pt2=new Point3D(),pt3=new Point3D();
	

	
	public void buildshape1(double x, double y, double z, double dx, double dy, double dz) 
	{
		xReal[0]=x;        yReal[0]=y;         zReal[0]=z; 
		xReal[1]=x+dx;    yReal[1]=y;         zReal[1]=z; 
		xReal[2]=x+dx;    yReal[2]=y+dy;     zReal[2]=z; 
		xReal[3]=x;        yReal[3]=y+dy;     zReal[3]=z; 

		xReal[4]=x;        yReal[4]=y;         zReal[4]=z+dz; 
		xReal[5]=x+dx;    yReal[5]=y;         zReal[5]=z+dz; 
		xReal[6]=x+dx;    yReal[6]=y+dy;     zReal[6]=z+dz; 
		xReal[7]=x;        yReal[7]=y+dy;    zReal[7]=z+dz; 

		
	}
	
	public void  pointstoguf()
	{
		//0
		xGuf[0][0]=xReal[0];     yGuf[0][0]=yReal[0];   zGuf[0][0]=zReal[0];
		xGuf[0][1]=xReal[1];     yGuf[0][1]=yReal[1];   zGuf[0][1]=zReal[1];
		xGuf[0][2]=xReal[2];     yGuf[0][2]=yReal[2];   zGuf[0][2]=zReal[2];
		xGuf[0][3]=xReal[3];     yGuf[0][3]=yReal[3];   zGuf[0][3]=zReal[3];
		
		//1
		xGuf[1][0]=xReal[7];    yGuf[1][0]=yReal[7];   zGuf[1][0]=zReal[7];
		xGuf[1][1]=xReal[6];    yGuf[1][1]=yReal[6];   zGuf[1][1]=zReal[6];
		xGuf[1][2]=xReal[5];    yGuf[1][2]=yReal[5];   zGuf[1][2]=zReal[5];
		xGuf[1][3]=xReal[4];    yGuf[1][3]=yReal[4];   zGuf[1][3]=zReal[4];
	
		
	    xGuf[2][0]=xReal[0];        yGuf[2][0]=yReal[0];   zGuf[2][0]=zReal[0];  
		xGuf[2][1]=xReal[4];        yGuf[2][1]=yReal[4];   zGuf[2][1]=zReal[4];  
		xGuf[2][2]=xReal[5];        yGuf[2][2]=yReal[5];   zGuf[2][2]=zReal[5]; 
		xGuf[2][3]=xReal[1];        yGuf[2][3]=yReal[1];   zGuf[2][3]=zReal[1];  

	
	    xGuf[3][0]=xReal[1];        yGuf[3][0]=yReal[1];   zGuf[3][0]=zReal[1];  
		xGuf[3][1]=xReal[5];        yGuf[3][1]=yReal[5];   zGuf[3][1]=zReal[5];  
		xGuf[3][2]=xReal[6];        yGuf[3][2]=yReal[6];   zGuf[3][2]=zReal[6]; 
		xGuf[3][3]=xReal[2];        yGuf[3][3]=yReal[2];   zGuf[3][3]=zReal[2];  

	    
	    xGuf[4][0]=xReal[3];        yGuf[4][0]=yReal[3];   zGuf[4][0]=zReal[3];  
		xGuf[4][1]=xReal[2];        yGuf[4][1]=yReal[2];   zGuf[4][1]=zReal[2];  
		xGuf[4][2]=xReal[6];        yGuf[4][2]=yReal[6];   zGuf[4][2]=zReal[6]; 
		xGuf[4][3]=xReal[7];        yGuf[4][3]=yReal[7];   zGuf[4][3]=zReal[7];  

		
	    xGuf[5][0]=xReal[0];        yGuf[5][0]=yReal[0];   zGuf[5][0]=zReal[0];  
		xGuf[5][1]=xReal[3];        yGuf[5][1]=yReal[3];   zGuf[5][1]=zReal[3];  
		xGuf[5][2]=xReal[7];        yGuf[5][2]=yReal[7];   zGuf[5][2]=zReal[7]; 
		xGuf[5][3]=xReal[4];        yGuf[5][3]=yReal[4];   zGuf[5][3]=zReal[4];  

		
		
    
    
	}
	
	public void convertAndShow(Graphics page ,Point3D pN,boolean l3, 
			                   Point3D magoz, Point3D plight)
	{
		Color color[] = null;
		
		
		
		if (pN.z==1) 
		{
			color=colors[0];	
		}
		else if (pN.z==2) 
		{
             color=colors[1];	
		}
		else if (pN.z==3) 
		{
			 color=colors[2];	
		}
		
						
		
		for(int i =0;i<numOfPoint+2;i++)
		{
			convert3D(xGuf[i],yGuf[i],zGuf[i],numOfPoint,magoz);

			if(!l3)
			{
				if ( check(xInt[0],yInt[0],xInt[1],yInt[1],xInt[2],yInt[2]) )
				{

					pt1.setXYZ(xGuf[i][0], yGuf[i][0], zGuf[i][0]);
					pt2.setXYZ(xGuf[i][1], yGuf[i][1], zGuf[i][1]);
					pt3.setXYZ(xGuf[i][2], yGuf[i][2], zGuf[i][2]);
					
					page.setColor(colorToShow(plight,pt1,pt2,pt3,color[i]));
					page.fillPolygon(xInt,yInt, numOfPoint);
					
					page.setColor(colorToShow(plight,pt1,pt2,pt3,Color.black));
					page.drawPolygon(xInt, yInt, numOfPoint);
							
				}

			}
			else
			{
				page.setColor(Color.black);
				page.drawPolygon(xInt, yInt, numOfPoint);
			}


		}
		
	}
	
	
//	public void convertAndShow2(Graphics page ,Point3D pN)
//	{
//		for(int i =0;i<2;i++)
//		{
//			convert2D(xGuf[i],yGuf[i],numOfPoint);
//			
//			if ( check(xInt[0],yInt[0],xInt[1],yInt[1],xInt[2],yInt[2]) )
//			{
//			page.setColor(Color.gray);
//			page.fillPolygon(xInt,yInt, numOfPoint);
//			page.setColor(Color.black);
//			page.drawPolygon(xInt, yInt, numOfPoint);
//			
//			}
//		
//		}
//		
//			
//		for(int i =2;i<numOfPoint+2;i++)
//		{
//			convert2D(xGuf[i],yGuf[i],4);
//		
//			if ( check(xInt[0],yInt[0],xInt[1],yInt[1],xInt[2],yInt[2]) )
//			{
//				page.setColor(Color.blue);
//				page.fillPolygon(xInt,yInt, 4);
//				page.setColor(Color.black);
//				page.drawPolygon(xInt, yInt, 4);
//				
//				
//			}
//		}
	
//	}	
//	
	
	private boolean check(int x1, int y1, int x2, int y2, int x3, int y3) 
	{
		return x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)>=0.0;
	}



	private void convert2D(double[] xR, double[] yR, int size) 
	{

		for(int i=0;i<size;i++)
		{
			xInt[i]=(int)xR[i];
			yInt[i]=(int)yR[i];

		}
	}

	private void convert3D(double[] xR, double[] yR,double[] zR, int size, Point3D magoz) 
	{
		
		for(int i=0;i<size;i++)
		{
			
			xInt[i]=(int)(xR[i]*magoz.z/(magoz.z+zR[i])+magoz.x);
			yInt[i]=(int)(yR[i]*magoz.z/(magoz.z+zR[i])+magoz.y);
			
//			xInt[i]=(int)(xR[i]*d/(d+zR[i]))+1000/2;
//			yInt[i]=(int)(yR[i]*d/(d+zR[i]))+650/2;

		}
	}
 
	

	public void mullMat(Matrix3DForStu mat)
	{
		mat.mullAllPoints(xReal, yReal,zReal, numOfPoint*2);
	}


	public Color colorToShow(Point3D lightPoint,Point3D p1,Point3D p2,Point3D p3,Color c)
	{


		Point3D normal,vec;
		normal=new Point3D(0,0,0);
		vec=new Point3D(0,0,0);
		normal.computeABC(p1, p2, p3);
		vec.x = p1.x - lightPoint.x;
		vec.y = p1.y - lightPoint.y;
		vec.z = p1.z - lightPoint.z;
		double cosa = ((normal.x*vec.x) + (normal.y*vec.y) + (normal.z*vec.z));
		cosa /= (Math.sqrt(normal.x*normal.x + normal.y*normal.y + normal.z*normal.z) * Math.sqrt(vec.x*vec.x + vec.y*vec.y + vec.z*vec.z));
		cosa=Math.acos(cosa);
//		System.out.println("cos="+cosa);

		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		
//		System.out.println("hsb="+hsb[0] + " "+hsb[1] + " "+hsb[2] + " "+hsb.length);
		if(cosa < 0 )
		{
			cosa = 0;
			return Color.getHSBColor(hsb[0], hsb[1], 1);
		}
		

//		cosa=0.3 + 0.4*(cosa);
//		cosa=1.57- 0.1*(cosa);
		cosa=1-0.2*(cosa);
		if(cosa < 0 )
		  cosa = 0;
	    if (cosa >1)
		   cosa=1.0;
		
		return Color.getHSBColor(hsb[0], hsb[1], (float) (cosa));
	}

	
	
}
