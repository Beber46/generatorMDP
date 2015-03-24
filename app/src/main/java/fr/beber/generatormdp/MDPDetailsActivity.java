package fr.beber.generatormdp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import fr.beber.generatormdp.util.DateFormat;


public class MDPDetailsActivity extends Activity {

    private Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpdetails);

        final ActionBar actionBar = getActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        final String identifiant = getIntent().getStringExtra(Constante.APPID);
        if(identifiant!=null){

            final ApplicationDAO applicationDAO = new ApplicationDAO(this);
            applicationDAO.openOnlyRead();
            this.application = applicationDAO.getById(Integer.valueOf(identifiant));
            applicationDAO.close();

            final MdpDAO mdpDAO = new MdpDAO(this);
            mdpDAO.openOnlyRead();
            final Mdp mdp = mdpDAO.getById(application.getId());
            mdpDAO.close();

            setTitle(this.application.getName());

            final TextView textViewDescripion = (TextView) findViewById(R.id.TVDescription);
            textViewDescripion.setText(application.getDescription());

            final TextView textViewMdp = (TextView) findViewById(R.id.TVMDP);
            textViewMdp.setText(mdp.getMdp());

            final TextView textViewDateDebut = (TextView) findViewById(R.id.TVDerniereModif);
            textViewDateDebut.setText(getResources().getString(R.string.textview_date_modif)+" "+DateFormat.getCalendarFormat(mdp.getDateModify(),"dd-MM-yyyy"));

            final Button buttonDelete = (Button)findViewById(R.id.BTDelete);
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ApplicationDAO applicationDAO = new ApplicationDAO(getApplicationContext());
                    applicationDAO.open();
                    applicationDAO.delete(application.getId());
                    applicationDAO.close();

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

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_modify:
                final Intent intent = new Intent(getApplicationContext(),ModifyMDPActivity.class);
                intent.putExtra(Constante.APPID,String.valueOf(application.getId()));
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
