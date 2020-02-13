package entites;

import enums.TypeCase;

/**
 * Réprésentation d'une case de la carte
 */
public class Case
{
    /**
     * Type de la case
     */
    private TypeCase typeCase;

    /**
     * Nombre de trésor présent sur la case (par défaut 0)
     */
    private Integer nbTresor = 0;

    /**
     * Si la case est occupé par un aventurier (par défaut 0)
     */
    private boolean libre = true;

    public Case(TypeCase typeCase)
    {
        this.typeCase = typeCase;
    }

    public Case(TypeCase typeCase, Integer nbTresor)
    {
        this.typeCase = typeCase;
        this.nbTresor = nbTresor;
    }

    public TypeCase getTypeCase()
    {
        return typeCase;
    }

    public void setTypeCase(TypeCase typeCase)
    {
        this.typeCase = typeCase;
    }

    public Integer getNbTresor()
    {
        return nbTresor;
    }

    public void setNbTresor(Integer nbTresor)
    {
        this.nbTresor = nbTresor;
    }

    public boolean isLibre()
    {
        return libre;
    }

    public void setLibre(boolean libre)
    {
        this.libre = libre;
    }

    /**
     * Consommation d'un trésor et modification du type de la case si il n'y a plus de trésor
     *
     * @return true si un trésor a été trouvé, false sinon
     */
    public boolean consommerTresor()
    {
        if (nbTresor > 0)
        {
            this.nbTresor--;
            if (this.nbTresor == 0)
            {
                this.setTypeCase(TypeCase.PLAINE);
            }
            return true;
        }
        //Dans le cas ou la case aurait été initialisé comme trésor mais avec 0 trésors
        if (this.nbTresor == 0)
        {
            this.setTypeCase(TypeCase.PLAINE);
        }
        return false;
    }
}