package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bdd.table.TLevel;
import fr.beber.generatormdp.bean.Level;

import java.util.List;

/**
 * Cette classe permet de faire les opérations basiques.
 *
 * @author Bertrand
 * @version 1.0
 */
public class LevelDAO extends Repository<Level> {

    /**
     * Champs en base de données de {@link fr.beber.generatormdp.bean.Level}
     */
    private static final String[] mColumn = new String[]{
            TLevel.LEVEL_COLUMN_ID,
            TLevel.LEVEL_COLUMN_NAME,
            TLevel.LEVEL_COLUMN_COLOR
    };

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     */
    public LevelDAO(final Context context) {
        mSQLOH = new BDD(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Level> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(TLevel.TN_LEVEL, mColumn, null, null, null, null, mColumn[0]);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.rawQuery("SELECT "+this.getAllParams()+" FROM "+TLevel.TN_LEVEL+" WHERE "+mColumn[0]+" = ?",new String[]{String.valueOf(id)});

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(final Level level) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], level.getName());
        contentValues.put(mColumn[2], level.getColor());

        return mBDD.insert(TLevel.TN_LEVEL, null, contentValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Level level) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], level.getName());
        contentValues.put(mColumn[2], level.getColor());

        mBDD.update(TLevel.TN_LEVEL, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(level.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(TLevel.TN_LEVEL, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level convertCursorToObject(final Cursor cursor) {
        final Level level = new Level();
        level.setId(cursor.getInt(TLevel.LEVEL_NUM_ID));
        level.setName(cursor.getString(TLevel.LEVEL_NUM_NAME));
        level.setColor(cursor.getString(TLevel.LEVEL_NUM_COLOR));

        return level;
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
