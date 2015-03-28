package fr.beber.generatormdp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Mdp;
import fr.beber.generatormdp.util.Constante;
import fr.beber.generatormdp.util.GenerateMDP;


public class ModifyMDPActivity extends Activity {

    private Boolean isNumeric = Boolean.FALSE;
    private Boolean isMinuscule = Boolean.TRUE;
    private Boolean isMajuscule = Boolean.FALSE;
    private Boolean isSpecial = Boolean.FALSE;
    private final View.OnClickListener onClickListenerModify = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final GenerateMDP generateMDP = new GenerateMDP(getApplicationContext(),isNumeric,isMinuscule,isMajuscule,isSpecial,Integer.valueOf(String.valueOf(seekBadValue.getText())));

            Log.d(getClass().getName(), generateMDP.toString());

            final MdpDAO mdpDAO = new MdpDAO(getApplicationContext());
            mdpDAO.openOnlyRead();
            final Mdp mdp = mdpDAO.getById(applicationAct.getId());
            mdpDAO.close();
            mdp.setMdp(generateMDP.getPassWord());
            mdp.setLevel(generateMDP.getLevel());
            mdp.setIsMaj(isMajuscule);
            mdp.setIsMin(isMinuscule);
            mdp.setIsNumeric(isNumeric);
            mdp.setIsSpec(isSpecial);

            final Intent intent = new Intent(getApplicationContext(),MDPDetailsActivity.class);

            mdpDAO.open();
            mdpDAO.update(mdp);
            intent.putExtra(Constante.APPID, String.valueOf(applicationAct.getId()));
            Log.d(getClass().getName(), mdp.toString());
            mdpDAO.close();

            startActivity(intent);
            finish();
        }
    };
    private TextView seekBadValue;
    private Application applicationAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_mdp);

        final ActionBar actionBar = getActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        final String identifiant = getIntent().getStringExtra(Constante.APPID);
        if(identifiant!=null) {

            final ApplicationDAO applicationDAO = new ApplicationDAO(this);
            applicationDAO.openOnlyRead();
            this.applicationAct = applicationDAO.getById(Integer.valueOf(identifiant));
            applicationDAO.close();

            setTitle(this.applicationAct.getName());

            final MdpDAO mdpDAO = new MdpDAO(this);
            mdpDAO.openOnlyRead();
            final Mdp mdp = mdpDAO.getById(applicationAct.getId());
            mdpDAO.close();

            Log.d(getClass().getName(),"mdp : "+mdp.toString());

            this.seekBadValue = (TextView) findViewById(R.id.seekBarTVMod);
            seekBadValue.setText(String.valueOf(Constante.MIN_VALUE));

            final TextView textViewPassword = (TextView)findViewById(R.id.passwordTVMod);
            textViewPassword.setText(mdp.getMdp());

            final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarsizeMod);
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
            seekBar.setProgress(mdp.getMdp().length());
            seekBadValue.setText(String.valueOf(mdp.getMdp().length()));

            final CheckBox checkBoxNumeric = (CheckBox) findViewById(R.id.checkBoxNumeriqueMod);
            checkBoxNumeric.setChecked(mdp.getIsNumeric().booleanValue());
            isNumeric = checkBoxNumeric.isChecked();
            checkBoxNumeric.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isNumeric = ((CheckBox) view).isChecked();
                }
            });

            final CheckBox checkBoxSpec = (CheckBox) findViewById(R.id.checkBoSpecMod);
            checkBoxSpec.setChecked(mdp.getIsSpec().booleanValue());
            isSpecial = checkBoxSpec.isChecked();
            checkBoxSpec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isSpecial = ((CheckBox) view).isChecked();
                }
            });

            final CheckBox checkBoxMin = (CheckBox) findViewById(R.id.checkBoxMinusculeMod);
            checkBoxMin.setChecked(mdp.getIsMin().booleanValue());
            isMinuscule = checkBoxMin.isChecked();
            checkBoxMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isMinuscule = ((CheckBox) view).isChecked();
                }
            });

            final CheckBox checkBoxMaj = (CheckBox) findViewById(R.id.checkBoxMajusculeMod);
            checkBoxMaj.setChecked(mdp.getIsMaj().booleanValue());
            isMajuscule = checkBoxMaj.isChecked();
            checkBoxMaj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isMajuscule = ((CheckBox) view).isChecked();
                }
            });

            final Button buttonModify = (Button) findViewById(R.id.BTModify);
            buttonModify.setOnClickListener(this.onClickListenerModify);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
