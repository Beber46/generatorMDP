package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.LevelDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Level;
import fr.beber.generatormdp.bean.Mdp;

public class WelcomeActivity extends Activity {
    /**
     * Permet de passer à l'activity suivante.
     */
    private View.OnClickListener clickListenerLogo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final ImageView imageViewLogo = (ImageView)findViewById(R.id.IVLogo);

        imageViewLogo.setOnClickListener(this.clickListenerLogo);

        this.initBDDTest(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewLogo.performClick();
            }
        }, 2000);
    }

    /**
     * Pour faire des tests.
     * @param context Le contexte courant.
     */
    private void initBDDTest(final Context context){

        final BDD bdd = new BDD(context);
        bdd.dropALL();

        final ApplicationDAO applicationDAO = new ApplicationDAO(context);
        final Application application = new Application();
        application.setName("Google +");
        application.setDescription("Réseau social de Google.");

        applicationDAO.open();
        applicationDAO.save(application);
        applicationDAO.close();

        final LevelDAO levelDAO = new LevelDAO(context);
        final Level level = new Level();
        level.setName("Haut");
        level.setColor("Rouge");

        levelDAO.open();
        levelDAO.save(level);
        levelDAO.close();

        final MdpDAO mdpDAO = new MdpDAO(context);
        final Mdp mdp = new Mdp();
        mdp.setMdp("TEST");
        levelDAO.openOnlyRead();
        mdp.setLevel(levelDAO.getAll().get(0).getId());
        levelDAO.close();

        applicationDAO.openOnlyRead();
        mdp.setApp(applicationDAO.getAll().get(0).getId());
        applicationDAO.close();

        mdpDAO.open();
        mdpDAO.save(mdp);
        mdpDAO.close();
    }
}
