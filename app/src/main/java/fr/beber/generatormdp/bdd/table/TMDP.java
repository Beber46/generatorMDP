package fr.beber.generatormdp.bdd.table;

/**
 * Cette interface permet de créer et d'utiliser les champs pour la table MDP.
 *
 * @author Bertrand
 * @version 1.0
 */
public interface TMDP {

    /**
     * **************************** Création de la table de Mot de passe
     */
    public static final String TN_MDP = "MDP";
    public static final String MDP_COLUMN_ID = "_id";
    public static final int MDP_NUM_ID = 0;
    public static final String MDP_COLUMN_MDP = "MDP";
    public static final int MDP_NUM_MDP = 1;
    public static final String MDP_COLUMN_LEVEL = "LEVEL";
    public static final int MDP_NUM_LEVEL = 2;
    public static final String MDP_COLUMN_DATEDEBUT = "DATEDEBUT";
    public static final int MDP_NUM_DATEDEBUT = 3;
    public static final String MDP_COLUMN_NUM = "NUM";
    public static final int MDP_NUM_NUM = 4;
    public static final String MDP_COLUMN_MIN = "MIN";
    public static final int MDP_NUM_MIN = 5;
    public static final String MDP_COLUMN_MAJ = "MAJ";
    public static final int MDP_NUM_MAJ = 6;
    public static final String MDP_COLUMN_SPEC = "SPEC";
    /**
     * Permet de construire la requête pour créer la table <code>MDP</code>.
     */
    public static final String REQUETE_CREATION_MDP = "CREATE TABLE " + TMDP.TN_MDP + " " +
            "(" + TMDP.MDP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TMDP.MDP_COLUMN_LEVEL + " INTEGER NOT NULL, "
            + TMDP.MDP_COLUMN_MDP + " TEXT NOT NULL,"
            + TMDP.MDP_COLUMN_DATEDEBUT + " INTEGER NOT NULL,"
            + TMDP.MDP_COLUMN_NUM + " INTEGER NOT NULL,"
            + TMDP.MDP_COLUMN_MIN + " INTEGER NOT NULL,"
            + TMDP.MDP_COLUMN_MAJ + " INTEGER NOT NULL,"
            + TMDP.MDP_COLUMN_SPEC + " INTEGER NOT NULL,"
            + " FOREIGN KEY (" + TMDP.MDP_COLUMN_LEVEL + ") REFERENCES " + TLevel.TN_LEVEL + " (" + TLevel.LEVEL_COLUMN_ID + "));";
    public static final int MDP_NUM_SPEC = 7;
}
