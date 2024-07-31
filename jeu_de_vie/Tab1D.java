
import java.util.Arrays;

public class Tab1D {

	 private Tab1D [] tab ;
	 private int [] descend_cord;
	 protected Tab1D descend_root; // les pointeurs du tableau multidimensionnel 
	 public Tab1D [] getTab()
	 {
		 return tab;
	 }
	 public void setTab(Tab1D T,int i)
	 {
		 tab[i]=T;
	 }

	 public int [] getCord()
	 {
		 return descend_cord;
	 }
	 public Tab1D()
	 {
		 
	 }
	 protected Tab1D(int d,int [] tailles,int [] cord,Tab1D root)
	 {
		
		 
		 
		 int size=tailles.length;
		 if(tailles !=null)
		 {
			if (root == null) {
				this.descend_root = this;
			} else {
				this.descend_root = root;
			}
			 
			 this.descend_cord =cord;
			 if(d>0   )
			 {
				 tab=new Tab1D[tailles[size-d]];
				 for(int i=0;i<tailles[size-d];i++)
				 {
					 int [] temp_cord=new int[size-d+1];
					 if(this.descend_cord!=null)
					 {
						 for(int j=0;j<size-d;j++)
						 {
							try
							{
								temp_cord[j]=this.descend_cord[j]; // Essaie d'accéder à l'indice 0
							}
							catch (ArrayIndexOutOfBoundsException e)
							{
								 System.out.println("Erreur: tentative d'accès à un indice qui n'existe pas dans le tableau.");
							}
						 }
					 }
					 temp_cord[size-d]=i;
					 tab[i]= new Tab1D(d-1,tailles,(temp_cord),descend_root);
				 }
			 }
			 else if( d==0 ) // tailles.length tres necessaire pour eviter le o
			 {		
				 tab=new Tab1D[1];
				 tab[0]=new Cellule(cord,tailles,descend_root);
			 }
		 }
		 
	 }	
	 public Tab1D(int [] tailles)
	 {
		 
			 this(tailles.length,tailles,null,null);

		
	 }
	 //--------------------------------------------------------------------


		public Cellule getCellule(int d,int cord[], int [] tailles)
		{
			Cellule c=null;
			if (cord.length != tailles.length || d < 0 || d > cord.length) {
				System.out.println("Coordonnées invalides ou dimension incorrecte.");
				return c;
			}
			for(int i=0;i<cord.length;i++)
			{
				if(cord[i]<0 || cord[i]>=tailles[i])
				{
					System.out.println("Ces coordonnées n'existent pas dans ce tab.");
					return c;
				}
			}
			if(d>0)
			{
			
				
				Tab1D [] temp=this.getTab();
				return	temp[cord[cord.length-d]].getCellule(d-1,cord,tailles);
			
				 
			}
			else if (d==0)
			{
				return (Cellule)this.getTab() [0];

			}

			return c;
		}
		public Cellule getCellule(int []cord,int [] tailles)
		{

			if(cord==null)
			{
				return null;
			}
			return this.getCellule(cord.length,cord,tailles);
		}
	public  void parcoursT(int profendeur ,int [] tailles,GrilleGraphique grid)
	{
		Tab1D [] T=this.tab;
		if(profendeur==tailles.length -1) //derniere diemension avant la dimension 0 qui est une cellule
		{
			for(int i =0;i<tailles[profendeur];i++) // je parcours ce tableau qui contient les cellule
			{
				Cellule c= (Cellule)T[i].getTab()[0];
				//System.out.println( Arrays.toString(c.getTab()) );
				//System.out.println( c.getEtat());
				if(grid != null  && c.getEtat()==1)
				{
					int posx = i;
					int posy  =0;
					grid.colorierCase_1D(posx, posy);
				}


			}
		}
		else
		{
			for(int i=0;i<tailles[profendeur];i++)
			{
				T[i].parcoursT(profendeur+1,tailles,grid);
			}
		}

	}
	public void ajourner(int profendeur, int[] tailles, Tab1D montabajour, Regle r,int [][] mines) {
		if (profendeur == tailles.length - 1) {
			for (int i = 0; i < tailles[profendeur]; i++) {
				Cellule c = (Cellule) tab[i].getTab()[0];
				int[] cordC = c.getCord();
				Cellule c_a_j = montabajour.getCellule(cordC, tailles);
				c_a_j.setEtat(r.evaluer(c));
				if(mines!=null)//cas de 2 dimensions
				{
					mines[cordC[0]][cordC[1]]=r.evaluer(c);
				}

			}
		} else {
			for (int i = 0; i < tailles[profendeur]; i++) {
				tab[i].ajourner(profendeur + 1, tailles, montabajour, r,mines);
			}
		}
	}

	public void ajourner_3D(int profendeur, int[] tailles, Tab1D montabajour, Regle r,int [][] mines,String Coupe, int indice) {
		if (profendeur == tailles.length - 1) {
			for (int i = 0; i < tailles[profendeur]; i++) {
				Cellule c = (Cellule) tab[i].getTab()[0];
				int[] cordC = c.getCord();
				Cellule c_a_j = montabajour.getCellule(cordC, tailles);
				c_a_j.setEtat(r.evaluer(c));
				switch (Coupe) {
				case "X":
					if(mines!=null && cordC[0]==indice) //cas à 2 dimensions
					{
						mines[cordC[1]][cordC[2]]=r.evaluer(c);
					}

					break;
				case "Y":
					if(mines!=null && cordC[1]==indice) //cas à 2 dimensions
				{
					mines[cordC[0]][cordC[2]]=r.evaluer(c);
				}

					break;
				case "Z":
					if(mines!=null && cordC[2]==indice) //cas à 2 dimensions
				{
					mines[cordC[0]][cordC[1]]=r.evaluer(c);
				}
					break;
			}
				

			}
		} else {
			for (int i = 0; i < tailles[profendeur]; i++) {
				tab[i].ajourner_3D(profendeur + 1, tailles, montabajour, r,mines,Coupe,indice);
			}
		}
	}
}
