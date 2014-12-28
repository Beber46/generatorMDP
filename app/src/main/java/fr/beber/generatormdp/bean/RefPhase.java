package fr.beber.generatormdp.bean;

/**
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 */
public class RefPhase {

    /**
     * Identifiant d'une refphase.
     */
    private Integer id;

    /**
     * Nom d'une refphase.
     */
    private String name;

    /**
     * Permet d'obtenir l'identifiant d'une refphase.
     *
     * @return Un identifiant d'une refphase.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant d'une refphase.
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le nom d'une refphase.
     *
     * @return Le nom d'une refphase.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer le nom d'une refphase.
     */
    public void setName(final String name) {
        this.name = name;
    }

    public RefPhase() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefPhase refPhase = (RefPhase) o;

        if (id != null ? !id.equals(refPhase.id) : refPhase.id != null) return false;
        if (name != null ? !name.equals(refPhase.name) : refPhase.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RefPhase[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
