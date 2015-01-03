package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.Constante;


public class MDPDetailsActivity extends Activity {

    private Mdp mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpdetails);

        final String identifiant = getIntent().getStringExtra(Constante.MDPID);
        if(identifiant!=null){
            final MdpDAO mdpDAO = new MdpDAO(this);
            mdpDAO.openOnlyRead();
            Log.d(this.getClass().getName(), "identifiant : " + identifiant);
            this.mdp = mdpDAO.getById(Integer.valueOf(identifiant));
            mdpDAO.close();

            final ApplicationDAO applicationDAO = new ApplicationDAO(this);
            applicationDAO.openOnlyRead();
            final Application application = applicationDAO.getById(this.mdp.getApp());
            applicationDAO.close();

            final TextView textViewTitre = (TextView) findViewById(R.id.TVTitre);
            textViewTitre.setText(application.getName());

            final TextView textViewDescripion = (TextView) findViewById(R.id.TVDescription);
            textViewDescripion.setText(application.getDescription());

            final TextView textViewMdp = (TextView) findViewById(R.id.TVMDP);
            textViewMdp.setText(this.mdp.getMdp());


            final Button buttonDelete = (Button)findViewById(R.id.BTDelete);
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                    final MdpDAO mdpDAO = new MdpDAO(getApplicationContext());
                    mdpDAO.open();
                    mdpDAO.delete(mdp.getId());
                    mdpDAO.close();

                    startActivity(intent);
                    finish();
                }
            });
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

        if (id == R.id.action_modify) {
            final Intent intent = new Intent(getApplicationContext(),ModifyMDPActivity.class);
            intent.putExtra(Constante.MDPID,String.valueOf(mdp.getId()));
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
