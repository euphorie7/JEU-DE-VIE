public class ET extends Noeud{

    public ET(Noeud g,Noeud d)
    {
        super(g,d);
    }

    @Override
    public int evaluer(Cellule c) {
        int a=this.getNGauche().evaluer(c);
        int b=this.getNDroite().evaluer(c);

        if(a==0 || b==0)
        {
            return 0;
        }
        return 1;
    }
}
