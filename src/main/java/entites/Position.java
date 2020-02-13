package entites;

import java.util.Objects;

/**
 * Position
 */
public class Position
{
    /**
     * Position sur l'axe vertical
     */
    private Integer axeHorizontal;

    /**
     * Position sur l'axe vertical
     */
    private Integer axeVertical;

    public Position(Integer axeHorizontal, Integer axeVertical)
    {
        this.axeHorizontal = axeHorizontal;
        this.axeVertical = axeVertical;
    }

    public Integer getAxeHorizontal()
    {
        return axeHorizontal;
    }

    public void setAxeHorizontal(Integer axeHorizontal)
    {
        this.axeHorizontal = axeHorizontal;
    }

    public Integer getAxeVertical()
    {
        return axeVertical;
    }

    public void setAxeVertical(Integer axeVertical)
    {
        this.axeVertical = axeVertical;
    }

    @Override
    public boolean equals(Object o)
    {

        if (o == this)
        {
            return true;
        }
        if (!(o instanceof Position))
        {
            return false;
        }
        Position position = (Position) o;
        return axeHorizontal.equals(position.axeHorizontal) &&
                Objects.equals(axeVertical, position.axeVertical);

    }

    @Override
    public int hashCode()
    {
        return Objects.hash(axeHorizontal, axeVertical);
    }

    @Override
    public String toString()
    {
        return axeHorizontal + " - " + axeVertical;
    }
}
