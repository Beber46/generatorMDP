package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
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

            final Mdp mdp = new Mdp();
            mdp.setMdp(generateMDP.getPassWord());
            mdp.setLevel(generateMDP.getLevel());
            mdp.setApp(mdpAct.getApp());
            mdp.setId(mdpAct.getId());

            final Intent intent = new Intent(getApplicationContext(),MDPDetailsActivity.class);

            final MdpDAO mdpDAO = new MdpDAO(getApplicationContext());
            mdpDAO.open();
            mdpDAO.update(mdp);
            intent.putExtra(Constante.MDPID, String.valueOf(mdpAct.getId()));
            Log.d(getClass().getName(), mdp.toString());
            mdpDAO.close();

            startActivity(intent);
            finish();
        }
    };
    private TextView seekBadValue;
    private Mdp mdpAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_mdp);

        final String identifiant = getIntent().getStringExtra(Constante.MDPID);
        if(identifiant!=null) {
            final MdpDAO mdpDAO = new MdpDAO(this);
            mdpDAO.openOnlyRead();
            Log.d(this.getClass().getName(), "identifiant : " + identifiant);
            mdpAct = mdpDAO.getById(Integer.valueOf(identifiant));
            mdpDAO.close();

            this.seekBadValue = (TextView) findViewById(R.id.seekBarTVMod);
            seekBadValue.setText(String.valueOf(Constante.MIN_VALUE));

            final ApplicationDAO applicationDAO = new ApplicationDAO(this);
            final TextView textViewApp = (TextView)findViewById(R.id.appTVMod);
            applicationDAO.openOnlyRead();
            textViewApp.setText(applicationDAO.getById(mdpAct.getApp()).getName());
            applicationDAO.close();

            final TextView textViewPassword = (TextView)findViewById(R.id.passwordTVMod);
            textViewPassword.setText(mdpAct.getMdp());

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
            seekBar.setProgress(mdpAct.getMdp().length());
            seekBadValue.setText(String.valueOf(mdpAct.getMdp().length()));

            final CheckBox checkBoxNumeric = (CheckBox) findViewById(R.id.checkBoxNumeriqueMod);
            isNumeric = checkBoxNumeric.isChecked();
            checkBoxNumeric.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isNumeric = ((CheckBox) view).isChecked();
                }
            });

            final CheckBox checkBoxSpec = (CheckBox) findViewById(R.id.checkBoSpecMod);
            isSpecial = checkBoxSpec.isChecked();
            checkBoxSpec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isSpecial = ((CheckBox) view).isChecked();
                }
            });

            final CheckBox checkBoxMin = (CheckBox) findViewById(R.id.checkBoxMinusculeMod);
            isMinuscule = checkBoxMin.isChecked();
            checkBoxMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isMinuscule = ((CheckBox) view).isChecked();
                }
            });

            final CheckBox checkBoxMaj = (CheckBox) findViewById(R.id.checkBoxMajusculeMod);
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
}
