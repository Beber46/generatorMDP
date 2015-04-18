package fr.beber.generatormdp.util;

import android.content.Intent;
import android.os.AsyncTask;
import fr.beber.generatormdp.LoginActivity;
import fr.beber.generatormdp.MainActivity;
import fr.beber.generatormdp.bdd.dao.UserDAO;

/**
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 */
public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

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

        final UserDAO userDAO = new UserDAO(mLoginActivity.getApplicationContext());
        userDAO.openOnlyRead();
        final Boolean retour = userDAO.authentificate(mNickname,mPassword);
        userDAO.close();

        // TODO: register the new account here.
        return retour;
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