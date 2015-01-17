package fr.beber.generatormdp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.beber.generatormdp.R;
import fr.beber.generatormdp.bdd.dao.LevelDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Mdp;

import java.util.HashMap;
import java.util.List;

/**
 * Cette classe permet de définir l'adapter pour la liste view de la première page.
 *
 * @author Bertrand
 * @version 1.0
 */
public class StableAPPAdapter extends ArrayAdapter<Application> {

    final HashMap<Application, Integer> mIdApplication = new HashMap<Application, Integer>();
    final List<Application> applicationList;
    private final Context context;

    public StableAPPAdapter(final Context context, final int textViewResourceId, final List<Application> applicationList) {
        super(context, textViewResourceId, applicationList);
        this.context = context;
        this.applicationList = applicationList;
        for (int i = 0; i < applicationList.size(); ++i) {
            mIdApplication.put(applicationList.get(i), applicationList.get(i).getId());
        }
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.adapter_mdp, parent, false);
        final TextView textViewFirst = (TextView) rowView.findViewById(R.id.firstLine);
        final TextView textViewSecond = (TextView) rowView.findViewById(R.id.secondLine);
        final ImageView imageViewShield = (ImageView) rowView.findViewById(R.id.IVShield);

        textViewFirst.setText(applicationList.get(position).getName());

        final MdpDAO mdpDAO = new MdpDAO(this.context);
        mdpDAO.openOnlyRead();
        final Mdp mdp = mdpDAO.getById(applicationList.get(position).getId());
        mdpDAO.close();

        final LevelDAO levelDAO = new LevelDAO(this.context);
        levelDAO.openOnlyRead();
        textViewSecond.setText(levelDAO.getById(mdp.getLevel()).getName());
        levelDAO.close();

        switch (mdp.getLevel()){
            case 1:
                imageViewShield.setImageResource(R.drawable.shieldred);
                break;
            case 2:
                imageViewShield.setImageResource(R.drawable.shieldyellow);
                break;
            case 3:
                imageViewShield.setImageResource(R.drawable.shieldgreen);
                break;
            default:
                imageViewShield.setImageResource(R.drawable.shieldred);
        }

        return rowView;
    }

    @Override
    public Application getItem(int position) {
        return super.getItem(position);
    }
}