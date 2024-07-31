import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 
public class vPersonalise extends Voisinage  {

    public List<int[]> CordVoisinage;
    public vPersonalise(List<int[]> CordVoisinage)
    {
        super();
        this.CordVoisinage=CordVoisinage;
    }
    @Override
    public Cellule[] voisinage(Cellule c) {
        Cellule[] v = new Cellule[this.CordVoisinage.size()];
        Tab1D T=c.getFather_root();
        int[] tailles = c.getTailles();
        int[] cord_cellule = c.getCord();
        for (int j = 0; j < CordVoisinage.size(); j++) {
            int[] new_cord = new int[cord_cellule.length];
            boolean outOfBounds = false;
            for (int i = 0; i < CordVoisinage.get(j).length; i++) {
                new_cord[i] = cord_cellule[i] + CordVoisinage.get(j)[i];
                if (new_cord[i] < 0 || new_cord[i] > tailles[i]-1) {
                    outOfBounds = true; // En dehors des limites
                    break;
                }
            }
            if (!outOfBounds) {
                v[j] = T.getCellule(new_cord, tailles); // Crée une nouvelle cellule avec les nouvelles coordonnées
            } else {
                v[j] = null; // En dehors des limites
            }
        }
        return v; // Retourne le tableau de cellules voisines
    }
}


