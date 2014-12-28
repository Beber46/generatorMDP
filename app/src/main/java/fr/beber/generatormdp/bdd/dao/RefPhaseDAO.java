package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bean.RefPhase;

import java.util.List;

/**
 * Cette classe permet de les opérations basiques.
 *
 * @author Bertrand
 * @version 1.0
 */
public class RefPhaseDAO extends Repository<RefPhase> {

    /**
     * Champs en base de données de {@link RefPhase}
     */
    private String[] mColumn = new String[]{
            BDD.PHRASE_COLUMN_ID,
            BDD.PHRASE_COLUMN_NAME
    };

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     */
    public RefPhaseDAO(final Context context) {
        mSQLOH = new BDD(context);
    }

    /**
     * Constructeur
     *
     * @param sqLiteOpenHelper Définit le sqLiteOpenHelper à utiliser.
     */
    public RefPhaseDAO(final SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLOH = sqLiteOpenHelper;
    }

    @Override
    public List<RefPhase> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_PHRASE, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    @Override
    public RefPhase getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_PHRASE, mColumn, id.toString(), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    @Override
    public void save(final RefPhase refPhase) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], refPhase.getName());

        mBDD.insert(BDD.TN_PHRASE, null, contentValues);
        Log.d(this.getClass().getName(), "Sortie");
    }

    @Override
    public void update(final RefPhase refPhase) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], refPhase.getName());

        mBDD.update(BDD.TN_PHRASE, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(refPhase.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(BDD.TN_PHRASE, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    @Override
    public RefPhase convertCursorToObject(final Cursor cursor) {
        final RefPhase refPhase = new RefPhase();
        refPhase.setId(cursor.getInt(BDD.PHRASE_NUM_ID));
        refPhase.setName(cursor.getString(BDD.PHRASE_NUM_NAME));

        return refPhase;
    }
}
