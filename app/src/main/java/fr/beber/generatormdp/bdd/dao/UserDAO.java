package fr.beber.generatormdp.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.Repository;
import fr.beber.generatormdp.bdd.table.TUser;
import fr.beber.generatormdp.bean.User;
import fr.beber.generatormdp.util.QueryBuilder;

import java.util.List;

/**
 * Cette classe permet de faire les opérations basiques.
 *
 * @author Bertrand
 * @version 1.0
 */
public class UserDAO extends Repository<User> {

    /**
     * Champs en base de données de {@link fr.beber.generatormdp.bean.User}
     */
    private static final String[] mColumn = new String[]{
            TUser.USER_COLUMN_ID,
            TUser.USER_COLUMN_USERNAME,
            TUser.USER_COLUMN_MDP,
            TUser.USER_COLUMN_EMAIL
    };

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     */
    public UserDAO(final Context context) {
        mSQLOH = new BDD(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        Log.d(this.getClass().getName(), "Entree");
        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(TUser.TN_USER);

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToListObject(cursor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");

        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(TUser.TN_USER);
        queryBuilder.addConstraint("" + mColumn[0] + " = ?", String.valueOf(id));

        final Cursor cursor = mBDD.rawQuery(queryBuilder.toSQLString(),queryBuilder.getParamsArray());

        Log.d(this.getClass().getName(), "Sortie");
        return convertCursorToOneObject(cursor);
    }

    /**
     * Permet d'obtenir l'authentification d'un utilisateur.
     * @param username Le pseudo de l'utilisateur.
     * @param mdp Le mot de passe de l'utilisateur.
     * @return <code>TRUE</code> si ok, <code>FALSE</code> si aucun, <code>null</code> si plusieurs.
     */
    public Boolean authentificate(final String username, final String mdp){
        Log.d(this.getClass().getName(), "Entree");

        final QueryBuilder queryBuilder = new QueryBuilder(this.getAllParams());
        queryBuilder.addTable(TUser.TN_USER);
        queryBuilder.addConstraint(""+mColumn[1]+" = ?",username);
        queryBuilder.addConstraint(""+mColumn[2]+" = ?",mdp);

        int nbr = this.count(queryBuilder);

        Log.d(this.getClass().getName(), "Sortie");
        switch (nbr){
            case 1:
                return Boolean.TRUE;
            case 0:
                return Boolean.FALSE;
            default:
                return null;

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(final User user) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = this.getContentValues(user);

        return mBDD.insert(TUser.TN_USER, null, contentValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final User user) {
        Log.d(this.getClass().getName(), "Entree");
        final ContentValues contentValues = this.getContentValues(user);

        mBDD.update(TUser.TN_USER, contentValues, mColumn[0] + "=?", new String[]{String.valueOf(user.getId())});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer id) {
        Log.d(this.getClass().getName(), "Entree");
        mBDD.delete(TUser.TN_USER, mColumn[0] + "=?", new String[]{String.valueOf(id)});
        Log.d(this.getClass().getName(), "Sortie");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User convertCursorToObject(final Cursor cursor) {
        final User user = new User();
        user.setId(cursor.getInt(TUser.USER_NUM_ID));
        user.setUsername(cursor.getString(TUser.USER_NUM_USERNAME));
        user.setMdp(cursor.getString(TUser.USER_NUM_MDP));
        user.setEmail(cursor.getString(TUser.USER_NUM_EMAIL));

        return user;
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

    /**
     * Permet de créer un ContentValues.
     * @param user L'utilisateur.
     * @return ContentValues créé.
     */
    private ContentValues getContentValues(final User user){
        final ContentValues contentValues = new ContentValues();
        contentValues.put(mColumn[1], user.getUsername());
        contentValues.put(mColumn[2], user.getMdp());
        contentValues.put(mColumn[3], user.getEmail());

        return contentValues;
    }
}
