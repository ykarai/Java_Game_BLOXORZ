
public class Point3D 
{
	double x;
	double y;
	double z;
	
	public Point3D()
	{
		x=0;
		y=0;
		z=0;
	}
	
	public Point3D(double x,double y, double z)
	{
		setXYZ(x,y,z);
	}
	
	public void setXYZ(double x2, double y2, double z2)
	{
		x=x2;
		y=y2;
		z=z2;
	}

	public void mullMat( Matrix3DForStu mat ) 
	{

		double xTemp,yTemp,zTemp;
		xTemp=x; yTemp= y; zTemp=z;

		x=xTemp*mat.mat[0][0] + yTemp*mat.mat[1][0] + zTemp*mat.mat[2][0] + 1*mat.mat[3][0]; 
		y=xTemp*mat.mat[0][1] + yTemp*mat.mat[1][1] + zTemp*mat.mat[2][1] + 1*mat.mat[3][1]; 
		z=xTemp*mat.mat[0][2] + yTemp*mat.mat[1][2] + zTemp*mat.mat[2][2] + 1*mat.mat[3][2]; 

	}

	public void computeABC(Point3D p1, Point3D p2, Point3D p3) 
	{
		x=(p2.y-p1.y)*(p3.z-p2.z)-(p3.y-p2.y)*(p2.z-p1.z);
		y=(p2.x-p1.x)*(p3.z-p2.z)-(p3.x-p2.x)*(p2.z-p1.z)*(-1);
		z=(p2.x-p1.x)*(p3.y-p2.y)-(p3.x-p2.x)*(p2.y-p1.y);
		
	}
	
	public String toString()
	{
		return "x="+x+"  y="+y+"   z="+z;
	}

}
