public class G6 extends Voisinage{
    public G6()
    {
        super();
    }
    @Override
    // cellule principale, gauche droite, up down
    public Cellule [] voisinage(Cellule c)
    {
        Cellule [] v=new Cellule[7];
        if(c==null)
        {
            return v;
        }
        Tab1D T=c.getFather_root();
        G4 g4=new G4();
        Cellule [] v_i=g4.voisinage(c);
        System.arraycopy(v_i, 0, v, 0, v_i.length);
        v[5]=T.getCellule( this.v_front_exist(c),  c.getTailles() );
        v[6]=T.getCellule( this.v_back_exist(c),  c.getTailles() );
        return v;
    }
}
