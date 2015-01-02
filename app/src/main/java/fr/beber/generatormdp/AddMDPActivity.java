package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.LevelDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.Constante;


public class AddMDPActivity extends Activity {

    private static final int MIN_VALUE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mdp);

        final TextView seekBadValue = (TextView)findViewById(R.id.seekBarTV);
        seekBadValue.setText(String.valueOf(MIN_VALUE));

        final ApplicationDAO applicationDAO = new ApplicationDAO(this);
        applicationDAO.openOnlyRead();
        final ArrayAdapter<String> adapterListApplication = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, applicationDAO.getAllName());
        applicationDAO.close();

        final Spinner spinnerApplication = (Spinner)findViewById(R.id.spinApplication);
        spinnerApplication.setAdapter(adapterListApplication);

        final LevelDAO levelDAO = new LevelDAO(this);
        levelDAO.openOnlyRead();
        final ArrayAdapter<String> adapterListLevel = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, levelDAO.getAllName());
        levelDAO.close();

        final Spinner spinnerLevel = (Spinner)findViewById(R.id.spinLevel);
        spinnerLevel.setAdapter(adapterListLevel);

        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarsize);
        seekBar.setMax(32);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if (progress < MIN_VALUE) {
                    seekBar.setProgress(MIN_VALUE);
                    seekBadValue.setText(String.valueOf(MIN_VALUE));
                }
                else
                    seekBadValue.setText(String.valueOf(progress));
            }
        });

        final Button buttonValidate = (Button)findViewById(R.id.BTValidate);
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MdpDAO mdpDAO = new MdpDAO(getApplicationContext());
                mdpDAO.openOnlyRead();
                final Mdp mdp = mdpDAO.getAll().get(0);
                mdpDAO.close();
                final Intent intent = new Intent(getApplicationContext(),MDPDetailsActivity.class);
                intent.putExtra(Constante.MDPID,String.valueOf(mdp.getId()));
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_md, menu);
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
