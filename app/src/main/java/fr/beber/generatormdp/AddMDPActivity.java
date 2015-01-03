package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import fr.beber.generatormdp.adapter.SpinApplicationAdapter;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.Constante;
import fr.beber.generatormdp.util.GenerateMDP;


public class AddMDPActivity extends Activity {

    private Boolean isNumeric = Boolean.FALSE;
    private Boolean isMinuscule = Boolean.TRUE;
    private Boolean isMajuscule = Boolean.FALSE;
    private Boolean isSpecial = Boolean.FALSE;
    private TextView seekBadValue;
    private Integer appId;
    private final View.OnClickListener onClickListenerValidate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final GenerateMDP generateMDP = new GenerateMDP(getApplicationContext(),isNumeric,isMinuscule,isMajuscule,isSpecial,Integer.valueOf(String.valueOf(seekBadValue.getText())));

            Log.d(getClass().getName(),generateMDP.toString());

            final Mdp mdp = new Mdp();
            mdp.setMdp(generateMDP.getPassWord());
            mdp.setLevel(generateMDP.getLevel());
            mdp.setApp(appId);

            final Intent intent = new Intent(getApplicationContext(),MDPDetailsActivity.class);

            final MdpDAO mdpDAO = new MdpDAO(getApplicationContext());
            mdpDAO.open();
            intent.putExtra(Constante.MDPID, String.valueOf(mdpDAO.save(mdp)));
            mdpDAO.close();

            startActivity(intent);
            finish();
        }
    };
    private SpinApplicationAdapter adapterListApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mdp);

        this.seekBadValue = (TextView)findViewById(R.id.seekBarTV);
        seekBadValue.setText(String.valueOf(Constante.MIN_VALUE));

        final ApplicationDAO applicationDAO = new ApplicationDAO(this);
        applicationDAO.openOnlyRead();
        adapterListApplication = new SpinApplicationAdapter(this,  android.R.layout.simple_spinner_item, applicationDAO.getAll());
        applicationDAO.close();

        final Spinner spinnerApplication = (Spinner)findViewById(R.id.spinApplication);
        spinnerApplication.setAdapter(adapterListApplication);

        spinnerApplication.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                appId = adapterListApplication.getItem(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                if (progress < Constante.MIN_VALUE) {
                    seekBar.setProgress(Constante.MIN_VALUE);
                    seekBadValue.setText(String.valueOf(Constante.MIN_VALUE));
                } else
                    seekBadValue.setText(String.valueOf(progress));
            }
        });

        final CheckBox checkBoxNumeric = (CheckBox)findViewById(R.id.checkBoxNumerique);
        checkBoxNumeric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNumeric = ((CheckBox) view).isChecked();
            }
        });

        final CheckBox checkBoxSpec = (CheckBox)findViewById(R.id.checkBoSpec);
        checkBoxSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSpecial = ((CheckBox) view).isChecked();
            }
        });

        final CheckBox checkBoxMin = (CheckBox)findViewById(R.id.checkBoxMinuscule);
        checkBoxMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMinuscule = ((CheckBox)view).isChecked();
            }
        });

        final CheckBox checkBoxMaj = (CheckBox)findViewById(R.id.checkBoxMajuscule);
        checkBoxMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMajuscule = ((CheckBox)view).isChecked();
            }
        });

        final Button buttonValidate = (Button)findViewById(R.id.BTValidate);
        buttonValidate.setOnClickListener(this.onClickListenerValidate);
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
