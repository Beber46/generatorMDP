package fr.beber.generatormdp.bdd;

import android.database.Cursor;

import java.util.List;

/**
 * Interface qui permet de créer les méthodes par défaut utilisées par tous les repo en base.
 *
 * @author Beber46
 * @version 1.0
 */
public interface IRepository<T> {

    /**
     * Permet de récupérer la liste des {@link T}.
     *
     * @return La liste de {@link T} trouvée.
     */
    public List<T> getAll();

    /**
     * Permet de récupérer un {@link T} en fonction de son identifiant <code>id</code>.
     *
     * @param id Identifiant d'un {@link T}.
     * @return {@link T} trouvé.
     */
    public T getById(final Integer id);

    /**
     * Permet d'enregistrer un {@link T}.
     *
     * @param entite à enregistrer.
     */
    public long save(final T entite);

    /**
     * Permet de mettre à jour un {@link T}.
     *
     * @param entite à mettre à jour.
     */
    public void update(final T entite);

    /**
     * Permet de supprimer un {@link T} en fonction de son identifiant.
     *
     * @param id Identifiant d'un {@link T}.
     */
    public void delete(final Integer id);

    /**
     * Permet de convertire un {@link android.database.Cursor} en liste de {@link T}.
     *
     * @param cursor à convertir.
     * @return Une liste de {@link T} trouvé.
     */
    public List<T> convertCursorToListObject(Cursor cursor);

    /**
     * Permet de définir une {@link T} à partir du {@link android.database.Cursor}.
     *
     * @param cursor à convertir.
     * @return Une compositiion.
     */
    public T convertCursorToObject(Cursor cursor);

    /**
     * Permet de convertire un {@link android.database.Cursor} en {@link T}.
     *
     * @param cursor à convertir.
     * @return {@link T} trouvé.
     */
    public T convertCursorToOneObject(Cursor cursor);
}
