import java.util.Arrays;
public class COMPTER extends Feuille{


    Voisinage V;
    public COMPTER(Voisinage G)

    {

        super();
        this.V=G;
    }
    public void setVoisinage(Voisinage G)
    {
        this.V=G;
    }
    @Override
    public int evaluer(Cellule c)
    {
        int cmptr=0;
        Cellule [] list_voisins=V.voisinage(c);
        for(Cellule s : list_voisins) // depuis 1 parceque v[O] c'est la cellule elle meme
        {
            if(s!=null && s.getEtat()==1 ) // c'est possible qu'il ya pas des voisins et la fonction responsable retourne null
            {
                cmptr++;

            }
        }
        return cmptr;
    }
}
