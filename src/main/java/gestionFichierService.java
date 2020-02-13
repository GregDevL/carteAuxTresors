import entites.Aventurier;
import entites.Carte;
import entites.Case;
import entites.Position;
import enums.Orientation;
import enums.TypeCase;
import enums.TypeElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class gestionFichierService
{
    private static final String SEPARATEUR = " - ";

    /**
     * Initialisation de la carte du jeu
     *
     * @param ligne       la ligne du fichier en entrant
     * @param carte       la carte du jeu
     * @param aventuriers la liste des aventuriers
     */
    public static void initPlateau(String ligne, Carte carte, List<Aventurier> aventuriers)
    {
        String[] ligneSplit;
        ligneSplit = ligne.split(SEPARATEUR);

        if (ligne.startsWith(TypeElement.C.name()))
        {
            //ligneSplit[1] renvoie la position horizontale
            //ligneSplit[2] renvoie la position verticale
            carte.initCarte(Integer.parseInt(ligneSplit[1]), Integer.parseInt(ligneSplit[2]));
        }
        else if (ligne.startsWith(TypeElement.M.name()))
        {
            //ligneSplit[1] renvoie la position horizontale
            //ligneSplit[2] renvoie la position verticale
            Position position = new Position(Integer.parseInt(ligneSplit[1]), Integer.parseInt(ligneSplit[2]));
            carte.getTerrain().put(position, new Case(TypeCase.MONTAGNE));
        }
        else if (ligne.startsWith(TypeElement.T.name()))
        {
            //ligneSplit[1] renvoie la position horizontale
            //ligneSplit[2] renvoie la position verticale
            Position position = new Position(Integer.parseInt(ligneSplit[1]), Integer.parseInt(ligneSplit[2]));
            //ligneSplit[3] renvoie le nombre de trésor présent sur la case
            carte.getTerrain().put(position, new Case(TypeCase.TRESOR, Integer.parseInt(ligneSplit[3])));
        }
        else if (ligne.startsWith(TypeElement.A.name()))
        {
            //ligneSplit[2] renvoie la position horizontale
            //ligneSplit[3] renvoie la position verticale
            Position position = new Position(Integer.parseInt(ligneSplit[2]), Integer.parseInt(ligneSplit[3]));

            //La case sur laquelle l'aventurier est placé n'est plus libre
            carte.getTerrain().get(position).setLibre(false);

            //ligneSplit[1] renvoie le nom de l'aventurier
            //ligneSplit[4] renvoie l'orientation de l'aventurier
            //ligneSplit[5] renvoie la séquence de mouvement de l'aventurier
            aventuriers.add(new Aventurier(ligneSplit[1], Orientation.valueOf(ligneSplit[4]), position, ligneSplit[5]));
        }
    }

    /**
     * Ecriture dans le fichier de sortie de l'état du jeu en fin de partie
     *
     * @param carte       La carte du jeu
     * @param aventuriers Les aventuriers de la partie
     * @throws IOException Exception sur le fichier
     */
    public static void ecritureFichierSortie(Carte carte, List<Aventurier> aventuriers) throws IOException
    {
        //Initialisation du writer
        PrintWriter ecriture;
        ecriture = new PrintWriter(new BufferedWriter(new FileWriter("sortie/fichierSortant.txt")));

        //Ecriture des informations de la carte
        ecriture.println(carte.toString());

        //Ecriture des infromations des cases et aventuriers
        getCasesMontagne(ecriture, carte);
        getCasesTresor(ecriture, carte);
        getAventuriers(ecriture, aventuriers);

        ecriture.close();
    }

    /**
     * Ecriture des cases de type montagne
     *
     * @param carte La carte du jeu
     */
    public static void getCasesMontagne(PrintWriter ecriture, Carte carte)
    {
        carte.getTerrain().entrySet().stream()
                .filter(a -> TypeCase.MONTAGNE.equals(a.getValue().getTypeCase()))
                .forEach(positionCaseEntry -> ecriture.println(positionCaseEntry.getValue().getTypeCase() + " - "
                        + positionCaseEntry.getKey().toString()));
    }

    /**
     * Ecriture des cases de type trésor
     *
     * @param carte La carte du jeu
     */
    public static void getCasesTresor(PrintWriter ecriture, Carte carte)
    {
        carte.getTerrain().entrySet().stream()
                .filter(a -> TypeCase.TRESOR.equals(a.getValue().getTypeCase()))
                .forEach(positionCaseEntry -> ecriture.println(positionCaseEntry.getValue().getTypeCase() + " - "
                        + positionCaseEntry.getKey().toString() + " - "
                        + positionCaseEntry.getValue().getNbTresor()));
    }

    /**
     * Ecriture des aventuriers
     *
     * @param aventuriers La liste des aventuriers
     */
    public static void getAventuriers(PrintWriter ecriture, List<Aventurier> aventuriers)
    {
        aventuriers.forEach(
                x -> ecriture.println(x.toString()));
    }
}
