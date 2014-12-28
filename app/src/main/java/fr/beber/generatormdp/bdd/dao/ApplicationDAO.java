package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bean.Application;

import java.util.List;

/**
 * Cette classe permet de faire les opérations basiques.
 *
 * @author Bertrand
 * @version 1.0
 */
public class ApplicationDAO extends Repository<Application> {

    /**
     * Champs en base de données de {@link fr.beber.generatormdp.bean.Application}
     */
    private static final String[] mColumn = new String[]{
            BDD.APP_COLUMN_ID,
            BDD.APP_COLUMN_NAME,
            BDD.APP_COLUMN_DES,
            BDD.APP_COLUMN_PIC
    };


    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     */
    public ApplicationDAO(final Context context) {
        mSQLOH = new BDD(context);
    }

    /**
     * Constructeur
     *
     * @param sqLiteOpenHelper Définit le sqLiteOpenHelper à utiliser.
     */
    public ApplicationDAO(final SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLOH = sqLiteOpenHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_APP, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_APP, mColumn, id.toString(), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Application application) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], application.getName());
        contentValues.put(mColumn[2], application.getDescription());
        contentValues.put(mColumn[3], application.getPicture());

        mBDD.insert(BDD.TN_LEVEL, null, contentValues);
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Application application) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], application.getName());
        contentValues.put(mColumn[2], application.getDescription());
        contentValues.put(mColumn[3], application.getPicture());

        mBDD.update(BDD.TN_LEVEL, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(application.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(BDD.TN_LEVEL, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application convertCursorToObject(final Cursor cursor) {
        final Application application = new Application();
        application.setId(cursor.getInt(BDD.APP_NUM_ID));
        application.setName(cursor.getString(BDD.APP_NUM_NAME));
        application.setDescription(cursor.getString(BDD.APP_NUM_DES));
        application.setPicture(cursor.getString(BDD.APP_NUM_PIC));

        return application;
    }
}
