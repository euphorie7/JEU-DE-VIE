public class SI extends Noeud{
   
	Noeud m;
     public void setMiddle(Noeud m)
    {
        this.m=m;
    }
	public SI(Noeud g,Noeud m,Noeud d)
    {
        super(g,d);
        setMiddle(m);
    }
   

    @Override
    public int evaluer(Cellule c) {
        int a=super.getNGauche().evaluer(c);

        if(a!=0)
        {
        	return this.m.evaluer(c);
        }
       
        return super.getNDroite().evaluer(c);
    }
}
