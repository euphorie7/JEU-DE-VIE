import java.util.Arrays;
import java.util.Random ;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class  JeuDeVie {
	public static List<int[]> generateAllCoords(int[] tailles) {
		List<int[]> result = new ArrayList<>();
		generateCoordsRecursive(tailles, new int[tailles.length], 0, result);
		return result;
	}

	// Fonction récursive pour générer les coordonnées
	private static void generateCoordsRecursive(int[] tailles, int[] currentCoord, int profendeur, List<int[]> result) {
		if (profendeur == tailles.length) {
			result.add(currentCoord.clone());
			return;
		}

		for (int i = 0; i < tailles[profendeur]; i++) {
			currentCoord[profendeur] = i;
			generateCoordsRecursive(tailles, currentCoord, profendeur + 1, result);
		}
	}
	public static void run_1D(Tab1D ancient,Tab1D nouveau,int[] tailles,GrilleGraphique grid,Regle r)
	{
		List<int[]> lesCords=generateAllCoords(tailles);
		int t=1;
		while(t< grid.getHauteur())
		{
			//x largeur y hauteur
			ancient.ajourner(0, tailles, nouveau, r,null);
			for(int [] c : lesCords)
			{
				if (grid != null && nouveau.getCellule(c,tailles).getEtat() == 1) {

						grid.colorierCase_1D(c[0], t);

				}

			}
			ancient=nouveau;
			nouveau= new Tab1D(tailles);
			try {
            Thread.sleep(20); // Pause 
        } catch (InterruptedException e) {
            // Gérer toute interruption de sommeil si nécessaire
            e.printStackTrace();
        }
		t++;

		}
	}
	public static void run_2D(Tab1D ancient,Tab1D nouveau,int[] tailles,GrilleGraphique grid,Regle r)
	{
		//x largeur y hauteur
		
		int t=0;
		int [][] mines;		
		while(t<500) {
			mines = new int[grid.getLargeur()][grid.getHauteur()];
			ancient.ajourner(0, tailles, nouveau, r, mines);
			grid.colorierCase_2D(mines);
			ancient = nouveau;
			nouveau = new Tab1D(tailles);		
           try {
            Thread.sleep(50); // Pause 
        } catch (InterruptedException e) {
            // Gérer toute interruption de sommeil si nécessaire
            e.printStackTrace();
        }
                t++;        
			System.out.println(t);
		}
	}
	public static void run_3D(Tab1D ancient,Tab1D nouveau,int[] tailles,GrilleGraphique grid,Regle r,String Coupe,int indice)
	{
		//x largeur y hauteur
		List<int[]> lesCords=generateAllCoords(tailles);
		int t=0;
		int [][] mines;
		while(t<500) {
			mines = new int[grid.getLargeur()][grid.getHauteur()];
			ancient.ajourner_3D(0, tailles, nouveau, r, mines,Coupe,indice);
			grid.colorierCase_2D(mines);
			ancient = nouveau;
			nouveau = new Tab1D(tailles);		
           try {
            Thread.sleep(100); // Pause de 2000 millisecondes (2 secondes)
        } catch (InterruptedException e) {
            // Gérer toute interruption de sommeil si nécessaire
            e.printStackTrace();
        }
                t++;        
			System.out.println(t);
		}
	}

	public   JeuDeVie(String  path) throws Exception {


			GrilleGraphique grid;
			int largeur=60;//par defaut
			int hauteur=80;//par defaut pour dim 1
			int dimensions=0;
			String Coupe=null;
			int indice=0;
            int rand=-1;
			Regle r=null;
			int[] tailles=null;
			int posx=-1,posy=-1;
			List<int[]> CordCelluleInitiales=null;

		 	try {
          	Parser p = new Parser(path);
				dimensions=p.dimensions;
				CordCelluleInitiales = p.CordCelluleInitiales;
    			r=p.r;
    			Coupe=p.Coupe;
                rand=p.rand;
    			tailles=p.tailles;
						
            // Reste de votre code ici
			} catch (Exception e) {
					e.printStackTrace();
			}
			if(r==null)
			{
				System.out.println("La règle n'est pas bien precisée dans le fichier XML");
			}
			else if(CordCelluleInitiales==null)
			{
				System.out.println("Vous n'avaez pas necessairement besoin d'initialiser les cases , mais est ce que c'est le cas ?!");
			}
			Tab1D ancient = new Tab1D(tailles);
			Tab1D nouveau = new Tab1D(tailles);
		if(dimensions==1 && tailles!=null)
		{
			largeur = tailles[0];
			hauteur = tailles[1];
			grid = new GrilleGraphique(largeur, hauteur, 8);

			int[][] cords_case = new int[CordCelluleInitiales.size()][tailles.length];
            if(rand>0)
            {
                 Random random = new Random();
                 for (int x = 0; x < largeur; x++) {
                    
                        int s= random.nextInt(100) < rand ? 1 : 0;
                        if(s==1)
                        {
                                int [] cord_random={x,0};
                                ancient.getCellule(cord_random, tailles).setEtat(1);
                                grid.colorierCase_1D(x,0);

                        }
                        
                        
                    
                }
            }
			for (int i = 0; i < CordCelluleInitiales.size(); i++) 
			{
					System.out.println(Arrays.toString(CordCelluleInitiales.get(i)));
					System.out.println(Arrays.toString(tailles));
					
					posx = CordCelluleInitiales.get(i)[0];
					posy = CordCelluleInitiales.get(i)[1];
					
					if (posy < 0 || posy >= hauteur || posx < 0 || posx >= largeur) {
							System.out.println("Les coordonnées des points initialisés doivent être dans les limites de la grille");
							continue; // Ignore cette cellule si elle est hors limites
					}
					
					int[] cord_cellule = new int[]{posx, posy};
					cords_case[i] = cord_cellule;
					
					System.out.println(Arrays.toString(cords_case) + ":" + Arrays.toString(cord_cellule));
					ancient.getCellule(cord_cellule, tailles).setEtat(1);
					grid.colorierCase_1D(cord_cellule[0],cord_cellule[1]);
			}
			run_1D(ancient,nouveau,tailles,grid,r);

		}else if (dimensions==2&& tailles!=null)
		{
			
				largeur = tailles[0];
				hauteur = tailles[1];
				grid = new GrilleGraphique(largeur, hauteur, 8);
                int[][] mines = new int[largeur][hauteur];
                if(rand>0)
                {
                    Random random = new Random();
                    for (int x = 0; x < largeur; x++) {
                        for (int y = 0; y < hauteur; y++) {
                            int s= random.nextInt(100) < rand ? 1 : 0;
                            if(s==1)
                            {
                                    int [] cord_random={x,y};
                                    ancient.getCellule(cord_random, tailles).setEtat(1);
                                    mines[x][y]=1;
                            }
                        }
                    }
                    grid.colorierCase_2D(mines);
                }
				
				for (int i = 0; i < CordCelluleInitiales.size(); i++) 
				{
						System.out.println(Arrays.toString(CordCelluleInitiales.get(i)));
						System.out.println(Arrays.toString(tailles));
						
						posx = CordCelluleInitiales.get(i)[0];
						posy = CordCelluleInitiales.get(i)[1];
						
						if (posy < 0 || posy >= hauteur || posx < 0 || posx >= largeur) {
								System.out.println("Les coordonnées des points initialisés doivent être dans les limites de la grille");
								continue; // Ignore cette cellule si elle est hors limites
						}
						
						int[] cord_cellule = new int[]{posx, posy};
						mines[posx][posy]=1;
						
						
						ancient.getCellule(cord_cellule, tailles).setEtat(1);
						
				}
				grid.colorierCase_2D(mines);
				run_2D(ancient,nouveau,tailles,grid,r);
			
		}
		else if(dimensions==3&& tailles!=null)
		{

			switch (Coupe) {
				case "X":
					largeur = tailles[1];
					hauteur = tailles[2];

					break;
				case "Y":
					largeur = tailles[0];
					hauteur = tailles[2];
					break;
				case "Z":
					largeur = tailles[0];
					hauteur = tailles[1];
					break;
			}
            int[][] mines = new int[largeur][hauteur];
            grid = new GrilleGraphique(largeur, hauteur, 8);
            if(rand>0)
                {
                    Random random = new Random();
                    for (int x = 0; x < tailles[0]; x++) {
                        for (int y = 0; y < tailles[1]; y++) {
                            for (int z = 0; z < tailles[2]; z++) {
                                int s= random.nextInt(100) < rand ? 1 : 0;
                                if(s==1)
                                {
                                        int [] cord_random={x,y,z};
                                        ancient.getCellule(cord_random, tailles).setEtat(1);
                                        switch (Coupe){
                                            case "X":
                                                if(x==indice)
                                                {
                                                    mines[y][z]=1;
                                                }
                                                break;
                                            case "Y":
                                                if(y==indice)
                                                {
                                                    mines[x][z]=1;
                                                }
                                                break;
                                            case "Z":
                                                if(z==indice)
                                                {
                                                    mines[x][y]=1;
                                                }
                                                break;    
                                        }
                                }
                            }
                        }
                    }
                    grid.colorierCase_2D(mines);
                }
			
			
			int indic_x,indic_y,indic_z;
			for (int i = 0; i < CordCelluleInitiales.size(); i++) 
				{
						System.out.println(Arrays.toString(CordCelluleInitiales.get(i)));
						System.out.println(Arrays.toString(tailles));
						
						indic_x = CordCelluleInitiales.get(i)[0];
						indic_y = CordCelluleInitiales.get(i)[1];
						indic_z = CordCelluleInitiales.get(i)[2];
						
						
						
						int[] cord_cellule = new int[]{indic_x, indic_y,indic_z};
						switch (Coupe)
						{
							case "X":
								posx=CordCelluleInitiales.get(i)[1];
								posy=CordCelluleInitiales.get(i)[2];
								break;
							case "Y":
								posx=CordCelluleInitiales.get(i)[0];
								posy=CordCelluleInitiales.get(i)[2];
								break;
							case "Z":
								posx=CordCelluleInitiales.get(i)[0];
								posy=CordCelluleInitiales.get(i)[1];
								break;
							default :
								System.out.println("Coupe non lisible");
						}
						if (posy < 0 || posy >= hauteur || posx < 0 || posx >= largeur) {
								System.out.println("Les coordonnées des points initialisés doivent être dans les limites de la grille");
								continue; // Ignore cette cellule si elle est hors limites
						}
						mines[posx][posy]=1;
						ancient.getCellule(cord_cellule, tailles).setEtat(1);
				}
				grid.colorierCase_2D(mines);
				run_3D(ancient,nouveau,tailles,grid,r,Coupe,indice);
		}
		else
		{
			throw new IllegalArgumentException("Dimensions pas bien initialisé ");
		}
	
	}
	/*	Regle r = new Regle("SI(ET(EQ(COMPTER(G2),1),EQ(COMPTER(G0),0)),1,0)");
		//,
		

		int posx=25;
		int posy=0;
		//On crée un objet ChampGraphique de 51 cases de large, et 60 de haut
		GrilleGraphique grid = new GrilleGraphique(51, 60, 8);

		//initialisation


		//grid.colorierCase(posx, posy);
		int[] tailles = {grid.getLargeur()};// autre dimension
		Tab1D ancient = new Tab1D(tailles);
		Tab1D nouveau = new Tab1D(tailles);


		Random rnbr = new Random();
		int[] cord_x = {posx,posy};
		for(int i=0; i<30; i++)
		{
			//on tire une case au hasard dans la grille
			//posx = rnbr.nextInt(grid.getLargeur());
			//posy = rnbr.nextInt(grid.getHauteur());
			cord_x = new int[]{posx};
			ancient.getCellule(cord_x, tailles).setEtat(1);
			grid.colorierCase_1D(posx,posy);


			//et on la colorie en rouge

		}



		run_1D(ancient,nouveau,tailles,grid,r);



*/


	
}
