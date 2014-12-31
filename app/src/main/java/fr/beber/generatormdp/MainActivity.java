package fr.beber.generatormdp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import fr.beber.generatormdp.adapter.StableMDPAdapter;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.Constante;

import java.util.List;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MdpDAO mdpDAO = new MdpDAO(this);
        mdpDAO.openOnlyRead();
        final List<Mdp> applicationList = mdpDAO.getAll();
        mdpDAO.close();

        final StableMDPAdapter stableMDPAdapter = new StableMDPAdapter(this,android.R.layout.simple_list_item_1,applicationList);
        setListAdapter(stableMDPAdapter);
    }

    @Override
    protected void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Mdp mdp = (Mdp) getListAdapter().getItem(position);
        final Intent intent = new Intent(getApplicationContext(),MDPDetailsActivity.class);
        intent.putExtra(Constante.MDPID,String.valueOf(mdp.getId()));
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
