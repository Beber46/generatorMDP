package fr.beber.generatormdp.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import fr.beber.generatormdp.R;

/**
 * @author BLafage
 * @version 1.0
 */
public class UserSettingActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

    }
}