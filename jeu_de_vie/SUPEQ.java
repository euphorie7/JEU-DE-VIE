public class SUPEQ extends Noeud{
    public SUPEQ(Noeud g,Noeud d)
    {
        super(g,d);
    }

    @Override
    public int evaluer(Cellule  c) {
        int a=this.getNGauche().evaluer(c);
        int b=this.getNDroite().evaluer(c);
        if(a>=b)
        {
            return 1;
        }
        return 0;
    }
}