import java.awt.Color;
import java.io.Serializable;


public class Square implements Serializable
{
	int status;
	Color color;
	public Square(int status, Color color) 
	{
		this.status=status;
		this.color=color;
	}
}
