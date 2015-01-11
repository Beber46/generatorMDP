package fr.beber.generatormdp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.beber.generatormdp.R;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.LevelDAO;
import fr.beber.generatormdp.bean.Mdp;

import java.util.HashMap;
import java.util.List;

/**
 * Cette classe permet de définir l'adapter pour la liste view de la première page.
 *
 * @author Bertrand
 * @version 1.0
 */
public class StableMDPAdapter extends ArrayAdapter<Mdp> {

    final HashMap<Mdp, Integer> mIdMap = new HashMap<Mdp, Integer>();
    final List<Mdp> mdpList;
    private final Context context;

    public StableMDPAdapter(final Context context, final int textViewResourceId, final List<Mdp> mdpList) {
        super(context, textViewResourceId, mdpList);
        this.context = context;
        this.mdpList = mdpList;
        for (int i = 0; i < mdpList.size(); ++i) {
            mIdMap.put(mdpList.get(i), mdpList.get(i).getId());
        }
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.adapter_mdp, parent, false);
        final TextView textViewFirst = (TextView) rowView.findViewById(R.id.firstLine);
        final TextView textViewSecond = (TextView) rowView.findViewById(R.id.secondLine);

        final ApplicationDAO applicationDAO = new ApplicationDAO(this.context);
        applicationDAO.openOnlyRead();
        textViewFirst.setText(applicationDAO.getById(mdpList.get(position).getApp()).getName());
        applicationDAO.close();

        final LevelDAO levelDAO = new LevelDAO(this.context);
        levelDAO.openOnlyRead();
        textViewSecond.setText(levelDAO.getById(mdpList.get(position).getLevel()).getName());
        levelDAO.close();

        return rowView;
    }

    @Override
    public Mdp getItem(int position) {
        return super.getItem(position);
    }
}