package entites;

import enums.Mouvement;
import enums.Orientation;

import java.util.Arrays;
import java.util.List;

/**
 * Aventurier
 */
public class Aventurier
{
    /**
     * Nom de l'aventurier
     */
    private String nom;

    /**
     * Orientation de l'aventurier
     */
    private Orientation orientation;

    /**
     * Position de l'aventurier
     */
    private Position position;

    /**
     * Suite de mouvement de l'aventurier
     */
    private String mouvement;

    /**
     * Nombre de trésor ramassé par l'aventurier
     */
    private Integer nbTresor = 0;

    public Aventurier(String nom, Orientation orientation, Position position, String mouvement)
    {
        this.nom = nom;
        this.orientation = orientation;
        this.position = position;
        this.mouvement = mouvement;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public Orientation getOrientation()
    {
        return orientation;
    }

    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public String getMouvement()
    {
        return mouvement;
    }

    public void setMouvement(String mouvement)
    {
        this.mouvement = mouvement;
    }

    public Integer getNbTresor()
    {
        return nbTresor;
    }

    public void setNbTresor(Integer nbTresor)
    {
        this.nbTresor = nbTresor;
    }

    /**
     * Ramasser un trésor
     */
    public void ramasserTresor()
    {
        this.nbTresor++;
    }

    /**
     * Déplacement de l'aventurier
     * Mise à jour du champs mouvement en fonction
     */
    public void consommerMouvement()
    {
        if (this.mouvement.length() > 1)
        {
            mouvement = this.mouvement.substring(1);
        }
        else
        {
            this.mouvement = null;
        }
    }

    /**
     * Récupération de la nouvelle orientation de l'aventurier en fonction de son déplacement
     *
     * @return la nouvelle orientation
     */
    public Orientation getNewOrientation()
    {
        //La liste se construit dans l'ordre qu'elle est définit dans l'enum
        List<Orientation> orientations = Arrays.asList(Orientation.values());

        //Récupération des indexs dans la liste
        int courant = orientations.indexOf(this.orientation);
        int suiv = courant == orientations.size() - 1 ? 0 : courant + 1;
        int prec = courant == 0 ? orientations.size() - 1 : courant - 1;

        //Si l'aventurier va vers la gauche
        if (Mouvement.G.name().equals(this.mouvement.substring(0, 1)))
        {
            return orientations.get(prec);
        }
        //Si l'aventurier va vers la droite
        if (Mouvement.D.name().equals(this.mouvement.substring(0, 1)))
        {
            return orientations.get(suiv);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "A" + " - " + nom
                + " - " + position.getAxeHorizontal()
                + " - " + position.getAxeVertical()
                + " - " + orientation
                + " - " + nbTresor;
    }
}
