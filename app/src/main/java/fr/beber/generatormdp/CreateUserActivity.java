package fr.beber.generatormdp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import fr.beber.generatormdp.bdd.dao.UserDAO;
import fr.beber.generatormdp.bean.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateUserActivity extends Activity {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        final EditText createNickname = (EditText) findViewById(R.id.ETCreateNickname);
        final EditText createEmail = (EditText) findViewById(R.id.ETCreateEmail);
        final EditText createPassword = (EditText) findViewById(R.id.ETCreatePassword);
        final EditText createConfirmPassword = (EditText) findViewById(R.id.ETCreateConfirmPassword);

        final Button BTValide = (Button)findViewById(R.id.BTValidateUser);
        BTValide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verifiyData(createNickname, createEmail, createPassword, createConfirmPassword)) {

                    final User user = new User();
                    user.setUsername(createNickname.getText().toString());
                    user.setEmail(createEmail.getText().toString().toLowerCase());
                    user.setMdp(createPassword.getText().toString());

                    final UserDAO userDAO = new UserDAO(getApplicationContext());
                    userDAO.open();
                    userDAO.save(user);
                    userDAO.close();

                    final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    /**
     * Permet de vérifier les données passées en entrée pour la création du compte utilisateur.
     *
     * @param nicknameEditText Le pseudo.
     * @param emailEditText    L'email.
     * @param passwordEditText Le mot de passe.
     * @param confirmPasswordEditText Confirmation du mot de passe.
     * @return <code>true</code> si ok.
     */
    private boolean verifiyData(final EditText nicknameEditText, final EditText emailEditText, final EditText passwordEditText, final EditText confirmPasswordEditText) {

        boolean cancel = false;
        View focusView = null;

        nicknameEditText.setError(null);
        emailEditText.setError(null);
        passwordEditText.setError(null);
        confirmPasswordEditText.setError(null);

        final String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(password)) {
            cancel = true;
            passwordEditText.setError(getString(R.string.error_required));
            focusView = passwordEditText;

            passwordEditText.setText("");
            confirmPasswordEditText.setText("");
        } else if (!LoginActivity.isPasswordValid(password)) {
            cancel = true;
            passwordEditText.setError(getString(R.string.error_invalid_password));
            focusView = passwordEditText;
            focusView.requestFocus();

            passwordEditText.setText("");
            confirmPasswordEditText.setText("");
        }

        final String confirmPassword = confirmPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            cancel = true;
            confirmPasswordEditText.setError(getString(R.string.error_required));
            focusView = confirmPasswordEditText;

            passwordEditText.setText("");
            confirmPasswordEditText.setText("");
        } else if (!password.equals(confirmPassword)){
            cancel = true;
            confirmPasswordEditText.setError(getString(R.string.error_invalid_confirm_email));
            focusView = confirmPasswordEditText;

            passwordEditText.setText("");
            confirmPasswordEditText.setText("");
        }

        final String email = emailEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            cancel = true;
            emailEditText.setError(getString(R.string.error_required));
            focusView = emailEditText;
        } else if (!this.isEmail(email)) {
            cancel = true;
            emailEditText.setError(getString(R.string.error_invalid_email));
            focusView = emailEditText;
        }

        final String nickname = nicknameEditText.getText().toString();

        if (TextUtils.isEmpty(nickname)) {
            cancel = true;
            nicknameEditText.setError(getString(R.string.error_required));
            focusView = nicknameEditText;
        } else if (nickname.length() < 4) {
            cancel = true;
            nicknameEditText.setError(getString(R.string.error_invalid_nickname));
            focusView = nicknameEditText;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else
            return true;

    }

    /**
     * Vérification de l'email.
     *
     * @param email Adresse email de l'utilisateur.
     * @return <code>true</code> si ok.
     */
    private boolean isEmail(final String email) {
        final Pattern p = Pattern.compile(EMAIL_PATTERN);
        final Matcher m = p.matcher(email);
        return m.matches();
    }
}
