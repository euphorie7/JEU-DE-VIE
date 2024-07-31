import java.io.File;
import java.util.Scanner;
public class Main
{

    public static void main(String []args)
    {
           Scanner scanner = new Scanner(System.in);
        File currentDir = new File(".");
        File[] files = currentDir.listFiles((dir, name) -> name.endsWith(".xml"));

        if (files == null || files.length == 0) {
            System.out.println("Aucun fichier de configuration trouvé dans le répertoire actuel.");
            return;
        }

        System.out.println("Fichiers de configuration disponibles :");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }

        System.out.print("Sélectionnez le numéro d'un fichier de configuration pour lancer la simulation (entrez le numéro) : ");
        int choix = scanner.nextInt();
        if (choix < 1 || choix > files.length) {
            System.out.println("Choix invalide.");
            return;
        }

        File selectedFile = files[choix - 1];
        System.out.println("Fichier sélectionné : " + selectedFile.getName());

        // Lire et analyser le fichier de configuration
        JeuDeVie jeu;
        try {
            jeu = new JeuDeVie(selectedFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier de configuration.");
            e.printStackTrace();
            return;
        }
    }



}