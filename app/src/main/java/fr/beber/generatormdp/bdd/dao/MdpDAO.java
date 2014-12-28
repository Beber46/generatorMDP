package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bean.Mdp;

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
            BDD.MDP_COLUMN_APP,
            BDD.MDP_COLUMN_LEVEL
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
        final Cursor cursor = mBDD.query(BDD.TN_MDP, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mdp getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_MDP, mColumn, id.toString(), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Mdp mdp) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], mdp.getMdp());
        contentValues.put(mColumn[2], mdp.getApp());
        contentValues.put(mColumn[3], mdp.getLevel());

        mBDD.insert(BDD.TN_MDP, null, contentValues);
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Mdp mdp) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], mdp.getMdp());
        contentValues.put(mColumn[2], mdp.getApp());
        contentValues.put(mColumn[3], mdp.getLevel());

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
        mdp.setApp(cursor.getInt(BDD.MDP_NUM_APP));
        mdp.setLevel(cursor.getInt(BDD.MDP_NUM_LEVEL));

        return mdp;
    }
}
