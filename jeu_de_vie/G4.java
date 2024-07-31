public class G4 extends Voisinage{
    public G4()
    {
        super();
    }
    @Override
    // cellule principale, gauche droite, up down
    public Cellule [] voisinage(Cellule c)
    {
        Cellule [] sortie=new Cellule[5];
        if(c==null)
        {
            return sortie;
        }
        Tab1D T=c.getFather_root();
        int [] tailles=c.getTailles();
        
        
        G2 g2=new G2();
        Cellule [] v_i=g2.voisinage(c);
        System.arraycopy(v_i, 0, sortie, 0, v_i.length);


        sortie[3]=T.getCellule( this.v_up_exist(c),  tailles );
        sortie[4]=T.getCellule( this.v_down_exist(c),  tailles );

        return sortie;
    }
}
