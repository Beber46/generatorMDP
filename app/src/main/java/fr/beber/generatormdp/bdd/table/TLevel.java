package fr.beber.generatormdp.bdd.table;

/**
 * Cette classe permet de
 * <p/>
 * @author Beber46
 * @version 1.0
 */
public interface TLevel {

    /**
     * **************************** Création de la table de level
     */
    public static final String TN_LEVEL = "LEVEL";
    public static final String LEVEL_COLUMN_ID = "_id";
    public static final int LEVEL_NUM_ID = 0;
    public static final String LEVEL_COLUMN_NAME = "NAME";
    public static final int LEVEL_NUM_NAME = 1;
    public static final String LEVEL_COLUMN_COLOR = "COLOR";
    /**
     * Permet de construire la requête pour créer la table <code>LEVEL</code>.
     */
    public static final String REQUETE_CREATION_LEVEL = "CREATE TABLE " + TLevel.TN_LEVEL + " " +
            "(" + TLevel.LEVEL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TLevel.LEVEL_COLUMN_COLOR + " TEXT NOT NULL, "
            + TLevel.LEVEL_COLUMN_NAME + " TEXT NOT NULL); ";
    public static final int LEVEL_NUM_COLOR = 2;
}
