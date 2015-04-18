package fr.beber.generatormdp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.settings.UserSettingActivity;
import fr.beber.generatormdp.util.Constante;
import fr.beber.generatormdp.util.GenerateMDP;


public class AddMDPActivity extends Activity {

    private Boolean isStop = Boolean.FALSE;
    private Boolean isNumeric = Boolean.FALSE;
    private Boolean isMinuscule = Boolean.TRUE;
    private Boolean isMajuscule = Boolean.FALSE;
    private Boolean isSpecial = Boolean.FALSE;
    private final View.OnClickListener onClickListenerValidate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final String nomAppli = applicationName.getText().toString();

            if(!nomAppli.isEmpty()) {

                if(isMajuscule || isMinuscule || isNumeric || isSpecial) {

                    final GenerateMDP generateMDP = new GenerateMDP(getApplicationContext(), isNumeric, isMinuscule, isMajuscule, isSpecial, Integer.valueOf(String.valueOf(seekBadValue.getText())));

                    Log.d(getClass().getName(), generateMDP.toString());

                    final Mdp mdp = new Mdp();
                    mdp.setMdp(generateMDP.getPassWord());
                    mdp.setLevel(generateMDP.getLevel());
                    mdp.setIsMaj(isMajuscule);
                    mdp.setIsMin(isMinuscule);
                    mdp.setIsNumeric(isNumeric);
                    mdp.setIsSpec(isSpecial);

                    final Intent intent = new Intent(getApplicationContext(), MDPDetailsActivity.class);
                    isStop = Boolean.FALSE;

                    final MdpDAO mdpDAO = new MdpDAO(getApplicationContext());
                    mdpDAO.open();
                    final Integer mdpID = (int) mdpDAO.save(mdp);
                    mdpDAO.close();

                    final ApplicationDAO applicationDAO = new ApplicationDAO(getApplicationContext());
                    applicationDAO.open();
                    final Application application = new Application();
                    application.setName(nomAppli);
                    application.setDescription(applicationDes.getText().toString());
                    application.setMdp(mdpID);

                    intent.putExtra(Constante.APPID, String.valueOf(applicationDAO.save(application)));
                    applicationDAO.close();


                    startActivity(intent);
                    finish();
                }else
                    Toast.makeText(getApplicationContext(), "L'application doit au moins comporter un type!", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(getApplicationContext(), "L'application doit au moins comporter un nom!", Toast.LENGTH_LONG).show();
        }
    };
    private TextView seekBadValue;
    private EditText applicationName;
    private EditText applicationDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mdp);

        this.isStop = Boolean.TRUE;

        final ActionBar actionBar = getActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        this.seekBadValue = (TextView)findViewById(R.id.seekBarTV);
        seekBadValue.setText(String.valueOf(Constante.MIN_VALUE));

        applicationName = (EditText)findViewById(R.id.applicationNameET);
        applicationDes = (EditText)findViewById(R.id.applicationDescriptifET);

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

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                this.isStop = Boolean.FALSE;
                final Intent intent = new Intent(getApplicationContext(),UserSettingActivity.class);
                startActivityForResult(intent, Constante.RESULT_SETTINGS);
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
