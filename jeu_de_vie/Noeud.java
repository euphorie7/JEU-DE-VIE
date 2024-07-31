public abstract class Noeud {

    protected Noeud NGauche;
    protected Noeud NDroite;
    protected void setDroite (Noeud d)
    {
        NDroite=d;
    }

    protected void setGauche (Noeud g)
    {
        NGauche=g;
    }
    public Noeud getNDroite()
    {
        return NDroite;
    }
    public Noeud getNGauche()
    {
        return NGauche;
    }

    public Noeud(Noeud g,Noeud d)
    {
        setGauche(g);
        setDroite(d);
    }
    public Noeud()
    {
        // pour la classe regle
    }
    public abstract int evaluer(Cellule  c);



}
