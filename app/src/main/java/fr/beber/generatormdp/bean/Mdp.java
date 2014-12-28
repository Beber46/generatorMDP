package fr.beber.generatormdp.bean;

/**
 * Cette classe permet de définir le bean MDP.
 *
 * @author Bertrand
 * @version 1.0
 */
public class Mdp {

    /**
     * Identifiant d'un mdp.
     */
    private Integer id;

    /**
     * Mot de passe d'un mdp.
     */
    private String mdp;

    /**
     * Clé étrangère pour référencer l'application.
     */
    private Integer app;

    /**
     * Clé étrangère pour référencer le niveau.
     */
    private Integer level;

    /**
     * Permet d'obtenir l'identifiant d'un mdp.
     *
     * @return Un identifiant d'un mdp.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant d'un mdp.
     *
     * @param id Identifiant d'un mdp.
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le mot de passe d'un mdp.
     *
     * @return Le mot de passe d'un mdp.
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Permet de changer le mot de passe d'un mdp.
     *
     * @param mdp Mot de passe.
     */
    public void setMdp(final String mdp) {
        this.mdp = mdp;
    }

    /**
     * Permet d'obtenir l'identifiant d'une application.
     * @return L'identifiant d'une application.
     */
    public Integer getApp() {
        return app;
    }

    /**
     * Permet de changer l'identifiant d'une application.
     * @param app Identifiant de l'application.
     */
    public void setApp(final Integer app) {
        this.app = app;
    }

    /**
     * Permet d'obtenir l'identifiant d'un level.
     * @return L'identifiant d'un level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Permet de changer l'identifiant d'un level.
     * @param level Identifiant d'un leve.
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }

    public Mdp() {

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mdp mdp = (Mdp) o;

        if (app != null ? !app.equals(mdp.app) : mdp.app != null) return false;
        if (id != null ? !id.equals(mdp.id) : mdp.id != null) return false;
        if (level != null ? !level.equals(mdp.level) : mdp.level != null) return false;
        if (mdp != null ? !mdp.equals(mdp.mdp) : mdp.mdp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mdp[" +
                "id=" + id +
                ", mdp='" + mdp + '\'' +
                ", app=" + app +
                ", level=" + level +
                ']';
    }
}
