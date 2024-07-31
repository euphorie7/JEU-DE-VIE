
public class G2 extends Voisinage{
    public G2()
    {
        super();
    }
@Override
// cellule principale , gauche droite
    public Cellule [] voisinage(Cellule c)
    {
        Cellule[] v=new Cellule[3];
        if(c==null)
        {
            return v;
        }
        Tab1D T=c.getFather_root();
        System.out.println(T);
        
        v[0]=c;
        v[1]=T.getCellule( this.v_g_exist(c) , c.getTailles() );
        v[2]=T.getCellule( this.v_d_exist(c),  c.getTailles() );


        return v;
    }
}
