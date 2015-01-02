package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bean.Application;

import java.util.ArrayList;
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
     * Permet d'obtenir la liste des nom des applications.
     * @return La liste des noms.
     */
    public List<String> getAllName() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_APP, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return getListNameApplication(cursor);
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
        contentValues.put(mColumn[2], application.getDescription()!=null?application.getDescription():"");
        contentValues.put(mColumn[3], application.getPicture()!=null?application.getPicture():"");

        mBDD.insert(BDD.TN_APP, null, contentValues);
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
        contentValues.put(mColumn[2], application.getDescription()!=null?application.getDescription():"");
        contentValues.put(mColumn[3], application.getPicture()!=null?application.getPicture():"");

        mBDD.update(BDD.TN_APP, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(application.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(BDD.TN_APP, mColumn[0] + "=?", new String[]{String.valueOf(id)});
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
        final String description = cursor.getString(BDD.APP_NUM_DES);
        application.setDescription(description.length()>0?description:null);
        final String picture = cursor.getString(BDD.APP_NUM_PIC);
        application.setPicture(picture.length()>0?picture:null);

        return application;
    }

    /**
     * Permet d'obtenir le nom des applications.
     * @param cursor à convertir.
     * @return le nom de l'applicaiton.
     */
    public String getNameApplication(final Cursor cursor) {
        return cursor.getString(BDD.APP_NUM_NAME);
    }

    /**
     * Permet de convertire un {@link android.database.Cursor} en liste de {@link String}.
     *
     * @param cursor à convertir.
     * @return Une liste de {@link String} trouvé.
     */
    public List<String> getListNameApplication(final Cursor cursor) {
        final List<String> liste = new ArrayList<String>();

        if (cursor.getCount() == 0)
            return liste;

        cursor.moveToFirst();
        do {
            String exec = this.getNameApplication(cursor);
            liste.add(exec);
        } while (cursor.moveToNext());

        cursor.close();

        return liste;
    }

}
