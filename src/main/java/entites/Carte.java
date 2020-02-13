package entites;

import enums.TypeCase;

import java.util.HashMap;

/**
 * Carte du jeu
 */
public class Carte
{
    /**
     * Le terrain du jeu
     */
    private HashMap<Position, Case> terrain;

    /**
     * Dimension de la carte sur l'axe horizontal
     */
    private Integer dimensionHorizontale;

    /**
     * Dimension de la carte sur l'axe vertical
     */
    private Integer dimensionVerticale;

    public HashMap<Position, Case> getTerrain()
    {
        return terrain;
    }

    public void setTerrain(HashMap<Position, Case> terrain)
    {
        this.terrain = terrain;
    }

    public Integer getDimensionHorizontale()
    {
        return dimensionHorizontale;
    }

    public void setDimensionHorizontale(Integer dimensionHorizontale)
    {
        this.dimensionHorizontale = dimensionHorizontale;
    }

    public Integer getDimensionVerticale()
    {
        return dimensionVerticale;
    }

    public void setDimensionVerticale(Integer dimensionVerticale)
    {
        this.dimensionVerticale = dimensionVerticale;
    }

    /**
     * Initialisation de l'objet carte
     *
     * @param dimensionHorizontale dimension horizontale
     * @param dimensionVerticale   dimension verticale
     */
    public void initCarte(Integer dimensionHorizontale, Integer dimensionVerticale)
    {
        this.initTerrain(dimensionHorizontale, dimensionVerticale);
        this.dimensionHorizontale = dimensionHorizontale;
        this.dimensionVerticale = dimensionVerticale;
    }

    /**
     * Vérification si le déplacement est contenu dans le plateau de jeu
     *
     * @param position La position vers laquelle se diriger
     * @return true si déplacement valide, false sinon
     */
    public boolean verifDeplacement(Position position)
    {
        return position.getAxeVertical() < dimensionVerticale &&
                position.getAxeVertical() >= 0 &&
                position.getAxeHorizontal() >= 0 &&
                position.getAxeHorizontal() < dimensionHorizontale;
    }

    /**
     * Initialisation du terrain
     *
     * @param dimensionHorizontale dimension horziontale
     * @param dimensionVerticale   dimension verticale
     */
    private void initTerrain(Integer dimensionHorizontale, Integer dimensionVerticale)
    {
        this.terrain = new HashMap<>();
        for (int i = 0; i < dimensionHorizontale; i++)
        {
            for (int j = 0; j < dimensionVerticale; j++)
            {
                this.terrain.put(new Position(i, j), new Case(TypeCase.PLAINE));
            }
        }
    }

    @Override
    public String toString()
    {
        return "C" + " - " + dimensionHorizontale + " - " + dimensionVerticale;
    }
}
