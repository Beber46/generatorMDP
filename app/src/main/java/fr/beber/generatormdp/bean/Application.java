package fr.beber.generatormdp.bean;

/**
 * Cette classe permet de définir le bean APP.
 *
 * @author Bertrand
 * @version 1.0
 */
public class Application {

    /**
     * Identifiant d'une application.
     */
    private Integer id;

    /**
     * Nom d'une application.
     */
    private String name;

    /**
     * Description d'une application.
     */
    private String description;

    /**
     * Image d'une application.
     */
    private String picture;

    /**
     * Clé étrangère pour référencer l'application.
     */
    private Integer mdp;

    /**
     * Permet d'obtenir l'identifiant d'une application.
     * @return L'identifiant.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant d'une application.
     * @param id Identifiant d'une application
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir l'identifiant d'une application.
     * @return Le nom de l'application.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer le nom de l'application.
     * @param name Nom de l'application.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Permet d'obtenir le descriptif de l'application.
     * @return Le descriptif de l'application.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de changer le descriptif de l'application.
     * @param description Description de l'application.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Permet d'obtenir l'image de l'application.
     * @return L'image de l'application.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Permet de changer l'image de l'application.
     * @param picture Image de l'application.
     */
    public void setPicture(final String picture) {
        this.picture = picture;
    }

    /**
     * Permet d'obtenir l'identifiant du mot de passe.
     * @return L'identifiant du mot de passe.
     */
    public Integer getMdp() {
        return mdp;
    }

    /**
     * Permet de changer l'identifiant du mot de passe de l'application.
     * @param mdp Mot de passe.
     */
    public void setMdp(Integer mdp) {
        this.mdp = mdp;
    }

    public Application() {

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (mdp != null ? !mdp.equals(that.mdp) : that.mdp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Application[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", mdp='" + mdp + '\'' +
                ']';
    }
}
