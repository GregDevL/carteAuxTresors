import entites.Aventurier;
import entites.Carte;
import entites.Position;
import enums.Mouvement;
import enums.Orientation;
import enums.TypeCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Application
{

    public static void main(String[] args) throws IOException
    {
        Carte carte = new Carte();
        List<Aventurier> aventuriers = new ArrayList<>();
        BufferedReader lecteurAvecBuffer = null;
        String ligne;

        //Ouverture du fichier entrant
        try
        {
            File file = new File(Application.class.getResource("fichierEntrant.txt").getFile());
            lecteurAvecBuffer = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException exc)
        {
            System.out.println("Erreur d'ouverture du fichier");
        }

        //Lecture du fichier ligne par ligne
        if (lecteurAvecBuffer != null)
        {
            while ((ligne = lecteurAvecBuffer.readLine()) != null)
            {
                gestionFichierService.initPlateau(ligne, carte, aventuriers);
            }
            lecteurAvecBuffer.close();
        }

        ListIterator<Aventurier> it = aventuriers.listIterator();
        List<Aventurier> aventurierPourAffichageList = new ArrayList<>();

        //Boucle de jeu
        while (it.hasNext())
        {
            Aventurier aventurier = it.next();

            gestionDeplacementAventurier(aventurier, carte);

            //Si l'aventurier ne dispose plus de mouvement
            //Alors il est retirer de l'itérateur et mis dans une autre liste
            if (aventurier.getMouvement() == null)
            {
                aventurierPourAffichageList.add(aventurier);
                it.remove();
            }
            //Si on arrive au dernier aventurier de la liste
            // Alors on reprend au début de la liste
            if (!it.hasNext())
            {
                it = aventuriers.listIterator();
            }
        }

        //Ecriture du fichier de sortie
        gestionFichierService.ecritureFichierSortie(carte, aventurierPourAffichageList);

        System.out.println("L'exécution du programme est terminée");
    }

    /**
     * Gestion des déplacements des aventuriers sur la carte
     *
     * @param aventurier L'aventurier qui se déplace
     * @param carte      la carte du jeu
     */
    private static void gestionDeplacementAventurier(Aventurier aventurier, Carte carte)
    {
        Position position = null;
        String mouvement = aventurier.getMouvement().substring(0, 1);

        //Si l'aventurier avance
        if (Mouvement.A.name().equals(mouvement))
        {
            //Définition de la nouvelle future potentielle position de l'aventurier
            if (Orientation.N.equals(aventurier.getOrientation()))
            {
                position = new Position(aventurier.getPosition().getAxeHorizontal(), aventurier.getPosition().getAxeVertical() - 1);
            }
            else if (Orientation.S.equals(aventurier.getOrientation()))
            {
                position = new Position(aventurier.getPosition().getAxeHorizontal(), aventurier.getPosition().getAxeVertical() + 1);
            }
            else if (Orientation.E.equals(aventurier.getOrientation()))
            {
                position = new Position(aventurier.getPosition().getAxeHorizontal() + 1, aventurier.getPosition().getAxeVertical());
            }
            else if (Orientation.O.equals(aventurier.getOrientation()))
            {
                position = new Position(aventurier.getPosition().getAxeHorizontal() - 1, aventurier.getPosition().getAxeVertical());
            }

            //Vérification si le déplacement est dans la carte
            if (position != null && carte.verifDeplacement(position))
            {
                deplacementSurCaseSpecial(aventurier, position, carte);
            }

        }

        //Si l'aventurier change juste son orientation
        else if (Mouvement.D.name().equals(mouvement) || Mouvement.G.name().equals(mouvement))
        {
            aventurier.setOrientation(aventurier.getNewOrientation());
        }

        //Consommation du mouvement de l'aventurier
        aventurier.consommerMouvement();

    }

    /**
     * Déplacement de l'aventurier sur une nouvelle case
     *
     * @param aventurier L'aventurier qui veut se déplacer
     * @param position   La nouvelle position de l'aventurier
     * @param carte      La carte du jeu
     */
    private static void deplacementSurCaseSpecial(Aventurier aventurier, Position position, Carte carte)
    {
        //Si la case sur laquelle l'aventurier veut se déplacer n'est pas libre ou est une montagne
        //Alors l'aventurier reste sur la case ou il était
        if (!carte.getTerrain().get(position).isLibre() || TypeCase.MONTAGNE.equals(carte.getTerrain().get(position).getTypeCase()))
        {
            return;
        }
        //Si la case sur laquelle il se déplace est un trésor
        if (TypeCase.TRESOR.equals(carte.getTerrain().get(position).getTypeCase()))
        {
            //Gestion de la case
            boolean consommationTresor = carte.getTerrain().get(position).consommerTresor();

            //Si un trésor a été ramassé sur la case
            if (consommationTresor)
            {
                //Ajout d'un trésor à l'aventurier
                aventurier.ramasserTresor();
            }
        }

        //Changement du statut libre de la case sur laquelle il était
        carte.getTerrain().get(aventurier.getPosition()).setLibre(true);

        //Faire avancer l'aventurier si la case sur laquelle il veut aller est une plaine ou un trésor
        aventurier.setPosition(position);

        //Changement du statut libre de la case sur laquelle il se trouve nouvellement
        carte.getTerrain().get(position).setLibre(false);
    }
}

