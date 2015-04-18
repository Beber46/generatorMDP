package fr.beber.generatormdp.bean;

/**
 * Cette classe permet de définir le bean USER.
 *
 * @author Bertrand
 * @version 1.0
 */
public class User {

    /**
     * Identifiant d'un mdp.
     */
    private Integer id;

    /**
     * Pseudo de l'utilisateur.
     */
    private String username;

    /**
     * Mot de passe d'un mdp.
     */
    private String mdp;

    /**
     * Email.
     */
    private String email;


    public User() {

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
     * Permet d'obtenir le pseudo.
     *
     * @return Le pseudo.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Permet de changer le pseudo.
     *
     * @param username Pseudo.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Permet d'obtenir l'email.
     *
     * @return L'email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permet de changer l'email.
     *
     * @param email Email.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (mdp != null ? !mdp.equals(user.mdp) : user.mdp != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mdp='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ']';
    }
}
