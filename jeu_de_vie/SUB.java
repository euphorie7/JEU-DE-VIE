public class SUB extends Noeud{
    public SUB(Noeud g,Noeud d)
    {
        super(g,d);
    }

    @Override
    public int evaluer(Cellule  c) {
        int a=this.getNGauche().evaluer(c);
        int b=this.getNDroite().evaluer(c);
        return a-b;
    }
}