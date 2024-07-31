public class G26 extends Voisinage{
    public G26()
    {
        super();
    }

    //  middle layer  front layer back layer

    @Override
    public Cellule [] voisinage(Cellule c)
    {
        Cellule[] v=new Cellule[27];
        if(c==null)
        {
                return v;
        }
        Tab1D T=c.getFather_root();
        int [] tailles=c.getTailles();
        
       
        G8 g8=new G8();
        int[] cord_front_layer=this.v_front_exist(c);
        int[] cord_back_layer=this.v_back_exist(c);
        Cellule [] v_middle=g8.voisinage(c);
        System.arraycopy(v_middle, 0, v, 0, v_middle.length);
        
        Cellule [] v_front=g8.voisinage(T.getCellule(cord_front_layer, tailles));
        System.arraycopy(v_front,0, v, 9, v_front.length);
        Cellule [] v_back=g8.voisinage(T.getCellule(cord_back_layer, tailles));
        System.arraycopy(v_back,0, v, 18, v_back.length);
        return v;


    }
}
