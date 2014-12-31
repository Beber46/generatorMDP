package fr.beber.generatormdp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import fr.beber.generatormdp.adapter.StableMDPAdapter;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Mdp;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MdpDAO mdpDAO = new MdpDAO(this);
        mdpDAO.openOnlyRead();
        final List<Mdp> applicationList = mdpDAO.getAll();
        mdpDAO.close();

        final StableMDPAdapter stableMDPAdapter = new StableMDPAdapter(this,android.R.layout.simple_list_item_1,applicationList);
        final ListView listview = (ListView) findViewById(R.id.LVmdp);

        listview.setAdapter(stableMDPAdapter);
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
