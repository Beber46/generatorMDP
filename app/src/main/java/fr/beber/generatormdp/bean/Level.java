package fr.beber.generatormdp.bean;

/**
 * Cette classe permet de définir le bean LEVEL.
 *
 * @author Bertrand
 * @version 1.0
 */
public class Level {

    /**
     * Identifiant d'un level.
     */
    private Integer id;

    /**
     * Nom d'un level.
     */
    private String name;

    /**
     * Couleur d'un level.
     */
    private String color;

    /**
     * Permet d'obtenir l'identifiant d'un level.
     *
     * @return Un identifiant d'un level.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant d'un level.
     * @param id Identifiant d'un level.
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le nom d'un level.
     *
     * @return Le nom d'un level.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer le nom d'un level.
     * @param name Nom d'un level.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Permet d'obtenir la couleur d'un level.
     *
     * @return La couleur d'un level.
     */
    public String getColor() {
        return color;
    }

    /**
     * Permet de changer la couleur d'un level.
     * @param color Couleur d'un level.
     */
    public void setColor(final String color) {
        this.color = color;
    }

    public Level() {

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Level level = (Level) o;

        if (color != null ? !color.equals(level.color) : level.color != null) return false;
        if (id != null ? !id.equals(level.id) : level.id != null) return false;
        if (name != null ? !name.equals(level.name) : level.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Level[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ']';
    }
}
