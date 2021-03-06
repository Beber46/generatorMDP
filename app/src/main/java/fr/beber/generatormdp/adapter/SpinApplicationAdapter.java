package fr.beber.generatormdp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.beber.generatormdp.bean.Application;

import java.util.List;

/**
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 */
public class SpinApplicationAdapter extends ArrayAdapter<Application> {

    private final Context context;

    private final List<Application> values;

    public SpinApplicationAdapter(Context context, int textViewResourceId, List<Application> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.size();
    }

    public Application getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return this.getItem(position).getId().longValue();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        final TextView textView = new TextView(context);
        textView.setText(values.get(position).getName());

        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        super.getDropDownView(position, convertView, parent);

        final TextView textView = new TextView(context);
        textView.setText(values.get(position).getName());

        return textView;
    }
}
