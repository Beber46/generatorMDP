package fr.beber.generatormdp.bean;

import fr.beber.generatormdp.util.DateFormat;

import java.util.Calendar;

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
     * Clé étrangère pour référencer le niveau.
     */
    private Integer level;

    /**
     * Date de création du mot de passe.
     */
    private Calendar dateModify;

    /**
     * Vérifie si le mot de passe comporte des nombres.
     */
    private Boolean isNumeric = Boolean.FALSE;

    /**
     * Vérifie si le mot de passe comporte des minuscules.
     */
    private Boolean isMin = Boolean.FALSE;

    /**
     * Vérifie si le mot de passe comporte des majuscules.
     */
    private Boolean isMaj = Boolean.FALSE;

    /**
     * Vérifie si le mot de passe comporte des caractères spéciaux.
     */
    private Boolean isSpec = Boolean.FALSE;

    public Mdp() {

    }

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

    /**
     * Permet d'obtenir la date de création.
     * @return La date de création.
     */
    public Calendar getDateModify() {
        return dateModify;
    }

    /**
     * Permet de changer la date de création.
     * @param dateModify Date de création.
     */
    public void setDateModify(final Calendar dateModify) {
        this.dateModify = dateModify;
    }

    /**
     * @return <code>TRUE</code> si numérique.
     */
    public Boolean getIsNumeric() {
        return isNumeric;
    }

    public void setIsNumeric(Boolean isNumeric) {
        this.isNumeric = isNumeric;
    }

    /**
     * @return <code>TRUE</code> si minuscule.
     */
    public Boolean getIsMin() {
        return isMin;
    }

    public void setIsMin(Boolean isMin) {
        this.isMin = isMin;
    }

    /**
     * @return <code>TRUE</code> si majuscule.
     */
    public Boolean getIsMaj() {
        return isMaj;
    }

    public void setIsMaj(Boolean isMaj) {
        this.isMaj = isMaj;
    }

    /**
     * @return <code>TRUE</code> si spécial.
     */
    public Boolean getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Boolean isSpec) {
        this.isSpec = isSpec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mdp mdp1 = (Mdp) o;

        if (dateModify != null ? !dateModify.equals(mdp1.dateModify) : mdp1.dateModify != null) return false;
        if (id != null ? !id.equals(mdp1.id) : mdp1.id != null) return false;
        if (isMaj != null ? !isMaj.equals(mdp1.isMaj) : mdp1.isMaj != null) return false;
        if (isMin != null ? !isMin.equals(mdp1.isMin) : mdp1.isMin != null) return false;
        if (isNumeric != null ? !isNumeric.equals(mdp1.isNumeric) : mdp1.isNumeric != null) return false;
        if (isSpec != null ? !isSpec.equals(mdp1.isSpec) : mdp1.isSpec != null) return false;
        if (level != null ? !level.equals(mdp1.level) : mdp1.level != null) return false;
        if (mdp != null ? !mdp.equals(mdp1.mdp) : mdp1.mdp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (dateModify != null ? dateModify.hashCode() : 0);
        result = 31 * result + (isNumeric != null ? isNumeric.hashCode() : 0);
        result = 31 * result + (isMin != null ? isMin.hashCode() : 0);
        result = 31 * result + (isMaj != null ? isMaj.hashCode() : 0);
        result = 31 * result + (isSpec != null ? isSpec.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mdp[" +
                "id=" + id +
                ", mdp='" + mdp + '\'' +
                ", level=" + level +
                ", dateModify=" + DateFormat.getCalendarFormat(dateModify,"dd-MM-yyyy H:m:s") +
                ", isNumeric=" + isNumeric +
                ", isMin=" + isMin +
                ", isMaj=" + isMaj +
                ", isSpec=" + isSpec +
                ']';
    }
}
