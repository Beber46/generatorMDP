package fr.beber.generatormdp.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Classe BDD permettant des créer et mettre à jour la base de données. Elle permet également d'accéder aux constantes
 * propres aux tables.
 */
public class BDD extends SQLiteOpenHelper {
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
    private static final String REQUETE_CREATION_LEVEL = "CREATE TABLE " + TN_LEVEL + " " +
            "(" + LEVEL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LEVEL_COLUMN_COLOR + " TEXT NOT NULL, "
            + LEVEL_COLUMN_NAME + " TEXT NOT NULL); ";
    public static final int LEVEL_NUM_COLOR = 2;
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
    private static final String REQUETE_CREATION_MDP = "CREATE TABLE " + TN_MDP + " " +
            "(" + MDP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MDP_COLUMN_LEVEL + " INTEGER NOT NULL, "
            + MDP_COLUMN_MDP + " TEXT NOT NULL,"
            + MDP_COLUMN_DATEDEBUT + " INTEGER NOT NULL,"
            + MDP_COLUMN_NUM + " INTEGER NOT NULL,"
            + MDP_COLUMN_MIN + " INTEGER NOT NULL,"
            + MDP_COLUMN_MAJ + " INTEGER NOT NULL,"
            + MDP_COLUMN_SPEC + " INTEGER NOT NULL,"
            + " FOREIGN KEY (" + MDP_COLUMN_LEVEL + ") REFERENCES " + TN_LEVEL + " (" + LEVEL_COLUMN_ID + "));";
    public static final int MDP_NUM_SPEC = 7;
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
    private static final String REQUETE_CREATION_APP = "CREATE TABLE " + TN_APP + " " +
            "(" + APP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + APP_COLUMN_NAME + " TEXT NOT NULL, "
            + APP_COLUMN_DES + " TEXT, "
            + APP_COLUMN_PIC + " TEXT,"
            + APP_COLUMN_MDP + " INTEGER NOT NULL, "
            + " FOREIGN KEY (" + APP_COLUMN_MDP + ") REFERENCES " + TN_MDP + " (" + MDP_COLUMN_ID + "));";
    public static final int APP_NUM_MDP = 4;
    /**
     * Version de la base de données
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Nom du schéma
     */
    private static final String BASE_NAME = "generatorMDP.db";

    /**
     * Constructeur provenant de l'heritage
     *
     * @param context Le contexte courant.
     * @param factory Définit le factory à utiliser.
     */
    public BDD(final Context context, final CursorFactory factory) {
        super(context, BASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Constructeur provenant de l'héritage
     *
     * @param context Le contexte courant.
     */
    public BDD(final Context context) {
        this(context, null);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(REQUETE_CREATION_LEVEL);
        sqLiteDatabase.execSQL(REQUETE_CREATION_APP);
        sqLiteDatabase.execSQL(REQUETE_CREATION_MDP);
    }

    /**
     * Lorsque l'on change le numéro de version de la base on supprime la table puis on la recrée.
     *
     * @param sqLiteDatabase Nécessaire pour la méthode parente.
     * @param oldVersion     Ancienne version de la base de données.
     * @param newVersion     Nouvelle version de la base de données.
     */
    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int oldVersion, final int newVersion) {

        if (newVersion > DATABASE_VERSION) {
            Log.w(BDD.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_LEVEL + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_MDP + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_APP + ";");
            this.onCreate(sqLiteDatabase);
        }
    }

    //TODO: juste pour les tests.
    public void dropALL(){
        final SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_LEVEL + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_MDP + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_APP + ";");
        this.onCreate(sqLiteDatabase);
        sqLiteDatabase.close();
    }

}
