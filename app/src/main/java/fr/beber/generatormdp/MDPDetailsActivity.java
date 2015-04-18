package fr.beber.generatormdp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.settings.UserSettingActivity;
import fr.beber.generatormdp.util.CalendarHelper;
import fr.beber.generatormdp.util.Constante;
import fr.beber.generatormdp.util.LetterTileProvider;


public class MDPDetailsActivity extends Activity {

    private Application application;
    private Boolean isStop = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdpdetails);

        this.isStop = Boolean.TRUE;

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
            textViewDateDebut.setText(getResources().getString(R.string.textview_date_modif)+" "+ CalendarHelper.getCalendarFormat(mdp.getDateModify()));

            final Button buttonModify = (Button)findViewById(R.id.BTModify);
            buttonModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isStop = Boolean.FALSE;
                    final Intent intent = new Intent(getApplicationContext(),ModifyMDPActivity.class);
                    intent.putExtra(Constante.APPID,String.valueOf(application.getId()));
                    startActivity(intent);
                    finish();
                }
            });

            final Resources res = this.getResources();
            final ImageView imageViewLetter = (ImageView)findViewById(R.id.IVLogoDetails);
            final LetterTileProvider letterTileProvider = new LetterTileProvider(this);
            imageViewLetter.setImageBitmap(letterTileProvider.getLetterIcon(this.application.getName(),res.getDimensionPixelSize(R.dimen.letter_tile_size_xx),res.getDimensionPixelSize(R.dimen.letter_tile_size_xx)));

            final ImageView iconWarning = (ImageView)findViewById(R.id.IVIconWarning);
            if(Boolean.TRUE.equals(CalendarHelper.compareCalendarExpiration(this, mdp.getDateModify()))){
                iconWarning.setVisibility(View.VISIBLE);
                textViewDateDebut.setTextColor(Color.RED);
            }
            else
                iconWarning.setVisibility(View.INVISIBLE);

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

        final Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_delete:
                this.isStop = Boolean.FALSE;
                final ApplicationDAO applicationDAO = new ApplicationDAO(getApplicationContext());
                applicationDAO.open();
                applicationDAO.delete(application.getId());
                applicationDAO.close();
                finish();
                return true;
            case R.id.action_settings:
                this.isStop = Boolean.FALSE;
                intent = new Intent(getApplicationContext(),UserSettingActivity.class);
                startActivityForResult(intent,Constante.RESULT_SETTINGS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Boolean.TRUE.equals(this.isStop)) {
            final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.isStop = Boolean.TRUE;
    }
}
