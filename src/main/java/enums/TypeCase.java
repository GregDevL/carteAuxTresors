package enums;


public enum TypeCase
{
    MONTAGNE("M"),
    TRESOR("T"),
    PLAINE("P");

    private String valeur;

    TypeCase(String valeur)
    {
        this.valeur = valeur;
    }

    public String toString()
    {
        return this.valeur;
    }

}
