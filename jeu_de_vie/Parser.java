import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 
public class Parser {
    public int dimensions;
    public Regle r;
    public int indice;
    public int rand=-1; //par defaut il n'y a pas d'initialisation aleatoire sauf si on le demande
    String Coupe=null;
    public String nomVoisinage = null;
    public int[] tailles;
    public List<int[]> CordCelluleInitiales = new ArrayList<>();
    public List<int[]> CordVoisinage = new ArrayList<>();
    private Document doc; // Définition de doc comme variable de classe

    public Parser(String xmlFile) throws Exception {
        parse(xmlFile);
    }

    public void extract(String xElement, List<int[]> capsule) {
        NodeList nodeList = doc.getElementsByTagName(xElement);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            NodeList valeurNodes = ((Element) node).getElementsByTagName("Valeur");
            for (int i = 0; i < valeurNodes.getLength(); i++) {
                String[] parts = valeurNodes.item(i).getTextContent().split(",");
                int[] tab = new int[parts.length];
                for (int j = 0; j < parts.length; j++) {
                    tab[j] = Integer.parseInt(parts[j].trim());
                }
                capsule.add(tab);
            }
        }
    }
   

    public void processVoisinages() {
        Node voisinagesNode = doc.getElementsByTagName("Voisinages").item(0);
        if (voisinagesNode != null) {
            Node nomVoisinageNode = ((Element) voisinagesNode).getElementsByTagName("Nom").item(0);
            if (nomVoisinageNode != null) {
                nomVoisinage = nomVoisinageNode.getTextContent();
                extract("Voisinages", CordVoisinage);
               
             
            }
        }

    }

    private void parse(String xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(new File(xmlFile)); // Initialisation de doc

        dimensions = Integer.parseInt(doc.getElementsByTagName("Dimensions").item(0).getTextContent());
        if(((Element) doc.getElementsByTagName("Coupe").item(0))!=null)
        {
            Coupe=((Element) doc.getElementsByTagName("Coupe").item(0)).getElementsByTagName("Valeur").item(0).getTextContent();
            indice=Integer.parseInt(((Element) doc.getElementsByTagName("Coupe").item(0)).getElementsByTagName("indice").item(0).getTextContent());
        }
        if(((Element) doc.getElementsByTagName("Random").item(0))!=null)
        {
            rand=Integer.parseInt(((Element) doc.getElementsByTagName("Random").item(0)).getElementsByTagName("Valeur").item(0).getTextContent());
        }
        Node champTailles = doc.getElementsByTagName("Taille").item(0);
        NodeList valeur_list = ((Element) champTailles).getElementsByTagName("Valeur");

        tailles = new int[valeur_list.getLength()];
        for (int i = 0; i < tailles.length; i++) {
            Node valeur = valeur_list.item(i);
            tailles[i] = Integer.parseInt(valeur.getTextContent());
        }
        processVoisinages();
        Node champRegles = doc.getElementsByTagName("Regles").item(0);
        String regle = ((Element) champRegles).getElementsByTagName("Valeur").item(0).getTextContent();
        r = new Regle(regle,this.CordVoisinage);

        extract("CellulesInitiales", CordCelluleInitiales);
        
    }
}
