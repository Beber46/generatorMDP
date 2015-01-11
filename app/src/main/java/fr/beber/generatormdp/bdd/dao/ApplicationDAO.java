package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.util.QueryBuilder;

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
            BDD.APP_COLUMN_PIC,
            BDD.APP_COLUMN_MDP
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
     * Permet de récupérer la liste des {@link Application} par name croissants.
     *
     * @return La liste de {@link Application} trouvée.
     */
    @Override
    public List<Application> getAll() {
       return this.getAll(true);
    }

    /**
     * Permet de récupérer la liste des {@link Application}.
     *
     * @param orderBy <code>TRUE</code> si order by par nom d'application
     * @return La liste de {@link Application} trouvée.
     */
    public List<Application> getAll(boolean orderBy) {
        Log.d(this.getClass().getName(), "Entree");

        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(BDD.TN_APP);
        if(orderBy)
            queryBuilder.setOrderBy(mColumn[1]);

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");

        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(BDD.TN_APP);
        queryBuilder.addConstraint(""+mColumn[0]+" = ?",String.valueOf(id));

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(final Application application) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], application.getName());
        contentValues.put(mColumn[2], application.getDescription()!=null?application.getDescription():"");
        contentValues.put(mColumn[3], application.getPicture()!=null?application.getPicture():"");
        contentValues.put(mColumn[4], application.getMdp());

        return mBDD.insert(BDD.TN_APP, null, contentValues);
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
        contentValues.put(mColumn[4], application.getMdp());

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
        application.setMdp(cursor.getInt(BDD.APP_NUM_MDP));

        return application;
    }

    /**
     * Retourne tous les champs de table en un seul string, util pour les selects.
     * @return la selection.
     */
    private String getAllParams(){
        String retour = "";

        for(int i = 0; i<mColumn.length;i++){
            retour = retour + mColumn[i];

            if(i!=(mColumn.length-1))
                retour = retour + ", ";
        }

        return retour;
    }

}
