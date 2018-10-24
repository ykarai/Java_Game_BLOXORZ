/*
 * Created on Eiar 5767
 * upDate     Tamuz 5769
 * @author levian
 * for Student
 */
public class Matrix3DForStu 
{
	final int size=4;
	double mat[][];

	public Matrix3DForStu()
	{
		mat=new double[size][size];
		setIdentity();
	}
	
	public void setIdentity()
	{
		mat[0][0]=1.0;  mat[0][1]=0.0;  mat[0][2]=0.0; mat[0][3]=0.0;
		mat[1][0]=0.0;  mat[1][1]=1.0;  mat[1][2]=0.0; mat[1][3]=0.0;
		mat[2][0]=0.0;  mat[2][1]=0.0;  mat[2][2]=1.0; mat[2][3]=0.0;
		mat[3][0]=0.0;  mat[3][1]=0.0;  mat[3][2]=0.0; mat[3][3]=1.0;
	}
	
	public void setMatMove(double dx,double dy,double dz)
	{
		mat[0][0]=1.0;  mat[0][1]=0.0;  mat[0][2]=0.0; mat[0][3]=0.0;
		mat[1][0]=0.0;  mat[1][1]=1.0;  mat[1][2]=0.0; mat[1][3]=0.0;
		mat[2][0]=0.0;  mat[2][1]=0.0;  mat[2][2]=1.0; mat[2][3]=0.0;
		mat[3][0]=dx;   mat[3][1]=dy;   mat[3][2]=dz;  mat[3][3]=1.0;
	}

	public void setMatRotateAxis(double x1, double y1,double z1,
			                     double x2, double y2,double z2,
			                                       double teta)
	{
		Matrix3DForStu m1= new Matrix3DForStu();
		double a,b,c,d,l;
		
	    a = x2-x1; b = y2-y1; c = z2-z1;
	    l = (float) Math.sqrt(a*a+b*b+c*c);
	    a = a/l;   b = b/l;   c = c/l;
	    d = (float) Math.sqrt(b*b+c*c);

	    if (d == 0)
	     this.rotateXFix(x1,y1,z1, teta);
	    else
	    {
	      setMatMove(-x1,-y1,-z1);

          m1.setIdentity();                     // x axis
	      m1.mat[1][1] = c/d;  m1.mat[1][2] = b/d;
	      m1.mat[2][1] = -b/d; m1.mat[2][2] = c/d;
	      mullMatMat(m1);

	      m1.setIdentity();                     // y axis
	      m1.mat[0][0] = d;  m1.mat[0][2] = a;
	      m1.mat[2][0] = -a; m1.mat[2][2] = d;
	      mullMatMat(m1);

	      m1.RotateZ(teta);               // Z axis
	      mullMatMat(m1);

          m1.setIdentity();                     // y axis
	      m1.mat[0][0] = d;  m1.mat[0][2] = -a;
	      m1.mat[2][0] = a; m1.mat[2][2] = d;
	      mullMatMat(m1);


          m1.setIdentity();                     // x axis
	      m1.mat[1][1] = c/d;  m1.mat[1][2] = -b/d;
	      m1.mat[2][1] = b/d;  m1.mat[2][2] = c/d;
	      mullMatMat(m1);

	      m1.setMatMove(x1,y1,z1);
	      mullMatMat(m1);
	    }

		
	}

	public void mullMatMat(Matrix3DForStu aMat)
	{
		Matrix3DForStu temp=new Matrix3DForStu();
		for (int i= 0; i<size;i++)
			for (int j=0; j<size;j++)
			{
				temp.mat[i][j]=0;
				for (int k=0;k<size;k++)
					temp.mat[i][j] += mat[i][k] * aMat.mat[k][j];
			}
		mat=temp.mat;
	}
	
	public void mullAllPoints(double xr[], double yr[],double zr[], int aNum)
	{
		double xTemp,yTemp,zTemp;
		for(int i=0;i<aNum;i++)
		{
			xTemp=xr[i]; yTemp= yr[i]; zTemp=zr[i];
			xr[i]=xTemp*mat[0][0] + yTemp*mat[1][0] + zTemp*mat[2][0] + 1*mat[3][0]; 
			yr[i]=xTemp*mat[0][1] + yTemp*mat[1][1] + zTemp*mat[2][1] + 1*mat[3][1]; 
			zr[i]=xTemp*mat[0][2] + yTemp*mat[1][2] + zTemp*mat[2][2] + 1*mat[3][2]; 
		}
		
	}

	public void rotateXFix(double x,double y, double z, double teta)
	{
		Matrix3DForStu temp=new Matrix3DForStu();
		this.setMatMove(-x, -y, -z);
		temp.RotateX(teta);
		mullMatMat(temp);
		temp.setMatMove(x, y, z);
		mullMatMat(temp);
	}
	
	public void RotateX(double teta)
	{
		mat[0][0]=1.0;  mat[0][1]=0.0;             mat[0][2]=0.0;            mat[0][3]=0.0;
		mat[1][0]=0.0;  mat[1][1]=Math.cos(teta);  mat[1][2]=Math.sin(teta); mat[1][3]=0.0;
		mat[2][0]=0.0;  mat[2][1]=-Math.sin(teta); mat[2][2]=Math.cos(teta); mat[2][3]=0.0;
		mat[3][0]=0.0;  mat[3][1]=0.0;             mat[3][2]=0.0;            mat[3][3]=1.0;
	}

	
	
	public void rotateYFix(double x, double y, double z, double teta)
	{
		Matrix3DForStu temp=new Matrix3DForStu();
		setMatMove(-x, -y, -z);
		temp.RotateY(teta);
		mullMatMat(temp);
		temp.setMatMove(x, y, z);
		mullMatMat(temp);
		
	}

	private void RotateY(double teta)
	{
		mat[0][0]=Math.cos(teta); mat[0][1]=0.0;  mat[0][2]=Math.sin(teta); mat[0][3]=0.0;
		mat[1][0]=0.0;            mat[1][1]=1.0;  mat[1][2]=0.0;            mat[1][3]=0.0;
		mat[2][0]=-Math.sin(teta);mat[2][1]=0.0;  mat[2][2]=Math.cos(teta); mat[2][3]=0.0;
		mat[3][0]=0.0;            mat[3][1]=0.0;  mat[3][2]=0.0;            mat[3][3]=1.0;
		
	}

	
	
	
	public void rotateZFix(double x, double y, double z, double teta)
	{
		Matrix3DForStu temp=new Matrix3DForStu();
		setMatMove(-x, -y, -z);
		temp.RotateZ(teta);
		mullMatMat(temp);
		temp.setMatMove(x, y, z);
		mullMatMat(temp);
		
	}
	private void RotateZ(double teta)
	{
		mat[0][0]=Math.cos(teta);          mat[0][1]=Math.sin(teta);  mat[0][2]=0.0;     mat[0][3]=0.0;
		mat[1][0]=-Math.sin(teta);         mat[1][1]=Math.cos(teta);  mat[1][2]=0.0;     mat[1][3]=0.0;
		mat[2][0]=0.0;                     mat[2][1]=0.0;             mat[2][2]=1.0;     mat[2][3]=0.0;
		mat[3][0]=0.0;                     mat[3][1]=0.0;            mat[3][2]=0.0;     mat[3][3]=1.0;	
	}


	public void SetBigger(double x, double y, double z, double Sx,double Sy, double Sz )
	{
		Matrix3DForStu temp=new Matrix3DForStu();
		setMatMove(-x, -y, -z);
		temp.scale(Sx,Sy,Sz);
		mullMatMat(temp);
		temp.setMatMove(x, y, z);
		mullMatMat(temp);
	}	
	public void SetSmaller(double x, double y, double z, double Sx,double Sy, double Sz ) 
	{
		Matrix3DForStu temp=new Matrix3DForStu();
		setMatMove(-x, -y, -z);
		temp.scale(Sx,Sy,Sz);
		mullMatMat(temp);
		temp.setMatMove(x, y, z);
		mullMatMat(temp);
		
		
		
		
	}

	public void scale(double x,double y,double z)
	{
		mat[0][0]=x;     mat[0][1]=0.0;   mat[0][2]=0.0;   mat[0][3]=0.0;
		mat[1][0]=0.0;   mat[1][1]=y;     mat[1][2]=0.0;   mat[1][3]=0.0;
		mat[2][0]=0.0;   mat[2][1]=0.0;   mat[2][2]=z;     mat[2][3]=0.0;
		mat[3][0]=0.0;   mat[3][1]=0.0;   mat[3][2]=0.0;   mat[3][3]=1.0;	
	
	
	}


}
