package fr.beber.generatormdp.bdd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import fr.beber.generatormdp.util.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstract permettant de définir les principales méthodes à utiliser dans les classes repos.
 *
 * @author Beber46
 * @version 1.0
 */
public abstract class Repository<T> implements IRepository<T> {
    /**
     * Utiliser pour ouvrir ou fermer une connexion BDD
     */
    protected static SQLiteDatabase mBDD;
    /**
     * Utiliser pour ouvrir ou fermer une connexion BDD
     */
    protected static SQLiteOpenHelper mSQLOH;

    /**
     * Constructeur
     */
    public Repository() {

    }

    /**
     * Ouverture d'une connexion BDD en mode écriture.
     */
    public void open() {
        mBDD = mSQLOH.getWritableDatabase();
    }

    /**
     * Overture d'une connexion BDD en mode lecture.
     */
    public void openOnlyRead() {
        mBDD = mSQLOH.getReadableDatabase();
    }

    /**
     * Fermeture d'une connexion BDD.
     */
    public void close() {
        mBDD.close();
    }

    /**
     * Permet de convertire un {@link android.database.Cursor} en liste de {@link T}.
     *
     * @param cursor à convertir.
     * @return Une liste de {@link T} trouvé.
     */
    public List<T> convertCursorToListObject(final Cursor cursor) {
        final List<T> liste = new ArrayList<T>();

        if (cursor.getCount() == 0)
            return liste;

        cursor.moveToFirst();
        do {
            T exec = this.convertCursorToObject(cursor);
            liste.add(exec);
        } while (cursor.moveToNext());

        cursor.close();

        return liste;
    }

    /**
     * Permet de faire un count sur le nombre de ligne que retourne le <code>queryBuilder</code>.
     * @param queryBuilder La requête à exécuter.
     * @return Le nombre de ligne obtenue.
     */
    public int count(final QueryBuilder queryBuilder){

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());
        return cursor.getCount();
    }

    /**
     * Permet de convertire un {@link android.database.Cursor} en {@link T}.
     *
     * @param cursor à convertir.
     * @return {@link T} trouvé.
     */
    public T convertCursorToOneObject(final Cursor cursor) {
        cursor.moveToFirst();

        final T exec = this.convertCursorToObject(cursor);

        cursor.close();
        return exec;
    }
}
