package fr.beber.generatormdp.bdd.table;

/**
 * Cette interface permet de créer et d'utiliser les champs pour la table USER.
 *
 * @author Bertrand
 * @version 1.0
 */
public interface TUser {

    public static final String TN_USER = "USER";
    public static final String USER_COLUMN_ID = "_id";
    public static final int USER_NUM_ID = 0;
    public static final String USER_COLUMN_USERNAME = "USERNAME";
    public static final int USER_NUM_USERNAME = 1;
    public static final String USER_COLUMN_MDP = "MDP";
    public static final int USER_NUM_MDP = 2;
    public static final String USER_COLUMN_EMAIL = "EMAIL";
    public static final String REQUETE_CREATION_USER = "CREATE TABLE " + TUser.TN_USER + " " +
            "(" + TUser.USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TUser.USER_COLUMN_USERNAME + " TEXT NOT NULL, "
            + TUser.USER_COLUMN_MDP + " TEXT NOT NULL, "
            + TUser.USER_COLUMN_EMAIL + " TEXT NOT NULL "
            + ");";
    public static final int USER_NUM_EMAIL = 3;
}
