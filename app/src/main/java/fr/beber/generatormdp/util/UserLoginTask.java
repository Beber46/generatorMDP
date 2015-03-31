package fr.beber.generatormdp.util;

import android.content.Intent;
import android.os.AsyncTask;
import fr.beber.generatormdp.LoginActivity;
import fr.beber.generatormdp.MainActivity;

/**
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 */
public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "Beber069:hello"
    };

    private final String mNickname;
    private final String mPassword;
    private final LoginActivity mLoginActivity;

    public UserLoginTask(final LoginActivity loginActivity, final String nickname, final String password) {
        mLoginActivity = loginActivity;
        mNickname = nickname;
        mPassword = password;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return false;
        }

        for (String credential : DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            if (pieces[0].equals(mNickname)) {
                // Account exists, return true if the password matches.
                return pieces[1].equals(mPassword);
            }
        }

        // TODO: register the new account here.
        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        mLoginActivity.mAuthTask = null;

        if (success) {
            final Intent intent = new Intent(mLoginActivity.getApplicationContext(), MainActivity.class);
            mLoginActivity.startActivity(intent);
            mLoginActivity.finish();
        } /*else {
            mLoginActivity.mPasswordView.setError(mLoginActivity.getString(R.string.error_incorrect_password));
            mLoginActivity.mPasswordView.requestFocus();
        }*/
        if(mLoginActivity!=null)
            mLoginActivity.showProgress(false);
    }

    @Override
    protected void onCancelled() {
        mLoginActivity.mAuthTask = null;
        mLoginActivity.showProgress(false);
        super.onCancelled();
    }
}