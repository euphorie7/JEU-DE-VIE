public class NON extends Noeud{
    public NON(Noeud n)
    {
        super(n,null);
    }

    @Override
    public int evaluer(Cellule  c) {
        int a=this.getNGauche().evaluer(c);
        return 1-a;
    }
}
