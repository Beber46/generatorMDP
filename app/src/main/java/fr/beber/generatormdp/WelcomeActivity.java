package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final ImageView imageViewLogo = (ImageView)findViewById(R.id.IVLogo);

        imageViewLogo.setOnClickListener(this.clickListenerLogo);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                imageViewLogo.setOnClickListener(clickListenerLogo);
            }
        }, 2000);
    }


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
}
