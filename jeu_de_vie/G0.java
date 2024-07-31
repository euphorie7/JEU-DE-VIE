public class G0 extends Voisinage{

    public G0()
    {
        super();
    }
    @Override
    public Cellule [] voisinage(Cellule c) //voisinage de c relativement à la table T
    {
        
        Cellule[] v=new Cellule[1];
        if(c==null)
        {
            return v;
        }
        v[0]=c;
        return v;
    }
}
