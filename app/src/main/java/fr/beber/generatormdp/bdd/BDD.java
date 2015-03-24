package fr.beber.generatormdp.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.table.TApplication;
import fr.beber.generatormdp.bdd.table.TLevel;
import fr.beber.generatormdp.bdd.table.TMDP;

/**
 * Classe BDD permettant des créer et mettre à jour la base de données. Elle permet également d'accéder aux constantes
 * propres aux tables.
 */
public class BDD extends SQLiteOpenHelper {

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
        sqLiteDatabase.execSQL(TLevel.REQUETE_CREATION_LEVEL);
        sqLiteDatabase.execSQL(TApplication.REQUETE_CREATION_APP);
        sqLiteDatabase.execSQL(TMDP.REQUETE_CREATION_MDP);
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
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TLevel.TN_LEVEL + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TMDP.TN_MDP + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TApplication.TN_APP + ";");
            this.onCreate(sqLiteDatabase);
        }
    }

    //TODO: juste pour les tests.
    public void dropALL(){
        final SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TLevel.TN_LEVEL + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TMDP.TN_MDP + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TApplication.TN_APP + ";");
        this.onCreate(sqLiteDatabase);
        sqLiteDatabase.close();
    }

}
