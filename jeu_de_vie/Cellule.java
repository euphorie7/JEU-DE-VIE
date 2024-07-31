
public class Cellule extends Tab1D {
	
	// pour des causes de verification du bon deroulement du code :
	public static int c=0;
	private Tab1D Tab =null;
	private Tab1D father_root=null;
	private int [] tailles=null;
	private int etat;
	private int [] cord;
	private void setTailles(int []tailles)
	{
		this.tailles=tailles;
	}
	public int [] getTailles()
	{
		return tailles;
	}
	private void setFather_root(Tab1D father_root)
	{
		this.father_root=father_root;
	}
	public Tab1D getFather_root()
	{
		return father_root;
	}
	private void setTab(Tab1D T)
	{
		this.Tab=T;
	}
	public int getEtat()
	{
		return etat;
	}
	public int [] getCord()
	{
		return cord;
	}
	public void setEtat(int e)
	{
		etat=e;
	}
	public void setCord(int[] cord)
	{
		if(cord!=null)
		{
			this.cord=cord;
		}
		else
		{
			System.out.println("pointeur cord null");
		}
	}
	public Cellule( int e, int[] cord,int[]tailles,Tab1D father_root)
	{
		super(); // new int[]{} est un tableau de taille 0
		setEtat(e);
		setCord(cord);
		setTab(Tab);
		setTailles(tailles);
		setFather_root(father_root);
		
		
	}
	public Cellule(int [] cord,int []tailles,Tab1D father_root)
	{
		this(0,cord,tailles,father_root);
		c++;
	}
	
//------------------------------------------------------------------------------


}
