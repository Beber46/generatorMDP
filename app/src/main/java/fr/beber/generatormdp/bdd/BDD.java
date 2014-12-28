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
     * **************************** Création de la table de référence pour la phrase
     */
    public static final String TN_PHRASE = "REFPHRASE";
    public static final String PHRASE_COLUMN_ID = "_id";
    public static final int PHRASE_NUM_ID = 0;
    public static final String PHRASE_COLUMN_NAME = "NAME";
    public static final int PHRASE_NUM_NAME = 1;
    /**
     * Permet de construire la requête pour créer la table <code>REFPHRASE</code>.
     */
    private static final String REQUETE_CREATION_PHRASE = "CREATE TABLE " + TN_PHRASE + " " +
            "(" + PHRASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PHRASE_COLUMN_NAME + " TEXT NOT NULL); ";
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
     * Constructeur provenant de l'heritage
     *
     * @param context Le contexte courant.
     */
    public BDD(final Context context) {
        this(context, null);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(REQUETE_CREATION_PHRASE);
    }

    /**
     * Lorsque l'on change le numero de version de la base on supprime la table puis on la recree.
     *
     * @param sqLiteDatabase Nécessaire pour la méthode parente.
     * @param oldVersion     Ancienne version de la base de données.
     * @param newVersion     Nouvelle version de la base de données.
     */
    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int oldVersion, final int newVersion) {

        if (newVersion > DATABASE_VERSION) {
            Log.w(BDD.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_PHRASE + ";");
            this.onCreate(sqLiteDatabase);
        }
    }

}
