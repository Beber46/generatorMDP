package fr.beber.generatormdp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 */
public class QueryBuilder {

    private String select;

    private List<String> tables = new ArrayList<String>();
    private List<String> constraints = new ArrayList<String>();
    private List<String> params = new ArrayList<String>();

    private String orderBy = "";

    public QueryBuilder(final String select) {
        if(!select.toUpperCase().contains("SELECT"))
            this.select = "SELECT "+ select;
        else
            this.select = select;
    }

    /**
     * Définit un order by.
     * @param orderBy OrderBy à définir.
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * Récupère toutes les contraites de la requete en tableau String.
     * @return
     */
    public String[] getParamsArray() {
        return params.toArray(new String[params.size()]);
    }

    /**
     * Permet d'ajouter une table à la liste des tables de la requete
     * @param table
     */
    public void addTable(final String table){
        this.tables.add(table);
    }

    /**
     * Permet d'ajouter des contraintes sans paramètre.
     * @param constraint Contrainte de la requete.
     */
    public void addConstraint(final String constraint){
        this.addConstraint(constraint,null);
    }

    /**
     * Permet d'ajouter des contraintes avec paramètre.
     * @param constraint Contrainte de la requete.
     * @param param Paramètre de la contrainte.
     */
    public void addConstraint(final String constraint, final String param){
        this.constraints.add(constraint);
        if(param!=null)
            this.params.add(param);
    }

    /**
     * Permet de reconstruire une requete à partir des conditions fournies.
     * @return La requete sans les paramètres.
     */
    public String toSQLString(){
        String retour = this.select;

        boolean first = true;
        for(String table : this.tables){
            if(first){
                first = false;
                retour = retour +" FROM "+table;
            }else
                retour = retour +", "+table;
        }

        first = true;
        for(String constraint : this.constraints){
            if(first){
                first = false;
                retour = retour +" WHERE "+constraint;
            }else
                retour = retour +" AND "+constraint;
        }

        if(this.orderBy.length()>0)
            retour = retour + " ORDER BY " + this.orderBy;

        return retour;
    }
}
