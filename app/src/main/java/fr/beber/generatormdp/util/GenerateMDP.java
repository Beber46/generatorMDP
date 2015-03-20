package fr.beber.generatormdp.util;

import android.content.Context;
import fr.beber.generatormdp.bdd.dao.LevelDAO;
import fr.beber.generatormdp.bean.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de générer un mot de passe aléatoire.
 *
 * @author Bertrand
 * @version 1.0
 */
public class GenerateMDP {

    /**
     * <code>TRUE</code> pour générer des numériques.
     */
    private final Boolean isNumeric;

    /**
     * <code>TRUE</code> pour générer des majuscules.
     */
    private final Boolean isMajuscule;

    /**
     * <code>TRUE</code> pour générer des minuscules.
     */
    private final Boolean isMinuscule;

    /**
     * <code>TRUE</code> pour générer des caractères spéciaux.
     */
    private final Boolean isSpecial;

    /**
     * Mémorisation du context courant.
     */
    private final Context context;

    /**
     * Taille du mot de passe.
     */
    private final Integer size;

    /**
     * Niveau de diffculté du mot de passe.
     */
    private Integer level = Integer.valueOf(0);

    private int nombreAleatoire = 0;


    public GenerateMDP(final Context context, final Boolean isNumeric, final Boolean isMinuscule, final Boolean isMajuscule, final Boolean isSpecial, final Integer size) {
        this.isNumeric = isNumeric;
        this.isMinuscule = isMinuscule;
        this.isMajuscule = isMajuscule;
        this.isSpecial = isSpecial;
        this.size = size;

        this.context = context;

    }

    /**
     * Permet de récupérer le niveau d'un mot de passe qui vient d'être généré par {@link #getPassWord()}.
     * @return le niveau du mot de passe.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Permet de créer le mot de passe.
     * @return le mot de passe généré.
     */
    public String getPassWord(){

        final List<String> operation = this.getTraitement();

        String password = "";
        final int max = operation.size();
        int position = this.getRandomValue(0,max-1);

        while(password.length()<this.size){
            final String op = operation.get(position);
            password = password + op.charAt(this.getRandomValue(0, op.length()-1));

            position = this.getRandomValue(0,max-1);
        }

        return password;
    }

    /**
     * Permet de créer la liste d'élément nécessaire pour la création du mot de passe.
     * @return une liste de string.
     */
    private List<String> getTraitement(){


        final List<String> operation = new ArrayList<String>();

        if(Boolean.TRUE.equals(this.isNumeric))
            operation.add("0123456789");

        if(Boolean.TRUE.equals(this.isMinuscule))
            operation.add("azertyuiopmlkjhgfdsqwxcvbn");

        if(Boolean.TRUE.equals(this.isMajuscule))
            operation.add("QSDFGHJKLMAZERTYUIOPWXCVBN");

        if(Boolean.TRUE.equals(this.isSpecial))
            operation.add("#!?$%&*");

        final LevelDAO levelDAO = new LevelDAO(this.context);
        levelDAO.openOnlyRead();
        final List<Level> levelList = levelDAO.getAll();
        levelDAO.close();

        if(operation.size()<=2)
            level = levelList.get(0).getId(); //min
        else if (operation.size()<=3)
            level = levelList.get(1).getId(); //medium
        else
            level = levelList.get(2).getId(); //high

        return operation;
    }

    /**
     * Permet de générer un nombre vraiment aléatoire.
     * @param min le nombre minimal.
     * @param max le nombre maximal.
     * @return le nombre généré.
     */
    private int getRandomValue(final int min, final int max){

        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenerateMDP)) return false;

        GenerateMDP that = (GenerateMDP) o;

        if (nombreAleatoire != that.nombreAleatoire) return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (isMajuscule != null ? !isMajuscule.equals(that.isMajuscule) : that.isMajuscule != null) return false;
        if (isMinuscule != null ? !isMinuscule.equals(that.isMinuscule) : that.isMinuscule != null) return false;
        if (isNumeric != null ? !isNumeric.equals(that.isNumeric) : that.isNumeric != null) return false;
        if (isSpecial != null ? !isSpecial.equals(that.isSpecial) : that.isSpecial != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isNumeric != null ? isNumeric.hashCode() : 0;
        result = 31 * result + (isMajuscule != null ? isMajuscule.hashCode() : 0);
        result = 31 * result + (isMinuscule != null ? isMinuscule.hashCode() : 0);
        result = 31 * result + (isSpecial != null ? isSpecial.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + nombreAleatoire;
        return result;
    }

    @Override
    public String toString() {
        return "GenerateMDP[" +
                "isNumeric=" + isNumeric +
                ", isMajuscule=" + isMajuscule +
                ", isMinuscule=" + isMinuscule +
                ", isSpecial=" + isSpecial +
                ", context=" + context +
                ", size=" + size +
                ", level=" + level +
                ", nombreAleatoire=" + nombreAleatoire +
                ']';
    }
}
