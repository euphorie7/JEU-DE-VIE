public class OU extends Noeud{
    public OU(Noeud g,Noeud d)
    {
        super(g,d);
    }

    @Override
    public int evaluer(Cellule  c) {
        int a=this.getNGauche().evaluer(c);
        int b=this.getNDroite().evaluer(c);
        if(a==1 || b==1)
        {
            return 1;
        }
        return 0;
    }
}
