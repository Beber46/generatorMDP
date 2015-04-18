package fr.beber.generatormdp.bdd.table;

/**
 * Cette interface permet de créer et d'utiliser les champs pour la table APP.
 *
 * @author Bertrand
 * @version 1.0
 */
public interface TApplication {

    /**
     * **************************** Création de la table de application
     */
    public static final String TN_APP = "APP";
    public static final String APP_COLUMN_ID = "_id";
    public static final int APP_NUM_ID = 0;
    public static final String APP_COLUMN_NAME = "NAME";
    public static final int APP_NUM_NAME = 1;
    public static final String APP_COLUMN_DES = "DESCRIPTION";
    public static final int APP_NUM_DES = 2;
    public static final String APP_COLUMN_PIC = "PICTURE";
    public static final int APP_NUM_PIC = 3;
    public static final String APP_COLUMN_MDP = "MDP";
    /**
     * Permet de construire la requête pour créer la table <code>APP</code>.
     */
    public static final String REQUETE_CREATION_APP = "CREATE TABLE " + TApplication.TN_APP + " " +
            "(" + TApplication.APP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TApplication.APP_COLUMN_NAME + " TEXT NOT NULL, "
            + TApplication.APP_COLUMN_DES + " TEXT, "
            + TApplication.APP_COLUMN_PIC + " TEXT,"
            + TApplication.APP_COLUMN_MDP + " INTEGER NOT NULL, "
            + " FOREIGN KEY (" + TApplication.APP_COLUMN_MDP + ") REFERENCES " + TMDP.TN_MDP + " (" + TMDP.MDP_COLUMN_ID + "));";
    public static final int APP_NUM_MDP = 4;
}
