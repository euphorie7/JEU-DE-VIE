
public abstract class Feuille extends Noeud	 {


	public Feuille()
	{
		super( null,null );


	}

	public abstract  int evaluer(Cellule c) ;
	/*
		if(this.getCel()!=null)
		{
			return this.getCel().getEtat();
		}
		else
		{
			return getNbr();
		}
	}
	*/

}
