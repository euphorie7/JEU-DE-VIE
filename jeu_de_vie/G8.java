public class G8 extends Voisinage{
    public G8()
    {
        super();
    }
    @Override
    // cellule principale, gauche droite, up down, gup dup gdown ddown
    public Cellule [] voisinage(Cellule c)
    {
        Cellule [] v=new Cellule[9];
        if(c==null)
        {
            return v;
        }
        Tab1D T=c.getFather_root();
        int [] tailles=c.getTailles();
        G4 g4=new G4();
        Cellule [] v_i=g4.voisinage(c);
        System.arraycopy(v_i, 0, v, 0, v_i.length);
        v[5]=T.getCellule( this.v_gup_exist(c),  tailles );
        v[6]=T.getCellule( this.v_dup_exist(c),  tailles);
        v[7]=T.getCellule( this.v_gdown_exist(c),  tailles );
        v[8]=T.getCellule( this.v_ddown_exist(c),  tailles );
        return v;
    }
}
