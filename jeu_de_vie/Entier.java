public class Entier extends Feuille{

    int entier;
    public Entier(int Nbr)

    {
        super();
        entier=Nbr;
    }
    @Override
    public int evaluer(Cellule  c)
    {
        return entier;
    }
}
