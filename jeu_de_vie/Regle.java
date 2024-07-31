import java.util.ArrayList;
import java.util.List;
import java.util.Stack; //pile
public class Regle  {

  private Noeud racine;

    public Regle(String regle,List<int[]> CordVoisinage) {
        this.racine = parse(regle,CordVoisinage);
    }


    private Noeud parse(String regle,List<int[]> CordVoisinage) {
        Stack<Noeud> pileDeNoeuds = new Stack<>();
        String[] tokens = regle.split("(?<=[(),])|(?=[(),])");

        for (String token : tokens) {
            token = token.trim(); // Supprime les espaces autour du token
            if (token.isEmpty()) {
                continue; // Ignore les tokens vides
            }
           // System.out.println(token +"-->");
            switch (token) {
                case "SI":
                    pileDeNoeuds.push(new SI(null, null, null));
                    break;
                case "EQ":
                    pileDeNoeuds.push(new EQ(null, null));
                    break;
                case "SUPEQ":
                    pileDeNoeuds.push(new SUPEQ(null, null));
                    break;
                case "NON":
                    pileDeNoeuds.push(new NON(null));
                    break;
                case "OU":
                    pileDeNoeuds.push(new OU(null, null));
                    break;
                case "SUP":
                    pileDeNoeuds.push(new SUP(null, null));
                    break;
                case "ET":
                    pileDeNoeuds.push(new ET(null, null));
                    break;
                case "COMPTER":
                    pileDeNoeuds.push(new COMPTER(null));
                    break;
               
                    
                case "(":
                    break;
                case ")":
                    System.out.println(pileDeNoeuds.toString());
                    Noeud operation = pileDeNoeuds.pop();
                    System.out.println(operation.getClass());
                    if(operation instanceof COMPTER || operation instanceof NON )
                    {
                      
                        pileDeNoeuds.push(operation);
                        break;
                    }
                    else if(pileDeNoeuds.size()>0)
                    {   
                       
                        Noeud premiere_depilation = pileDeNoeuds.pop(); 
                      //  System.out.println(premiere_depilation.getClass());
                      if(premiere_depilation instanceof NON)
                      {
                        premiere_depilation.setGauche(operation);
                        pileDeNoeuds.push(premiere_depilation);
                        break;
                      } 

                        Noeud deuxieme_depilation = pileDeNoeuds.pop();
                      //  System.out.println(deuxieme_depilation.getClass());
                        
                        if(pileDeNoeuds.size()>0)
                        {
                            System.out.println(pileDeNoeuds.toString());
                            Noeud troisieme_depilation = pileDeNoeuds.pop();
                            if (troisieme_depilation instanceof SI && deuxieme_depilation.getNDroite()!=null && deuxieme_depilation.getNGauche()!=null) {
                                ((SI) troisieme_depilation).setDroite(operation);
                                ((SI) troisieme_depilation).setMiddle(premiere_depilation);
                                ((SI) troisieme_depilation).setGauche(deuxieme_depilation);  
                                pileDeNoeuds.push(troisieme_depilation);
                                System.out.println(pileDeNoeuds.toString());
                                break;
                            }
                            pileDeNoeuds.push(troisieme_depilation);
                            if (deuxieme_depilation instanceof EQ || deuxieme_depilation instanceof ET || deuxieme_depilation instanceof SUPEQ || deuxieme_depilation instanceof SUP || deuxieme_depilation instanceof OU)
                            {
                                System.out.println(pileDeNoeuds.toString());
                                
                                deuxieme_depilation.setGauche(premiere_depilation);
                                deuxieme_depilation.setDroite(operation);
                            }
                            pileDeNoeuds.push(deuxieme_depilation);
                            System.out.println("ehm "+pileDeNoeuds.toString());
                            break;
                            // si non on suffi avec 
                           

                        }  
                        else //necessairement une des suivants
                        {
                            System.out.println("la");
                            if (deuxieme_depilation instanceof EQ || deuxieme_depilation instanceof ET || deuxieme_depilation instanceof SUPEQ || deuxieme_depilation instanceof SUP || deuxieme_depilation instanceof OU)
                            {
                                deuxieme_depilation.setGauche(premiere_depilation);
                                deuxieme_depilation.setDroite(operation);
                            }
                            pileDeNoeuds.push(deuxieme_depilation);
                            System.out.println(pileDeNoeuds.toString());
                            break;

                        }
                        
                    }
                    
                    
                   
                    break;
                case ",":
                    break;
                default:
                    if(token.equals("G0") ||token.equals("G2")|| token.equals("G4")|| token.equals("G6") || token.equals("G8")||token.equals("G26"))
                    {
                        System.out.println("avec voisinage :"+pileDeNoeuds.toString());
                        Noeud lastNode = pileDeNoeuds.peek(); // Dernier élément de la pile
                        if (lastNode instanceof COMPTER) {
                            Voisinage v = CreerVoisinage(token);
                            ((COMPTER) lastNode).setVoisinage(v);
                        }else
                        {
                                throw new IllegalArgumentException("Voisinage non applicable: " + token);
                        }
                    }
                    else  if (token.matches("\\d+")) { // Un entier positif
                        Noeud lastNode = pileDeNoeuds.peek(); // Dernier élément de la pile
                        if (lastNode instanceof NON) {      
                          pileDeNoeuds.push(new NON(new Entier(Integer.parseInt(token))));
                        }
                        else
                        {
                            pileDeNoeuds.push(new Entier(Integer.parseInt(token)));
                        }
                    } 
                    else if(CordVoisinage !=null ){
                        Noeud lastNode = pileDeNoeuds.peek(); // Dernier élément de la pile
                        if (lastNode instanceof COMPTER) {
                            Voisinage v=new vPersonalise(CordVoisinage);
                            ((COMPTER) lastNode).setVoisinage(v);
                            System.out.println("Passer par la !!");
                             System.out.println(pileDeNoeuds.toString());
                         
                        }else
                        {
                                throw new IllegalArgumentException("Voisinage non applicable: " + token);
                        }
                    }
                    else
                    {
                            throw new IllegalArgumentException("Token inconnu: " + token);
                    }
                    break;
            }
        }
        return pileDeNoeuds.pop();
    }
     
    


    private Voisinage CreerVoisinage(String token){
        Voisinage v;
        switch ( token ) {
            case "G0":
                v= new G0();
                break;
            case "G2":
                v= new G2();
                break;
            case "G4":
                v= new G4();
                break;
            case "G6":
                v= new G6();
                break;
            case "G8":
                v= new G8();
                break;
            case "G26":
                v= new G26();
                break;
            default:
                System.out.println("problemmme");
                v=null;
        }
        return v;
    }
    

    public int evaluer(Cellule c)
    {
       return  racine.evaluer(c);
    }
}
