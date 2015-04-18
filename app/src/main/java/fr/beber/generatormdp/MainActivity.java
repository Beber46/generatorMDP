package fr.beber.generatormdp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import fr.beber.generatormdp.adapter.StableAPPAdapter;
import fr.beber.generatormdp.bdd.dao.ApplicationDAO;
import fr.beber.generatormdp.bean.Application;
import fr.beber.generatormdp.settings.UserSettingActivity;
import fr.beber.generatormdp.util.Constante;

import java.util.List;


public class MainActivity extends ListActivity {

    private Boolean isStop = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isStop = Boolean.TRUE;

        final StableAPPAdapter stableAPPAdapter = new StableAPPAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,this.getAllApplication());
        setListAdapter(stableAPPAdapter);
    }

    @Override
    protected void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Application application = (Application) getListAdapter().getItem(position);
        final Intent intent = new Intent(getApplicationContext(),MDPDetailsActivity.class);
        this.isStop = Boolean.FALSE;
        intent.putExtra(Constante.APPID,String.valueOf(application.getId()));
        startActivity(intent);
    }

    /**
     * Permet de récupérer la liste des applications.
     * @return Liste d'application.
     */
    private List<Application> getAllApplication(){
        final ApplicationDAO applicationDAO = new ApplicationDAO(getApplicationContext());
        applicationDAO.openOnlyRead();
        final List<Application> applicationList = applicationDAO.getAll();
        applicationDAO.close();

        return applicationList;
    }

    @Override
    protected void onResume() {
        super.onResume();

        final StableAPPAdapter stableAPPAdapter = new StableAPPAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,this.getAllApplication());
        setListAdapter(stableAPPAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     */
    public boolean onOptionsItemSelected(final MenuItem item) {

        final Intent intent;
        switch (item.getItemId()){
            case R.id.action_add:
                this.isStop = Boolean.FALSE;
                intent = new Intent(getApplicationContext(),AddMDPActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                this.isStop = Boolean.FALSE;
                intent = new Intent(getApplicationContext(),UserSettingActivity.class);
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
