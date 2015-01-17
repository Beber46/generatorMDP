package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.QueryBuilder;

import java.util.Calendar;
import java.util.List;

/**
 * Cette classe permet de faire les opérations basiques.
 *
 * @author Bertrand
 * @version 1.0
 */
public class MdpDAO extends Repository<Mdp> {

    /**
     * Champs en base de données de {@link fr.beber.generatormdp.bean.Mdp}
     */
    private static final String[] mColumn = new String[]{
            BDD.MDP_COLUMN_ID,
            BDD.MDP_COLUMN_MDP,
            BDD.MDP_COLUMN_LEVEL,
            BDD.MDP_COLUMN_DATEDEBUT
    };

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     */
    public MdpDAO(final Context context) {
        mSQLOH = new BDD(context);
    }

    /**
     * Constructeur
     *
     * @param sqLiteOpenHelper Définit le sqLiteOpenHelper à utiliser.
     */
    public MdpDAO(final SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLOH = sqLiteOpenHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mdp> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(BDD.TN_MDP);

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mdp getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");

        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(BDD.TN_MDP);
        queryBuilder.addConstraint(""+mColumn[0]+" = ?",String.valueOf(id));

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(final Mdp mdp) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], mdp.getMdp());
        contentValues.put(mColumn[2], mdp.getLevel());
        contentValues.put(mColumn[3], Calendar.getInstance().getTimeInMillis());

        return mBDD.insert(BDD.TN_MDP, null, contentValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Mdp mdp) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], mdp.getMdp());
        contentValues.put(mColumn[2], mdp.getLevel());
        contentValues.put(mColumn[3], mdp.getDateModify().getTimeInMillis());

        mBDD.update(BDD.TN_MDP, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(mdp.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(BDD.TN_MDP, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mdp convertCursorToObject(final Cursor cursor) {
        final Mdp mdp = new Mdp();
        mdp.setId(cursor.getInt(BDD.MDP_NUM_ID));
        mdp.setMdp(cursor.getString(BDD.MDP_NUM_MDP));
        mdp.setLevel(cursor.getInt(BDD.MDP_NUM_LEVEL));
        final Calendar dateModify = Calendar.getInstance();
        dateModify.setTimeInMillis(cursor.getLong(BDD.MDP_NUM_DATEDEBUT));
        mdp.setDateModify(dateModify);

        return mdp;
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
