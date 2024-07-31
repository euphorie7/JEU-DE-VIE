public class EQ extends Noeud{
    public EQ(Noeud g,Noeud d)
    {
        super(g,d);
    }

    @Override
    public int evaluer(Cellule  c) {
        int a = this.getNGauche().evaluer(c);
        int b = this.getNDroite().evaluer(c);
        if (a == b) {
            return 1;
        } else {
            return 0;
        }
    }
}
