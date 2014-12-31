package fr.beber.generatormdp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.Constante;


public class MDPDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpdetails);

        final String identifiant = getIntent().getStringExtra(Constante.MDPID);
        if(identifiant!=null){
            final MdpDAO mdpDAO = new MdpDAO(this);
            mdpDAO.openOnlyRead();
            Log.d(this.getClass().getName(), "identifiant : " + identifiant);
            final Mdp mdp = mdpDAO.getById(Integer.valueOf(identifiant));
            mdpDAO.close();

            final ApplicationDAO applicationDAO = new ApplicationDAO(this);
            applicationDAO.openOnlyRead();
            final Application application = applicationDAO.getById(mdp.getId());
            applicationDAO.close();

            final TextView textViewTitre = (TextView) findViewById(R.id.TVTitre);
            textViewTitre.setText(application.getName());

            final TextView textViewDescripion = (TextView) findViewById(R.id.TVDescription);
            textViewDescripion.setText(application.getDescription());

            final TextView textViewMdp = (TextView) findViewById(R.id.TVMDP);
            textViewMdp.setText(mdp.getMdp());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mdpdetails, menu);
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
