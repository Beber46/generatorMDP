package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import fr.beber.generatormdp.bdd.BDD;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bdd.dao.LevelDAO;
import fr.beber.generatormdp.bdd.dao.MdpDAO;
import fr.beber.generatormdp.bdd.dao.UserDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.bean.Level;
import fr.beber.generatormdp.bean.Mdp;

import java.util.List;

public class WelcomeActivity extends Activity {

    private boolean click = true;

    /**
     * Permet de passer à l'activity suivante.
     */
    private View.OnClickListener clickListenerLogo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(click) {
                click=false;
                final Intent intent;

                final UserDAO userDAO = new UserDAO(getApplicationContext());
                userDAO.openOnlyRead();

                if(userDAO.getAll().size()>0)
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                else
                    intent = new Intent(getApplicationContext(), CreateUserActivity.class);

                userDAO.close();

                startActivity(intent);
                finish();
            }
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        final ImageView imageViewLogo = (ImageView)findViewById(R.id.IVLogo);

        imageViewLogo.setOnClickListener(this.clickListenerLogo);

        final LevelDAO levelDAO = new LevelDAO(this);
        levelDAO.openOnlyRead();
        final List<Level> list = levelDAO.getAll();
        levelDAO.close();

        if(list.size()==0)
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

        final LevelDAO levelDAO = new LevelDAO(context);
        levelDAO.open();
        final Level level = new Level();
        level.setName("Minimum");
        level.setColor("Blanc");
        levelDAO.save(level);
        levelDAO.close();

        Log.d(getClass().getName(),level.toString());

        levelDAO.open();
        final Level level1 = new Level();
        level1.setName("Millieu");
        level1.setColor("Orange");
        levelDAO.save(level1);
        levelDAO.close();

        Log.d(getClass().getName(),level.toString());

        levelDAO.open();
        final Level level2 = new Level();
        level2.setName("Haut");
        level2.setColor("Rouge");
        levelDAO.save(level2);
        levelDAO.close();

        Log.d(getClass().getName(),level2.toString());

        final MdpDAO mdpDAO = new MdpDAO(context);
        Mdp mdp = new Mdp();
        mdp.setMdp("TEST");
        levelDAO.openOnlyRead();
        mdp.setLevel(levelDAO.getAll().get(0).getId());
        mdp.setIsMaj(Boolean.TRUE);
        levelDAO.close();

        mdpDAO.open();
        mdpDAO.save(mdp);
        mdpDAO.close();

        mdp = new Mdp();
        mdp.setMdp("TEST2");
        levelDAO.openOnlyRead();
        mdp.setLevel(levelDAO.getAll().get(1).getId());
        mdp.setIsMaj(Boolean.TRUE);
        levelDAO.close();

        mdpDAO.open();
        mdpDAO.save(mdp);
        mdpDAO.close();

        final ApplicationDAO applicationDAO = new ApplicationDAO(context);
        final Application application = new Application();
        application.setName("Google +");
        application.setDescription("Réseau social de Google.");
        mdpDAO.open();
        application.setMdp(mdpDAO.getAll().get(0).getId());
        mdpDAO.close();

        applicationDAO.open();
        applicationDAO.save(application);
        applicationDAO.close();

        final Application application1 = new Application();
        application1.setName("Facebook");
        application1.setDescription("Réseau social de Facebook.");
        mdpDAO.open();
        application1.setMdp(mdpDAO.getAll().get(0).getId());
        mdpDAO.close();

        applicationDAO.open();
        applicationDAO.save(application1);
        applicationDAO.close();
    }
}
