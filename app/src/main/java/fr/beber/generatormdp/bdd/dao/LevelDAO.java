package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
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
            BDD.LEVEL_COLUMN_ID,
            BDD.LEVEL_COLUMN_NAME,
            BDD.LEVEL_COLUMN_COLOR
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
     * Constructeur
     *
     * @param sqLiteOpenHelper Définit le sqLiteOpenHelper à utiliser.
     */
    public LevelDAO(final SQLiteOpenHelper sqLiteOpenHelper) {
        mSQLOH = sqLiteOpenHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Level> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_LEVEL, mColumn, null, null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        final Cursor cursor = mBDD.query(BDD.TN_LEVEL, mColumn, id.toString(), null, null, null, null);

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Level level) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = new ContentValues();

        contentValues.put(mColumn[1], level.getName());
        contentValues.put(mColumn[2], level.getColor());

        mBDD.insert(BDD.TN_LEVEL, null, contentValues);
        Log.d(this.getClass().getName(), "Sortie");
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

        mBDD.update(BDD.TN_LEVEL, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(level.getId())});
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
    public Level convertCursorToObject(final Cursor cursor) {
        final Level level = new Level();
        level.setId(cursor.getInt(BDD.LEVEL_NUM_ID));
        level.setName(cursor.getString(BDD.LEVEL_NUM_NAME));
        level.setColor(cursor.getString(BDD.LEVEL_NUM_COLOR));

        return level;
    }
}